package com.cr.net.packets;

import com.cr.engine.core.Vector2f;
import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class ConnectPacket01 extends Packet{

	private String userName;
	private Vector2f pos = new Vector2f(0, 0);

	public ConnectPacket01(byte[] data) {
		super(11);
		String[] dataArray = readData(data).split(":");
		  
		this.userName = dataArray[1];
		this.pos.x = Float.parseFloat(dataArray[2]);
		this.pos.y = Float.parseFloat(dataArray[3]);
	}
	
	public ConnectPacket01(String userName,Vector2f pos) {
		super(11);
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
		return ("11" + ":" + userName + ":" + pos.x + ":" + pos.y).getBytes();
	}
	

	public Vector2f getPos() {
		return pos;
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
