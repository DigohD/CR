package com.cr.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.net.HeroMP;
import com.cr.net.packets.Packet;
import com.cr.net.packets.Packet.PacketTypes;
import com.cr.net.packets.Packet10Login;
import com.cr.net.packets.Packet12Move;
import com.cr.net.packets.Packet13Accept;
import com.cr.net.packets.Packet14Map;
import com.cr.net.packets.Packet15RequestMap;
import com.cr.net.packets.Packet16Disconnect;
import com.cr.net.packets.Packet18StaticObject;
import com.cr.net.packets.Packet19Loot;
import com.cr.net.packets.Packet20RequestObj;
import com.cr.states.net.MPClientState;
import com.cr.world.World;
import com.cr.world.terrain.Stone;
import com.cr.world.terrain.Tree;

public class Client implements Runnable{
	
	private InetAddress ip;
	private DatagramSocket socket;
	private Thread thread;
	
	private Vector2f startPos;
	
	private int port;
	private int packetNumber = 0;
	private int width, height;
	
	private volatile boolean running = false;
	public boolean disconnected = false;
	
	public boolean connected = false;
	
	private HashMap<String, HeroMP> clientsMap = new HashMap<String, HeroMP>();
	private HashMap<Byte, Integer> byteToIntMap = new HashMap<Byte, Integer>();
	
	public LinkedList<Integer> pixels = new LinkedList<Integer>();
	
	private Packet10Login loginPacket;
	private String userName;
	
	public Client(String userName, String ip, int port){
		this.userName = userName;
		this.port = port;
	
		byteToIntMap.put((byte)-1, ColorRGBA.BLACK);
		byteToIntMap.put((byte)0, ColorRGBA.GRAY);
		byteToIntMap.put((byte)1, ColorRGBA.GREEN);
		byteToIntMap.put((byte)2, ColorRGBA.BLUE);
		byteToIntMap.put((byte)3, ColorRGBA.BROWN);
		byteToIntMap.put((byte)4, ColorRGBA.YELLOW);
		
		try {
			this.ip = InetAddress.getByName(ip);
			socket = new DatagramSocket();
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
		loginPacket = new Packet10Login(userName);
		sendData(loginPacket.getData());
	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(!connected){
			loginPacket = new Packet10Login(userName);
			sendData(loginPacket.getData());
			
			byte[] data = new byte[1024];
        	DatagramPacket packet = new DatagramPacket(data, data.length);
               
            try{
                socket.setSoTimeout(1000);
                //System.out.println("BEFORE RECEIVE, !conected");
                socket.receive(packet);
                //System.out.println("AFTER RECEIVE, !connected");
            }catch(SocketTimeoutException e){
            	continue;
            } catch (IOException e) {	
            	e.printStackTrace();
            }
            	parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
       }
	
        while(running) {
        	byte[] data = new byte[1024];
        	DatagramPacket packet = new DatagramPacket(data, data.length);
               
            try{
                socket.setSoTimeout(1000);
               // System.out.println("BEFORE RECEIVE");
                socket.receive(packet);
                //System.out.println("AFTER RECEIVE");
            }catch(SocketTimeoutException e){
            	continue;
            } catch (IOException e) {	
            	e.printStackTrace();
            }
            	parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            }
           socket.close();
	}

	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		
		PacketTypes type = Packet.lookupPacket(Integer.parseInt(message.substring(0, 2)));
		Packet packet = null;
		
		switch(type){
			case MAP:
				//System.out.println("MAP PACKET RECEIVED");
				packet = new Packet14Map(data);
				handleMap(packet, data, address, port);
				break;
			case ACCEPT:
				connected = true;
				System.out.println("ACCEPT PACKET RECEIVED");
				packet = new Packet13Accept(data);
				handleAccept(packet, address, port);
				break;
			case MOVE:
				//System.out.println("MOVE PACKET RECEIVED");
				if(MPClientState.worldAssembled){
					packet = new Packet12Move(data);
					handleMove(packet, address, port);
				}
				
				break;
			case LOGIN:
				packet = new Packet10Login(data);
				handleLogin(packet, address, port);
				break;
			case DISCONNECT:
				packet = new Packet16Disconnect(data);
				handleDisconnect(packet, address, port);
				break;
			case LOOT:
				packet = new Packet19Loot(data);
				handleLoot(packet, address, port);
				break;
			case STATICOBJECT:
				packet = new Packet18StaticObject(data);
				handleStaticObject(packet, address, port);
				break;
			default:
				break;
		}
	}
	
	public List<Tree> trees = new ArrayList<Tree>();
	public List<Stone> stones = new ArrayList<Stone>();
	public boolean treesLoaded = false;
	public boolean stonesLoaded = false;
	
	int treeIndex = 0;
	int stoneIndex = 0;
	
