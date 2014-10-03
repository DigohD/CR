package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class Packet18StaticObject extends Packet{
	
	private int objectID;
	private int x, y;
	private int type;

	public Packet18StaticObject(byte[] data){
		super(18);
		
	}

	@Override
	public void writeData(Client client) {
		
		
	}

	@Override
	public void writeData(Server server) {
		
		
	}

	@Override
	public byte[] getData() {
		
		return null;
	}

}
