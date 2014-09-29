package com.cr.entity.hero;

import java.net.InetAddress;

import com.cr.engine.core.Vector2f;
import com.cr.world.World;

public class HeroMP extends Hero{
	
	private InetAddress ip;
	private int port;
	private String userName;

	public HeroMP(World world, String userName, InetAddress ip, int port) {
		super(world);
		this.position = new Vector2f(100, 100);
		this.userName = userName;
		this.ip = ip;
		this.port = port;
	}
	
	

	public InetAddress getInetAddress() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getUserName() {
		return userName;
	}

}
