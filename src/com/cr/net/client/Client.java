package com.cr.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client implements Runnable{
	

	
	private InetAddress ip;
	private DatagramSocket socket;
	private Thread thread;
	
	public Client(String ip){
	
		
		try {
			socket = new DatagramSocket(4678);
			this.ip = InetAddress.getByName(ip);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		thread = new Thread(this, "client-thread");
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
			
			System.out.println("Server: " + new String(packet.getData()));
			
		}
		
	}
	
	public void sendData(byte[] data){
		
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, 4678);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}

