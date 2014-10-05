package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet13Accept extends Packet{

	private String username;
	private int width, height;
	private float x, y;

    public Packet13Accept(byte[] data) {
        super(13);
        String[] dataArray = readData(data).split(":");
        this.username = dataArray[1];
        this.width = Integer.parseInt(dataArray[2]);
        this.height = Integer.parseInt(dataArray[3]);
        this.x = Float.parseFloat(dataArray[4]);
        this.y = Float.parseFloat(dataArray[5]);
    }

    public Packet13Accept(String username, int width, int height, float x, float y) {
        super(13);
        this.username = username;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
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
        return ("13" + ":" + this.username + ":" + width + ":" + height + ":" + x + ":" + y).getBytes();
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

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
