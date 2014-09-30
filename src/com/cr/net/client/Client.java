package com.cr.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.cr.engine.core.Vector2f;
import com.cr.entity.hero.HeroMP;
import com.cr.net.packets.ConnectPacket01;
import com.cr.net.packets.MovePacket02;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;

public class Client implements Runnable{

	private InetAddress ip;
	private DatagramSocket socket;
	private Thread thread;
	
	private List<ClientInfo> connectedClients = new ArrayList<ClientInfo>();
	private List<HeroMP> heroMockups = new ArrayList<HeroMP>();
	
	private volatile boolean running = false;
	
	public Client(String ip){
	
		
		try {
			socket = new DatagramSocket();
			this.ip = InetAddress.getByName(ip);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this, "client-thread");
		thread.start();
	}
	
	public synchronized void stop(){
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
			case CONNECT:
				packet = new ConnectPacket01(data);
				HeroMP hostHero = new HeroMP(((ConnectPacket01)packet).getUserName(),((ConnectPacket01)packet).getPos());
				heroMockups.add(hostHero);
				connectedClients.add(new ClientInfo(((ConnectPacket01)packet).getUserName(), address, port));
				break;
			case MOVE:
				packet = new MovePacket02(data);
				
				for(int i = 0; i < connectedClients.size(); i++){
					if(connectedClients.get(i).getUserName().equalsIgnoreCase(((MovePacket02)packet).getUserName())){
						heroMockups.get(i).setPosition(((MovePacket02) packet).getPos());
					}
				}
				
				
				
				break;
		}
	}
	
	public void sendData(byte[] data){
		
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<ClientInfo> getConnectedClients() {
		return connectedClients;
	}

	public List<HeroMP> getHeroMockups() {
		return heroMockups;
	}
	

}

