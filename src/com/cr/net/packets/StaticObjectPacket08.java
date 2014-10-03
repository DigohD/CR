package com.cr.net.packets;

import com.cr.net.client.Client;
import com.cr.net.server.Server;

public class StaticObjectPacket08 extends Packet{
	
	private int objectID;
	private int x, y;
	private int type;

	public StaticObjectPacket08(byte[] data){
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