	private void handleStaticObject(Packet packet, InetAddress address, int port) {
		Packet18StaticObject p = (Packet18StaticObject) packet;
		
		int type = p.getType();
		
		switch(type){
			
			
			case 0:
				if(treeIndex < p.getAmount()){
					Tree t = new Tree(p.getX(), p.getY());
					t.setObjectID(p.getObjectID());
					trees.add(t);
					Packet20RequestObj p0 = new Packet20RequestObj(0, treeIndex);
					sendData(p0.getData());
					treeIndex++;
				}
				
				if(trees.size() == p.getAmount()){
					treesLoaded = true;
				}
				break;
			case 1:
				System.out.println("STONES RECEIVED");
				System.out.println("Stones: " + stones.size());
				if(stoneIndex < p.getAmount()){
					Stone s = new Stone(p.getX(), p.getY());
					s.setObjectID(p.getObjectID());
					stones.add(s);
					Packet20RequestObj p1 = new Packet20RequestObj(1, stoneIndex);
					sendData(p1.getData());
					stoneIndex++;
				}
				
				if(stones.size() == p.getAmount()){
					stonesLoaded = true;
				}
				break;
			default:
				break;
		}
		
//		if(treesLoaded && stonesLoaded){
//			System.out.println("WORLD ASSEMLBED");
//			
//		}
			
		
	}

	private void handleLoot(Packet packet, InetAddress address, int port) {
		Packet19Loot p = (Packet19Loot) packet;
		World.spawnLoot(p.getX(), p.getY(), p.getType(), p.getAmount());
	}

	private void handleLogin(Packet packet, InetAddress address, int port) {
		Packet10Login p = (Packet10Login) packet;
		
		if(!(p.getUserName().equalsIgnoreCase(this.userName))){
			clientsMap.put(p.getUserName(), new HeroMP(p.getUserName(),new Vector2f(0,0), address, port));
		}
		else System.out.println(p.getUserName() + " has joined!");
		
	}
	
	private void handleDisconnect(Packet packet, InetAddress address, int port) {
		Packet16Disconnect p = (Packet16Disconnect) packet;
		if(clientsMap.containsKey(p.getUserName())){
			clientsMap.get(p.getUserName()).setLive(false);
			clientsMap.remove(p.getUserName());
		}
	}

	private void handleMove(Packet packet, InetAddress address, int port){
		String s = ((Packet12Move) packet).getUserName();
		if(clientsMap.containsKey(s)){
			clientsMap.get(s).setPosition(((Packet12Move) packet).getPos());
			clientsMap.get(s).setDirection(((Packet12Move) packet).getDir());
		}
	}
	
	private void handleAccept(Packet packet, InetAddress address, int port){
		Packet13Accept p = (Packet13Accept) packet;
		width = p.getWidth();
		height = p.getHeight();

		HeroMP hostHero = new HeroMP(p.getUsername(), new Vector2f(p.getX(), p.getY()), address, port);
		startPos = hostHero.getPosition();
		clientsMap.put(hostHero.getUserName(), hostHero);
		
		Packet15RequestMap p2 = new Packet15RequestMap(packetNumber);
		sendData(p2.getData());
		//System.out.println("REQUEST MAP PACKET SENT");
	}
	
	boolean mapReceived = false;
	
	private void handleMap(Packet packet, byte[] data, InetAddress address, int port){
		if(packetNumber == ((Packet14Map)packet).getPacketNumber()){
			assembleWorld(data);
			//System.out.println(pixels.size());
			if(pixels.size() > width*height*3){
				while(pixels.size() > 30000) pixels.removeFirst();
				MPClientState.worldAssembled = true;
		
				if(!mapReceived){
					try {
						MPClientState.lock.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Packet20RequestObj p2 = new Packet20RequestObj(0, treeIndex);
					sendData(p2.getData());
					treeIndex++;
					Packet20RequestObj p1 = new Packet20RequestObj(1, stoneIndex);
					sendData(p1.getData());
					stoneIndex++;
					MPClientState.lock.release();
					mapReceived = true;
				}
				
				
				

				return;
			}
			packetNumber++;
			Packet15RequestMap p = new Packet15RequestMap(packetNumber);
			sendData(p.getData());
			//System.out.println("REQUEST SENT");
		}else{
			Packet15RequestMap p = new Packet15RequestMap(packetNumber);
			sendData(p.getData());
		}
	}
	
	private void assembleWorld(byte[] data){
		for(int i = 0; i < 924; i++)
			pixels.addFirst(byteToIntMap.get(data[i+100]));
	}
	
	public void sendData(byte[] data){
		if(!disconnected){
			DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		
		
	}

	public HashMap<String, HeroMP> getClientsMap() {
		return clientsMap;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Vector2f getStartPos() {
		return startPos;
	}

	public String getUserName() {
		return userName;
	}

}

