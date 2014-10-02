package com.cr.net;

import java.awt.Rectangle;
import java.net.InetAddress;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.game.EntityManager;

public class HeroMP extends Entity implements Tickable, Renderable{
	
	private InetAddress ip;
	private int port;
	private String userName;
	private Sprite sprite;
	
	public HeroMP(String userName, Vector2f position, InetAddress ip, int port){
		super(position);
		this.userName = userName;
		this.ip = ip;
		this.port = port;
	}
	
	public void init(){
		sprite = new Sprite("mptest");
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
	
	}
	
	@Override
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x, position.y);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle((int) position.x, (int) position.y, sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}

	public String getUserName() {
		return userName;
	}

	public InetAddress getInetAddress() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	
	
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}
	

}
