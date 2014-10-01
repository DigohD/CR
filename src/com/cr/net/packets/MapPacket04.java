package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class MapPacket04 extends Packet{

	private int packetNumber;
	
	public MapPacket04(byte[] data) {
	    super(04);
	    
	    String[] dataArray = readData(data).split(":");
	  
	    this.packetNumber = Integer.parseInt(dataArray[1]);
	}
	
	public MapPacket04(int packetNumber) {
		super(04);
		this.packetNumber = packetNumber;
	}

	@Override
	public void writeData(Client client) {
		client.sendData(getData());
		
	}

	@Override
	public void writeData(Server server) {
		server.sendDataToAllClients(getData());
		
	}

	@Override
	public byte[] getData() {
		return ("04" + ":" + packetNumber + ":").getBytes();
	}

	public int getPacketNumber() {
		return packetNumber;
	}

}
