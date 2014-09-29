package com.cr.net.packets;

import com.cr.engine.core.Vector2f;
import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class LoginPacket00 extends Packet{
	
	private Vector2f pos;
	private String userName;

	public LoginPacket00(byte[] data) {
		super(00);
		this.userName = readData(data);
	}
	
	public LoginPacket00(String userName, Vector2f pos) {
		super(00);
		this.userName = userName;
		this.pos = pos;
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
		return ("02" + ":" + userName + ":" + pos.x + ":" + pos.y).getBytes();
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}

	public String getUserName() {
		return userName;
	}

}
