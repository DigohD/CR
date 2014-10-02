package com.cr.net;

import java.awt.Rectangle;
import java.net.InetAddress;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.body.Head;
import com.cr.entity.hero.body.LeftHand;
import com.cr.entity.hero.body.LowerBody;
import com.cr.entity.hero.body.RightHand;
import com.cr.entity.hero.body.UpperBody;
import com.cr.game.EntityManager;

public class HeroMP extends Entity implements Tickable, Renderable{
	
	private InetAddress ip;
	private int port;
	private String userName;
	private Sprite sprite;
	
	private Head head;
	private UpperBody body;
	private LowerBody lowerBody;
	private RightHand rightHand;
	private LeftHand leftHand;
	
	public HeroMP(String userName, Vector2f position, InetAddress ip, int port){
		super(position);
		this.userName = userName;
		this.ip = ip;
		this.port = port;
		
		head = new Head(position);
		body = new UpperBody(position);
		lowerBody = new LowerBody(position);
		rightHand = new RightHand(position);
		leftHand = new LeftHand(position);
	}
	
	public void init(){
		sprite = new Sprite("mptest");
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		head.tick(dt);
		body.tick(dt);
		lowerBody.tick(dt);
		rightHand.tick(dt);
		leftHand.tick(dt);
	}
	
	@Override
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x, position.y);
		
		lowerBody.render(screen);
		body.render(screen);
		rightHand.render(screen);
		leftHand.render(screen);
		head.render(screen);
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
