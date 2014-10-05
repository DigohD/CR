package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet20RequestObj extends Packet{
	
	private int type, index;
	
	public Packet20RequestObj(byte[] data) {
	    super(20);
	    
	    String[] dataArray = readData(data).split(":");
	  
	    this.type = Integer.parseInt(dataArray[1]);
	    this.index = Integer.parseInt(dataArray[2]);
	}
	
	public Packet20RequestObj(int type, int index) {
		super(20);
		this.type = type;
		this.index = index;
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
		return ("20" + ":" + type + ":" + index).getBytes();
	}

	public int getIndex() {
		return index;
	}

	public int getType() {
		return type;
	}

}
