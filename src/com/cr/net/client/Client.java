package com.cr.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.cr.engine.graphics.ColorRGBA;
import com.cr.net.HeroMP;
import com.cr.net.packets.AcceptPacket03;
import com.cr.net.packets.ConnectPacket01;
import com.cr.net.packets.MapPacket04;
import com.cr.net.packets.MovePacket02;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;
import com.cr.net.packets.RequestMapPacket05;
import com.cr.states.net.MPClientState;

public class Client implements Runnable{

	private InetAddress ip;
	private DatagramSocket socket;
	private Thread thread;
	
	private int port;
	private int packetNumber = 0;
	public int width, height;
	
	private volatile boolean running = false;
	
	private int[] bottomLayerData, middleLayerData, topLayerData;
	
	private HashMap<String, HeroMP> clientsMap = new HashMap<String, HeroMP>();
	private HashMap<Byte, Integer> byteToIntMap = new HashMap<Byte, Integer>();
	
	public LinkedList<Integer> pixels = new LinkedList<Integer>();
	
	public Client(String ip, int port){
		
		this.port = port;
	
		byteToIntMap.put((byte)-1, ColorRGBA.BLACK);
		byteToIntMap.put((byte)0, ColorRGBA.GRAY);
		byteToIntMap.put((byte)1, ColorRGBA.GREEN);
		byteToIntMap.put((byte)2, ColorRGBA.BLUE);
		byteToIntMap.put((byte)3, ColorRGBA.BROWN);
		byteToIntMap.put((byte)4, ColorRGBA.YELLOW);
		
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
			case MAP:
				//System.out.println("data size: " + data.length);
				packet = new MapPacket04(data);
				if(packetNumber == ((MapPacket04)packet).getPacketNumber()){
					assembleWorld(data);
					System.out.println(pixels.size());
					if(pixels.size() > width*height*3){
						while(pixels.size() > 30000) pixels.removeFirst();
						MPClientState.worldAssembled = true;
						return;
					}
					packetNumber++;
					RequestMapPacket05 p = new RequestMapPacket05(packetNumber);
					sendData(p.getData());
					System.out.println("REQUEST SENT");
				}else{
					RequestMapPacket05 p = new RequestMapPacket05(packetNumber);
					sendData(p.getData());
				}
				
				break;
			case ACCEPT:
				packet = new AcceptPacket03(data);
				width = ((AcceptPacket03)packet).getWidth();
				height = ((AcceptPacket03)packet).getHeight();
				bottomLayerData = new int[((AcceptPacket03)packet).getWidth() * ((AcceptPacket03)packet).getHeight()];
				middleLayerData = new int[((AcceptPacket03)packet).getWidth() * ((AcceptPacket03)packet).getHeight()];
				topLayerData = new int[((AcceptPacket03)packet).getWidth() * ((AcceptPacket03)packet).getHeight()];
				
				RequestMapPacket05 p = new RequestMapPacket05(packetNumber);
				sendData(p.getData());
				
				break;
			case CONNECT:
				packet = new ConnectPacket01(data);
				HeroMP hostHero = new HeroMP(((ConnectPacket01)packet).getUserName(),((ConnectPacket01)packet).getPos(), address, port);
				clientsMap.put(hostHero.getUserName(), hostHero);
				break;
			case MOVE:
				packet = new MovePacket02(data);
				
				String s = ((MovePacket02) packet).getUserName();
				if(clientsMap.containsKey(s))
					clientsMap.get(s).setPosition(((MovePacket02) packet).getPos());
				
//				for(int i = 0; i < connectedClients.size(); i++){
//					if(connectedClients.get(i).getUserName().equalsIgnoreCase(((MovePacket02)packet).getUserName())){
//						heroMockups.get(i).setPosition(((MovePacket02) packet).getPos());
//					}
//				}
				break;
		}
	}
	
	public void assembleWorld(byte[] data){
		for(int i = 0; i < 924; i++){
			pixels.addFirst(byteToIntMap.get(data[i+100]));
		}
	}
	
	public void sendData(byte[] data){
		
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public HashMap<String, HeroMP> getClientsMap() {
		return clientsMap;
	}

}

