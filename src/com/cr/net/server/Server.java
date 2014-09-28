package com.cr.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server implements Runnable{
	
	private DatagramSocket socket;
	private Thread thread;
	
	public Server(){
		
		try {
			socket = new DatagramSocket(4678);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		thread = new Thread(this, "server-thread");
		thread.start();
		
	}

	@Override
	public void run() {
		
		while(true){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String message = new String(packet.getData());
			System.out.println("Client: " + message);
			if(message.equalsIgnoreCase("ping"))
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
		}
		
	}
	
	public void sendData(byte[] data, InetAddress ip, int port){
		
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
