package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class AcceptPacket03 extends Packet{

	private String username;

    public AcceptPacket03(byte[] data) {
        super(03);
        String[] dataArray = readData(data).split(":");
        this.username = dataArray[1];
    }

    public AcceptPacket03(String username) {
        super(03);
        this.username = username;
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
        return ("03" + ":" + this.username).getBytes();
    }

    public String getUsername() {
        return username;
    }

}
