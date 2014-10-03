package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class StatPacket07 extends Packet{
	
	private String userName;
	private String statID;
	private float value;
	
	public StatPacket07(byte[] data){
		super(07);
		String[] dataArray = readData(data).split(":");
		this.userName = dataArray[1];
		this.statID = dataArray[2];
	    this.value = Float.parseFloat(dataArray[3]);
	}

	public StatPacket07(String userName, String statID, float value) {
		super(07);
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
		return ("07" + ":" + userName + ":" + statID + ":" + value).getBytes();
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
