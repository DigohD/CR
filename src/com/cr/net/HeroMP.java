package com.cr.net;

import java.awt.Rectangle;
import java.net.InetAddress;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero.Direction;
import com.cr.entity.hero.body.Head;
import com.cr.entity.hero.body.LeftHand;
import com.cr.entity.hero.body.LowerBody;
import com.cr.entity.hero.body.RightHand;
import com.cr.entity.hero.body.UpperBody;
import com.cr.game.EntityManager;
import com.cr.stats.StatsSheet;
import com.cr.util.CRString;
import com.cr.util.Camera;
import com.cr.util.FontLoader;

public class HeroMP extends Entity implements Tickable, Renderable{
	
	private InetAddress ip;
	private int port;
	private String userName;
	private Sprite sprite;
	
	private Transform t = new Transform();
	
	private Direction dir;
	
	private Head head;
	private UpperBody body;
	private LowerBody lowerBody;
	private RightHand rightHand;
	private LeftHand leftHand;
	
	private StatsSheet sheet;
	
	public HeroMP(Vector2f position){
		super(position);
	}
	
	public HeroMP(String userName, Vector2f position, InetAddress ip, int port){
		super(position);
		this.userName = userName;
		this.ip = ip;
		this.port = port;
		sheet = new StatsSheet(true);
	}
	
	public void init(){
		sprite = new Sprite("mptest");
		
		head = new Head(position, t);
		body = new UpperBody(position, t);
		lowerBody = new LowerBody(position, t);
		rightHand = new RightHand(position, t);
		leftHand = new LeftHand(position, t);
		
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
		if(head != null && this.position.sub(position).length() == 0)
			setBobing(false);
		else if(head != null)
			setBobing(true);
		
		this.position = position;
	}
	
	public void setDirection(Direction dir){
		if(lowerBody != null)
			lowerBody.setDir(dir);
		if(body != null)
			body.setDir(dir);
		if(rightHand != null)
			rightHand.setDir(dir);
		if(leftHand != null)
			leftHand.setDir(dir);
		if(head != null)
			head.setDir(dir);
	}
	
	@Override
	public void render(Screen screen) {
		Font f = FontLoader.aquireFont(FontColor.WHITE);
		f.setFont(CRString.create(userName));
		int length = userName.length();
		f.renderFont(position.x - (length * 5) - Camera.getCamX(), position.y - 70 - Camera.getCamY(), 0.25f);
		
		lowerBody.setPos(position);
		body.setPos(position);
		rightHand.setPos(position);
		leftHand.setPos(position);
		head.setPos(position);
		
		lowerBody.render(screen);
		body.render(screen);
		rightHand.render(screen);
		leftHand.render(screen);
		head.render(screen);
		
		FontLoader.releaseFont(f);
	}

	private void setBobing(boolean isBobing){
		head.getBob().setActive(isBobing);
		lowerBody.getBob().setActive(isBobing);
		body.getBob().setActive(isBobing);
		rightHand.getBob().setActive(isBobing);
		leftHand.getBob().setActive(isBobing);
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

	public StatsSheet getSheet() {
		return sheet;
	}
	

}
