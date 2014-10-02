package com.cr.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

import com.cr.game.EntityManager;
import com.cr.net.HeroMP;
import com.cr.net.packets.AcceptPacket03;
import com.cr.net.packets.ConnectPacket01;
import com.cr.net.packets.DisconnectPacket06;
import com.cr.net.packets.LoginPacket00;
import com.cr.net.packets.MapPacket04;
import com.cr.net.packets.MovePacket02;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;
import com.cr.net.packets.RequestMapPacket05;
import com.cr.states.net.MPHostState;
import com.cr.stats.StatsSheet;

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
		socket.close();
		System.out.println("Server closed..");
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while(running){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
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
				DisconnectPacket06 packet06 = new DisconnectPacket06(data);
				handleDisconnect(packet06, address, port);
				break;
			case LOGIN:
				LoginPacket00 packet00 = new LoginPacket00(data);
				handleLogin(packet00, address, port);
				break;
			case MOVE:
				MovePacket02 packet02 = new MovePacket02(data);
				handleMove(packet02, address, port);
				break;
			case REQUESTMAP:
				RequestMapPacket05 packet05 = new RequestMapPacket05(data);
				handleRequestMap(packet05, address, port);
				break;
			default:
				break;
		}
		
	}
	
	private void handleDisconnect(DisconnectPacket06 packet, InetAddress address, int port){
		System.out.println("DISCONNECT PACKET RECEIVED");
		System.out.println(new String(packet.getData()));
		System.out.println(clientsMap.get(packet.getUserName()));

		if(clientsMap.containsKey(packet.getUserName())){
			System.out.println("CLIENT EXISTS");
		}
		clientsMap.get(packet.getUserName()).setLive(false);
		clientsMap.remove(packet.getUserName());
		
		
		//sendDataToAllClients(packet.getData());
	}
	
	private void handleLogin(LoginPacket00 packet, InetAddress address, int port){
		System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUserName() + " has connected");
		String name = packet.getUserName();
		HeroMP hero = new HeroMP(name, EntityManager.getHero().getPos(), address, port);
		addConnection(hero, packet);
	}
	
	private void handleMove(MovePacket02 packet, InetAddress address, int port){
		String s = packet.getUserName();
		if(clientsMap.containsKey(s))
			clientsMap.get(s).setPosition(packet.getPos());
	
		packet.writeData(this);
	}
	
	private void handleRequestMap(RequestMapPacket05 packet, InetAddress address, int port){
		MapPacket04 p = new MapPacket04(packet.getPacketNumber());
		
		if(p.getPacketNumber() == -1){
			ConnectPacket01 p2 = new ConnectPacket01(EntityManager.getHero().getUserName(), EntityManager.getHero().getPos());
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
                packet = new LoginPacket00(h.getUserName());
                sendData(packet.getData(), client.getInetAddress(), client.getPort());
            }
        }
        

        
        if (!alreadyConnected) {
        	System.out.println("Player: " + client.getUserName() + " joined server succesfully");
        	sendData(new AcceptPacket03(client.getUserName(), MPHostState.getWorld().getWidth(), 
        			MPHostState.getWorld().getHeight()).getData(), client.getInetAddress(), client.getPort());
            clientsMap.put(client.getUserName(), client);
            statsMap.put(client, new StatsSheet(true));
        }
    }
	

	public void sendData(byte[] data, InetAddress ip, int port){
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
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
