package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet14Map extends Packet{

	private int packetNumber;
	
	public Packet14Map(byte[] data) {
	    super(14);
	    
	    String[] dataArray = readData(data).split(":");
	  
	    this.packetNumber = Integer.parseInt(dataArray[1]);
	}
	
	public Packet14Map(int packetNumber) {
		super(14);
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
		return ("14" + ":" + packetNumber + ":").getBytes();
	}

	public int getPacketNumber() {
		return packetNumber;
	}

}
