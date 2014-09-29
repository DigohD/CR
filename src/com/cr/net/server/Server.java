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
import com.cr.net.packets.AcceptPacket03;
import com.cr.net.packets.LoginPacket00;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;

public class Server implements Runnable{
	
	private DatagramSocket socket;
	private Thread thread;
	
	private List<HeroMP> connectedPlayers = new ArrayList<HeroMP>();
	
	private boolean running = false;
	
	public Server(){
		
		try {
			socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	
	public void start(){
		running = true;
		thread = new Thread(this, "server-thread");
		thread.start();
	}
	
	public void stop(){
		running = false;
		socket.close();
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
				HeroMP hero = new HeroMP(new Vector2f(100, 100), ((LoginPacket00) packet).getUserName(), address, port);
				addConnection(hero, (LoginPacket00) packet);
				break;
			case DISCONNECT:
				break;
		}
		
	}
	
	public void addConnection(HeroMP player, LoginPacket00 packet) {
    	boolean alreadyConnected = false;
    	//loop through all the connected players 
        for (HeroMP p : this.connectedPlayers) {
        	
            if (player.getUserName().equalsIgnoreCase(p.getUserName())) {
                if (p.getInetAddress() == null) {
                    p.setIp(player.getInetAddress());
                }
                if (p.getPort() == -1) {
                    p.setPort(player.getPort());
                }
                alreadyConnected = true;
            } else {
                // relay to the current connected player that there is a new
                // player
                sendData(packet.getData(), p.getInetAddress(), p.getPort());

                // relay to the new player that the currently connect player
                // exists
                packet = new LoginPacket00(p.getUserName(), p.getPosition());
                sendData(packet.getData(), player.getInetAddress(), player.getPort());
            }
        }
        if (!alreadyConnected) {
        	System.out.println("Player: " + player.getUserName() + " joined server");
        	sendData(new AcceptPacket03(player.getUserName()).getData(), player.getInetAddress(), player.getPort());
            this.connectedPlayers.add(player);
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
		for(HeroMP h : connectedPlayers)
			sendData(data, h.getInetAddress(), h.getPort());
	}
	

}
