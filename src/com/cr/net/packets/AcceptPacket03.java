package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class AcceptPacket03 extends Packet{

	private String username;
	private int width, height;

    public AcceptPacket03(byte[] data) {
        super(03);
        String[] dataArray = readData(data).split(":");
        this.username = dataArray[1];
        this.width = Integer.parseInt(dataArray[2]);
        this.height = Integer.parseInt(dataArray[3]);
    }

    public AcceptPacket03(String username, int width, int height) {
        super(03);
        this.username = username;
        this.width = width;
        this.height = height;
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
        return ("03" + ":" + this.username + ":" + width + ":" + height).getBytes();
    }

    public String getUsername() {
        return username;
    }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
