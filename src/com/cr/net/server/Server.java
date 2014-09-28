package com.cr.net.server;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Server {
	
	private Thread receiveThread, sendThread;
	private InetAddress ip;
	private DatagramSocket socket;
	private int port;
	
	public Server(InetAddress ip, int port){
		this.ip = ip;
		this.port = port;
		
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	

}
