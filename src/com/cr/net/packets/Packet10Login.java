package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet10Login extends Packet{
	
	private String userName;

	public Packet10Login(byte[] data) {
		super(10);
	    String[] dataArray = readData(data).split(":");
		this.userName = dataArray[1];
	}
	
	public Packet10Login(String userName) {
		super(10);
		this.userName = userName;
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
		return ("10" + ":" + userName).getBytes();
	}
	

	public String getUserName() {
		return userName;
	}

}
