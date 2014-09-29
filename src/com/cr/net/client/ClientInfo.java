package com.cr.net.client;

import java.net.InetAddress;

public class ClientInfo{
	
	private InetAddress ip;
	private int port;
	private String userName;
	
	public ClientInfo(String userName, InetAddress ip, int port) {
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
	
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	

}
