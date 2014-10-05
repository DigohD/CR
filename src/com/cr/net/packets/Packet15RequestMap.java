package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet15RequestMap extends Packet{
	
	private int packetNumber;
	
	public Packet15RequestMap(byte[] data) {
	    super(15);
	    
	    
	    String[] dataArray = readData(data).split(":");
	  
	    this.packetNumber = Integer.parseInt(dataArray[1]);
	}
	
	public Packet15RequestMap(int packetNumber) {
		super(15);
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
		return ("15" + ":" + packetNumber).getBytes();
	}

	public int getPacketNumber() {
		return packetNumber;
	}


}
