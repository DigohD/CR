package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class StatPacket07 extends Packet{
	
	private String userName;
	private String statID;
	private float value;
	
	public StatPacket07(byte[] data){
		super(07);
		//String[] dataArray = 
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
		
		return "07".getBytes();
	}

	public String getStatID() {
		return statID;
	}

	public float getValue() {
		return value;
	}

	public String getUserName() {
		return userName;
	}

}
