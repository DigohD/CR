package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class RequestMapPacket05 extends Packet{
	
	private int packetNumber;
	
	public RequestMapPacket05(byte[] data) {
	    super(05);
	    
	    String[] dataArray = readData(data).split(":");
	  
	    this.packetNumber = Integer.parseInt(dataArray[2]);
	}
	
	public RequestMapPacket05(int packetNumber) {
		super(05);
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
		return ("05" + ":" + packetID).getBytes();
	}

	public int getPacketNumber() {
		return packetNumber;
	}


}
