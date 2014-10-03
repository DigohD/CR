package com.cr.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import com.cr.game.EntityManager;
import com.cr.net.HeroMP;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;
import com.cr.net.packets.Packet10Login;
import com.cr.net.packets.Packet11Connect;
import com.cr.net.packets.Packet12Move;
import com.cr.net.packets.Packet13Accept;
import com.cr.net.packets.Packet14Map;
import com.cr.net.packets.Packet15RequestMap;
import com.cr.net.packets.Packet16Disconnect;
import com.cr.net.packets.Packet17Stat;
import com.cr.states.net.MPHostState;
import com.cr.stats.Stat;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;

public class Server implements Runnable{
	
	private DatagramSocket socket;
	private Thread thread;
	
	private HashMap<String, HeroMP> clientsMap = new HashMap<String, HeroMP>();
	private HashMap<HeroMP, StatsSheet> statsMap = new HashMap<HeroMP, StatsSheet>();
	
	private boolean running = false;
	
	public Server(int port){
		System.out.println("Server created");
		
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		System.out.println("Server started..");
		running = true;
		thread = new Thread(this, "server-thread");
		thread.start();
	}
	
	public void stop(){
		System.out.println("Server closing..");
		running = false;
		
		
		
		System.out.println("Server closed..");
		try {
			thread.join();
			System.out.println("Threads joined..");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			socket.setSoTimeout(10);
            while(running) {
            	byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
                try {
                	socket.receive(packet);
                }catch (SocketTimeoutException e) {
                    continue;
                }catch (IOException e) {
                    e.printStackTrace();
                }
                
                System.out.println("REcieve Packet: " + new String(packet.getData()));
                parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            }
        }catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
            socket.close();
        }
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		
		//System.out.println(message.substring(0, 2));
		PacketTypes type = Packet.lookupPacket(Integer.parseInt(message.substring(0, 2)));
		
		switch(type){
			case INVALID:
				break;
			case DISCONNECT:
				Packet16Disconnect packet06 = new Packet16Disconnect(data);
				handleDisconnect(packet06, address, port);
				break;
			case LOGIN:
				Packet10Login packet00 = new Packet10Login(data);
				handleLogin(packet00, address, port);
				break;
			case MOVE:
				Packet12Move packet02 = new Packet12Move(data);
				handleMove(packet02, address, port);
				break;
			case REQUESTMAP:
				Packet15RequestMap packet05 = new Packet15RequestMap(data);
				handleRequestMap(packet05, address, port);
				break;
			case STATS:
				Packet17Stat packet07 = new Packet17Stat(data);
				handleStatPacket(packet07, address, port);
				break;
			default:
				break;
		}
		
	}
	
	private void handleStatPacket(Packet17Stat packet07, InetAddress address, int port) {
		HeroMP client = clientsMap.get(packet07.getUserName());
		StatsSheet sheet = statsMap.get(client);
		if(sheet != null){
			Stat stat = sheet.getStat(StatID.valueOf(packet07.getStatID()));
			stat.setNewBase(packet07.getValue());
		}
	}

	private void handleDisconnect(Packet16Disconnect packet, InetAddress address, int port){
		if(clientsMap.containsKey(packet.getUserName())){
			clientsMap.get(packet.getUserName()).setLive(false);
			statsMap.remove(clientsMap.get(packet.getUserName()));
			clientsMap.remove(packet.getUserName());
		}
		sendDataToAllClients(packet.getData());
	}
	
	private void handleLogin(Packet10Login packet, InetAddress address, int port){
		System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUserName() + " has connected");
		String name = packet.getUserName();
		HeroMP hero = new HeroMP(name, EntityManager.getHero().getPos(), address, port);
		addConnection(hero, packet);
	}
	
	private void handleMove(Packet12Move packet, InetAddress address, int port){
		String s = packet.getUserName();
		if(clientsMap.containsKey(s)){
			clientsMap.get(s).setPosition(packet.getPos());
			clientsMap.get(s).setDirection(packet.getDir());
		}
		packet.writeData(this);
	}
	
	private void handleRequestMap(Packet15RequestMap packet, InetAddress address, int port){
		Packet14Map p = new Packet14Map(packet.getPacketNumber());
		
		if(p.getPacketNumber() == -1){
			Packet11Connect p2 = new Packet11Connect(EntityManager.getHero().getUserName(), EntityManager.getHero().getPos());
	        sendData(p2.getData(), address, port);
		}else{
			byte[] data2 = new byte[1024];
			for(int i = 0; i < p.getData().length; i++)
				data2[i] = p.getData()[i];
			
			sendData(MPHostState.getWorld().getBytes(p.getPacketNumber(), data2), address, port);
		}
		
		//System.out.println(new String(MPHostState.getWorld().getBytes2(p.getPacketNumber(), data2)));
	}
	
	private void addConnection(HeroMP client, Packet packet) {
    	boolean alreadyConnected = false;
    	//loop through all the connected players 
        for (String name : clientsMap.keySet()) {
        	HeroMP h = clientsMap.get(name);
            if (name.equalsIgnoreCase(client.getUserName())) {
                if (h.getInetAddress() == null) {
                    h.setIp(client.getInetAddress());
                }
                if (h.getPort() == -1) {
                    h.setPort(client.getPort());
                }
                alreadyConnected = true;
            } else {
                // relay to the current connected player that there is a new
                // player
                sendData(packet.getData(), h.getInetAddress(), h.getPort());

                // relay to the new player that the currently connect player
                // exists
                packet = new Packet10Login(h.getUserName());
                sendData(packet.getData(), client.getInetAddress(), client.getPort());
            }
        }
        
        if (!alreadyConnected) {
        	System.out.println("Player: " + client.getUserName() + " joined server succesfully");
        	sendData(new Packet13Accept(client.getUserName(), MPHostState.getWorld().getWidth(), 
        			MPHostState.getWorld().getHeight()).getData(), client.getInetAddress(), client.getPort());
            clientsMap.put(client.getUserName(), client);
            statsMap.put(client, new StatsSheet(true));
        }
    }
	

	public void sendData(byte[] data, InetAddress ip, int port){
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
		
//		System.out.println("SendPacket: " + new String(packet.getData()));
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDataToAllClients(byte[] data) {
		for(String name : clientsMap.keySet()){
			HeroMP h = clientsMap.get(name);
			sendData(data, h.getInetAddress(), h.getPort());
		}	
	}

	public HashMap<String, HeroMP> getClientsMap() {
		return clientsMap;
	}

}
