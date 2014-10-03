package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class RequestMapPacket05 extends Packet{
	
	private int packetNumber;
	
	public RequestMapPacket05(byte[] data) {
	    super(15);
	    
	    
	    String[] dataArray = readData(data).split(":");
	  
	    this.packetNumber = Integer.parseInt(dataArray[1]);
	}
	
	public RequestMapPacket05(int packetNumber) {
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
