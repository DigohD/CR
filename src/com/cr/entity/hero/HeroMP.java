package com.cr.entity.hero;

import java.net.InetAddress;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.hero.Hero.Direction;
import com.cr.entity.hero.body.Body;
import com.cr.entity.hero.body.Head;
import com.cr.entity.hero.body.LeftHand;
import com.cr.entity.hero.body.RightHand;
import com.cr.world.World;

public class HeroMP{
	
	private InetAddress ip;
	private int port;
	private String userName;
	private Vector2f position;
	
	private Head head;
	private Body body;
	private RightHand rightHand;
	

	private LeftHand leftHand;
	
	public enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}
	
	public static Direction currentDir;
	

	public HeroMP(Vector2f position, String userName, InetAddress ip, int port) {
		this.position = position;
		this.userName = userName;
		this.ip = ip;
		this.port = port;
		
		head = new Head();
		body = new Body();
		rightHand = new RightHand();
		leftHand = new LeftHand();
	}
	
	public void tick(float dt){
		head.tick(dt);
		body.tick(dt);
		rightHand.tick(dt);
		leftHand.tick(dt);
	}
	
	public void render(Screen screen){
		switch(currentDir){
		case SOUTH:
			body.render(screen);
			rightHand.render(screen);
			leftHand.render(screen);
			head.render(screen);
			break;
		case EAST:
			leftHand.render(screen);
			body.render(screen);
			head.render(screen);
			rightHand.render(screen);
			break;
		case NORTH:
			head.render(screen);
			rightHand.render(screen);
			leftHand.render(screen);
			head.render(screen);
			body.render(screen);
			break;
		case WEST:
			rightHand.render(screen);
			body.render(screen);
			head.render(screen);
			leftHand.render(screen);
			break;
		}
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

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getPosition() {
		return position;
	}

}
