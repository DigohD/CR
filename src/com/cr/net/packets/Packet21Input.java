package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet21Input extends Packet{
	
	private String name;
	private int keyCode;
	private int pressed;
	
	public Packet21Input(byte[] data){
		super(21);
		
		String dataArray[] = readData(data).split(":");
		
		this.name = dataArray[1];
		this.keyCode = Integer.parseInt(dataArray[2]);
		this.pressed = Integer.parseInt(dataArray[3]);
	}

	public Packet21Input(int packetID, String name, int keyCode, int pressed) {
		super(21);
		this.name = name;
		this.keyCode = keyCode;
		this.pressed = pressed;
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
		return ("21" + ":" + name + ":" + keyCode + ":" + pressed + ":").getBytes();
	}

	public String getName() {
		return name;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int isPressed() {
		return pressed;
	}

}
