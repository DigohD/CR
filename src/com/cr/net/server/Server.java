package com.cr.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cr.game.EntityManager;
import com.cr.net.HeroMP;
import com.cr.net.client.ClientInfo;
import com.cr.net.packets.AcceptPacket03;
import com.cr.net.packets.ConnectPacket01;
import com.cr.net.packets.LoginPacket00;
import com.cr.net.packets.MapPacket04;
import com.cr.net.packets.MovePacket02;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;
import com.cr.net.packets.RequestMapPacket05;
import com.cr.states.net.MPHostState;

public class Server implements Runnable{
	
	private DatagramSocket socket;
	private Thread thread;
	private HashMap<String, HeroMP> clientsMap = new HashMap<String, HeroMP>();
	
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
		
		System.out.println(message.substring(0, 2));
		PacketTypes type = Packet.lookupPacket(Integer.parseInt(message.substring(0, 2)));
		
		switch(type){
			default:
				break;
			case INVALID:
				break;
			case DISCONNECT:
				break;
			case LOGIN:
				LoginPacket00 packet0 = new LoginPacket00(data);
				handleLogin(packet0, address, port);
				break;
			case MOVE:
				MovePacket02 packet2 = new MovePacket02(data);
				String s = packet2.getUserName();
				if(clientsMap.containsKey(s))
					clientsMap.get(s).setPosition(packet2.getPos());
				
//				for(int i = 0; i < connectedClients.size(); i++){
//					if(connectedClients.get(i).getUserName().equalsIgnoreCase(((MovePacket02)packet).getUserName())){
//						heroMockups.get(i).setPosition(((MovePacket02) packet).getPos());
//					}
//				}
				
				packet2.writeData(this);
				break;
			case REQUESTMAP:
				RequestMapPacket05 packet05 = new RequestMapPacket05(data);
				MapPacket04 p = new MapPacket04(packet05.getPacketNumber());
				
				byte[] data2 = new byte[1024];
				for(int i = 0; i < p.getData().length; i++)
					data2[i] = p.getData()[i];
				
				sendData(MPHostState.getWorld().getBytes2(p.getPacketNumber(), data2), address, port);
				//System.out.println(new String(MPHostState.getWorld().getBytes2(p.getPacketNumber(), data2)));
				break;
		}
		
	}
	
	private void handleLogin(LoginPacket00 packet, InetAddress address, int port){
		System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUserName() + " has connected");
		String name = packet.getUserName();
		HeroMP hero = new HeroMP(name, EntityManager.getHero().getPos(), address, port);
		addConnection(hero, packet);
	}
	
	public void addConnection(HeroMP client, Packet packet) {
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
        
        packet = new ConnectPacket01(EntityManager.getHero().getUserName(), EntityManager.getHero().getPos());
        sendData(packet.getData(), client.getInetAddress(), client.getPort());
        
        if (!alreadyConnected) {
        	System.out.println("Player: " + client.getUserName() + " joined server succesfully");
        	sendData(new AcceptPacket03(client.getUserName(), MPHostState.getWorld().getWidth(), 
        			MPHostState.getWorld().getHeight()).getData(), client.getInetAddress(), client.getPort());
            clientsMap.put(client.getUserName(), client);
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
