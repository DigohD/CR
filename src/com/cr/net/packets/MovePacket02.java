package com.cr.net.packets;

import com.cr.engine.core.Vector2f;
import com.cr.entity.hero.Hero.Direction;
import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class MovePacket02 extends Packet{

	private Vector2f pos = new Vector2f(0, 0);
	private String userName;
	private Direction dir;
	
	public MovePacket02(byte[] data) {
	    super(12);
	    
	    String[] dataArray = readData(data).split(":");
	  
	    this.userName = dataArray[1];
	    this.pos.x = Float.parseFloat(dataArray[2]);
	    this.pos.y = Float.parseFloat(dataArray[3]);
	    this.dir = Direction.valueOf(dataArray[4]);
	}

	public MovePacket02(String userName, Vector2f pos, Direction dir) {
		super(12);
		
		this.userName = userName;
		this.pos = pos;
		this.dir = dir;
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
		return ("12" + ":" + userName + ":" + pos.x + ":" + pos.y + ":" + dir.name()).getBytes();
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public Direction getDir() {
		return dir;
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
