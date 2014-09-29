package com.cr.net.packets;

import com.cr.engine.core.Vector2f;
import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class MovePacket02 extends Packet{

	private Vector2f pos;
	private String userName;
	
	public MovePacket02(byte[] data) {
	    super(02);
	    
	    String[] dataArray = readData(data).split(":");
	    
	    this.userName = dataArray[1];
	    this.pos.x = Integer.parseInt(dataArray[2]);
	    this.pos.y = Integer.parseInt(dataArray[3]);
	}

	public MovePacket02(String userName, Vector2f pos) {
		super(02);
		
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
