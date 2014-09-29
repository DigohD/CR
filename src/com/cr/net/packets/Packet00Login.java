package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet00Login extends Packet{
	
	private String userName;

	public Packet00Login(byte[] data) {
		super(00);
		this.userName = readData(data);
	}
	
	public Packet00Login(String userName) {
		super(00);
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
		return ("00" + this.userName).getBytes();
	}

	public String getUserName() {
		return userName;
	}

}
