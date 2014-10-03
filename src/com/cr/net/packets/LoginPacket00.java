package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class LoginPacket00 extends Packet{
	
	private String userName;

	public LoginPacket00(byte[] data) {
		super(10);
		this.userName = readData(data);
	}
	
	public LoginPacket00(String userName) {
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
