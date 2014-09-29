package com.cr.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.cr.entity.hero.HeroMP;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;
import com.cr.net.packets.Packet00Login;
import com.cr.states.PlayState;

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
			case INVALID:
				break;
			case LOGIN:
				packet = new Packet00Login(data);
				//System.out.println("[" + address.getHostAddress() + ":" + port + "] " + ((Packet00Login) packet).getUserName() + " has connected");
				HeroMP hero = new HeroMP(PlayState.getWorld(), ((Packet00Login) packet).getUserName(), address, port);
				
				break;
			case DISCONNECT:
				break;
		}
		
	}
	
	public void addConnection(HeroMP hero, Packet00Login packet){
		boolean alreadyConnected = false;
		
		for(HeroMP h : this.connectedPlayers){
			if(hero.getUserName().equalsIgnoreCase(h.getUserName())){
				
			}
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
