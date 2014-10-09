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
import com.cr.entity.Mob;
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
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.CRString;
import com.cr.util.Camera;
import com.cr.util.FontLoader;
import com.cr.util.SpriteLoader;
import com.cr.world.World;

public class HeroMPServer extends Mob implements Tickable, Renderable{
	
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
	
	private HeroMPInput input;
	
	private Vector2f targetVel;
	
	public HeroMPServer(Vector2f position, World world){
		super(position, world);
	}
	
	public HeroMPServer(String userName, Vector2f position, InetAddress ip, int port, World world){
		super(position, world);
		this.userName = userName;
		this.ip = ip;
		this.port = port;
		if(NetStatus.isMultiPlayer && NetStatus.isHOST)
			sheet = new StatsSheet(true);
		
		input = new HeroMPInput(this);
		
		targetVel = new Vector2f(0, 0);
	}
	
	public void init(){
		sprite = SpriteLoader.getSprite("mptest");
		
		head = new Head(position, t);
		body = new UpperBody(position, t);
		lowerBody = new LowerBody(position, t);
		rightHand = new RightHand(position, t);
		leftHand = new LeftHand(position, t);
		
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt){
		velocity.x = approachTarget(targetVel.x, velocity.x, dt*accSpeed);
		velocity.y = approachTarget(targetVel.y, velocity.y, dt*accSpeed);
		
		//if(!collisionWithTile(targetVel.x, 0))
			position.x = position.x + targetVel.x*dt;
	
		//if(!collisionWithTile(0, targetVel.y))
			position.y = position.y + targetVel.y*dt;
		
		move(dt);
		
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
		//System.out.println("LOWER BODY: " + lowerBody + " , GETBOB: " + lowerBody.getBob());
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
	
	public float getSpeed() {
		return speed * sheet.getStat(StatID.MOVEMENT_SPEED).getTotal();
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

	public Vector2f getTargetVel() {
		return targetVel;
	}
	
	public HeroMPInput getInput() {
		return input;
	}

	@Override
	public void death() {
		
	}

	@Override
	public void push(Vector2f distance) {
		
	}

	@Override
	public void playHurtSound() {
		
	}
	
	
	

}
