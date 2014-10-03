package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class DisconnectPacket06 extends Packet{
	
	private String userName;
	
	public DisconnectPacket06(byte[] data) {
		super(16);
		
		String[] dataArray = readData(data).split(":");
		
		this.userName = dataArray[1];
	}

	public DisconnectPacket06(String userName) {
		super(16);
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
		return ("16" + ":" + userName).getBytes();
	}

	public String getUserName() {
		return userName;
	}

}
