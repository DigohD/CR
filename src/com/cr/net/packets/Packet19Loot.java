package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet19Loot extends Packet{
	
	private int x, y;
	private int type, amount;

	public Packet19Loot(byte[] data){
		super(19);
		String[] array = readData(data).split(":");
		this.x = Integer.parseInt(array[1]);
		this.y = Integer.parseInt(array[2]);
		this.type = Integer.parseInt(array[3]);
		this.amount = Integer.parseInt(array[4]);
	}

	public Packet19Loot(int packetID, int x, int y, int type, int amount) {
		super(19);
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
		return ("19" + ":" + x + ":" + y + ":" + type + ":" + amount).getBytes();
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
		return type;
	}

}
