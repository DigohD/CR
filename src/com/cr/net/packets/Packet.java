package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public abstract class Packet {
	
	public static enum PacketTypes{
		INVALID(-1), 
		LOGIN(10), 
		CONNECT(11), 
		MOVE(12), 
		ACCEPT(13), 
		MAP(14), 
		REQUESTMAP(15), 
		DISCONNECT(16),
		STATS(17), 
		STATICOBJECT(18),
		LOOT(19);
		
		private int packetID;
		
		private PacketTypes(int packetID){
			this.packetID = packetID;
		}
		
		public int getID(){
			return packetID;
		}
	}
	
	public byte packetID;
	
	public Packet(int packetID){
		this.packetID = (byte) packetID;
	}
	
	public abstract void writeData(Client client);
	public abstract void writeData(Server server);
	public abstract byte[] getData();
	
	public String readData(byte[] data){
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public static PacketTypes lookupPacket(int id){
		for(PacketTypes p : PacketTypes.values()){
			if(p.getID() == id) return p;
		}
		return PacketTypes.INVALID;
	}

}
