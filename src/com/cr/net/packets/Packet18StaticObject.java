package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet18StaticObject extends Packet{
	
	private int objectID;
	private int x, y;
	private int type;
	private int amount;

	public Packet18StaticObject(byte[] data){
		super(18);
		String[] array = readData(data).split(":");
		this.objectID = Integer.parseInt(array[1]);
		this.x = Integer.parseInt(array[2]);
		this.y = Integer.parseInt(array[3]);
		this.type = Integer.parseInt(array[4]);
		this.amount = Integer.parseInt(array[5]);
	}

	public Packet18StaticObject(int objectID, int x, int y, int type, int amount) {
		super(18);
		this.objectID = objectID;
		this.x = x;
		this.y = y;
		this.type = type;
		this.amount = amount;
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
		
		return ("18" + ":" + objectID + ":" + x + ":" + y + ":" + type + ":" + amount).getBytes();
	}

	public int getObjectID() {
		return objectID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

}
