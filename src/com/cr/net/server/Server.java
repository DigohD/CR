package com.cr.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.cr.engine.core.Vector2f;
import com.cr.entity.hero.HeroMP;
import com.cr.game.EntityManager;
import com.cr.net.client.ClientInfo;
import com.cr.net.packets.AcceptPacket03;
import com.cr.net.packets.ConnectPacket01;
import com.cr.net.packets.LoginPacket00;
import com.cr.net.packets.MovePacket02;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;

public class Server implements Runnable{
	
	private DatagramSocket socket;
	private Thread thread;
	
	private List<ClientInfo> connectedClients = new ArrayList<ClientInfo>();
	private List<HeroMP> heroMockups = new ArrayList<HeroMP>();
	
	
	
	private boolean running = false;
	
	public Server(){
		System.out.println("Server created");
		
		try {
			socket = new DatagramSocket(12121);
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
		
		PacketTypes type = Packet.lookupPacket(Integer.parseInt(message.substring(0, 2)));
		Packet packet = null;
		
		switch(type){
			default:
				break;
			case INVALID:
				break;
			case LOGIN:
				packet = new LoginPacket00(data);
				System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((LoginPacket00) packet).getUserName() + " has connected");
				ClientInfo client = new ClientInfo(((LoginPacket00) packet).getUserName(), address, port);
				HeroMP hero = new HeroMP(client.getUserName(), EntityManager.getHero().getPos());
				addConnection(client, hero, (LoginPacket00) packet);
				break;
			case MOVE:
				packet = new MovePacket02(data);
				for(int i = 0; i < connectedClients.size(); i++){
					if(connectedClients.get(i).getUserName().equalsIgnoreCase(((MovePacket02)packet).getUserName())){
						heroMockups.get(i).setPosition(((MovePacket02) packet).getPos());
					}
				}
				
				packet.writeData(this);
				break;
			case DISCONNECT:
				break;
		}
		
	}
	
	public void addConnection(ClientInfo client, HeroMP hero, Packet packet) {
    	boolean alreadyConnected = false;
    	//loop through all the connected players 
        for (ClientInfo c : this.connectedClients) {
        	
            if (client.getUserName().equalsIgnoreCase(c.getUserName())) {
                if (c.getInetAddress() == null) {
                    c.setIp(client.getInetAddress());
                }
                if (c.getPort() == -1) {
                    c.setPort(client.getPort());
                }
                alreadyConnected = true;
            } else {
                // relay to the current connected player that there is a new
                // player
                sendData(packet.getData(), c.getInetAddress(), c.getPort());

                // relay to the new player that the currently connect player
                // exists
                packet = new LoginPacket00(c.getUserName());
                sendData(packet.getData(), client.getInetAddress(), client.getPort());
            }
        }
        
        packet = new ConnectPacket01(EntityManager.getHero().getUserName(), EntityManager.getHero().getPos());
        sendData(packet.getData(), client.getInetAddress(), client.getPort());
        
        if (!alreadyConnected) {
        	System.out.println("Player: " + client.getUserName() + " joined server succesfully");
        	sendData(new AcceptPacket03(client.getUserName()).getData(), client.getInetAddress(), client.getPort());
            connectedClients.add(client);
            heroMockups.add(hero);
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
		for(ClientInfo c : connectedClients)
			sendData(data, c.getInetAddress(), c.getPort());
	}
	
	public List<ClientInfo> getClients(){
		return connectedClients;
	}
	
	public List<HeroMP> getMockups(){
		return heroMockups;
	}
	

}
