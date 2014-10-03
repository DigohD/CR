package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet17Stat extends Packet{
	
	private String userName;
	private String statID;
	private float value;
	
	public Packet17Stat(byte[] data){
		super(17);
		String[] dataArray = readData(data).split(":");
		this.userName = dataArray[1];
		this.statID = dataArray[2];
	    this.value = Float.parseFloat(dataArray[3]);
	}

	public Packet17Stat(String userName, String statID, float value) {
		super(17);
		this.userName = userName;
		this.statID = statID;
		this.value = value;
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
		return ("17" + ":" + userName + ":" + statID + ":" + value).getBytes();
	}

	public String getStatID() {
		return statID;
	}

	public float getValue() {
		return value;
	}

	public String getUserName() {
		return userName;
	}

}
