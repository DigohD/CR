package com.cr.entity.hero;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Mob;
import com.cr.entity.hero.body.Body;
import com.cr.entity.hero.body.Head;
import com.cr.entity.hero.body.LeftHand;
import com.cr.entity.hero.body.RightHand;
import com.cr.entity.hero.misc.FootPrint;
import com.cr.gameEngine.Game;
import com.cr.input.KeyInput;
import com.cr.util.Vector2f;
import com.cr.world.World;

public class Hero extends Mob{
	
	private BufferedImage image;
	private Vector2f targetVel;
	
	private World w;
	
	public Vector2f getTargetVel(){
		return targetVel;
	}
	
	public static Vector2f position;

	private Head head;
	private Body body;
	private RightHand rightHand;
	private LeftHand leftHand;
	
	private float accSpeed = 3.5f;
	

	public float getAccSpeed(){
		return accSpeed;
	}

	private int printTimer;

	
	public enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}
	
	public static Direction currentDir;
	
	public Hero(World w) {
		super(position);
		this.w = w;
		position = new Vector2f(Game.WIDTH/2, Game.HEIGHT/2);

		head = new Head();
		body = new Body();
		rightHand = new RightHand();
		leftHand = new LeftHand();
		
		velocity = new Vector2f(0f, 0f);
		targetVel = new Vector2f(0, 0);
		
		width = 88/4 + 52/4;
		height = 28+19;
		currentDir = Direction.SOUTH;
	}
	
	private void processInput(){
		if(KeyInput.up){
			currentDir = Direction.NORTH;
			targetVel.y = -15f;
		}
		if(KeyInput.down){
			currentDir = Direction.SOUTH;
			targetVel.y = 15f;
		}
		if(KeyInput.right){
			currentDir = Direction.EAST;
			targetVel.x = 15f;
		}
		if(KeyInput.left){
			currentDir = Direction.WEST;
			targetVel.x = -15f;
		}
		
		if(KeyInput.up && KeyInput.left){
			currentDir = Direction.NORTH;
			targetVel.x = -10.6f;
			targetVel.y = -10.6f;
		}
		if(KeyInput.up && KeyInput.right){
			currentDir = Direction.NORTH;
			targetVel.x = 10.6f;
			targetVel.y = -10.6f;
		}
		if(KeyInput.down && KeyInput.left){
			currentDir = Direction.SOUTH;
			targetVel.x = -10.6f;
			targetVel.y = 10.6f;
		}
		if(KeyInput.down && KeyInput.right){
			currentDir = Direction.SOUTH;
			targetVel.x = 10.6f;
			targetVel.y = 10.6f;
		}
		
		if(!KeyInput.up && !KeyInput.down){
			targetVel.y = 0f;
		}
		if(!KeyInput.right && !KeyInput.left){
			targetVel.x = 0f;
		}
		
	}
	
	@Override
	public void tick(float dt) {
		processInput();

		velocity.x = approach(targetVel.x, velocity.x, dt*accSpeed);
		velocity.y = approach(targetVel.y, velocity.y, dt*accSpeed);
		move(dt);
		
		head.tick(dt);
		body.tick(dt);
		rightHand.tick(dt);
		leftHand.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
//		g.drawImage(image, (int)position.x, (int)position.y, null);
		switch(currentDir){
			case SOUTH:
				body.render(g);
				rightHand.render(g);
				leftHand.render(g);
				head.render(g);
				break;
			case EAST:
				leftHand.render(g);
				body.render(g);
				head.render(g);
				rightHand.render(g);
				break;
			case NORTH:
				head.render(g);
				rightHand.render(g);
				leftHand.render(g);
				body.render(g);
				break;
			case WEST:
				rightHand.render(g);
				body.render(g);
				head.render(g);
				leftHand.render(g);
				break;
		}
	}
	
	@Override
	protected void move(float dt){
		position = position.add(velocity.mul(dt));
		
		if(printTimer++ > 3){
			new FootPrint();
			printTimer = 0;
		}
		
		if(velocity.length() == 0)
			setBobing(false);
		else
			setBobing(true);
	}

	private void setBobing(boolean isBobing){
		head.getBob().setActive(isBobing);
		body.getBob().setActive(isBobing);
		rightHand.getBob().setActive(isBobing);
		leftHand.getBob().setActive(isBobing);
	}
	
	@Override
	public BufferedImage getImage() {
		return image;
	}
	
	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

}
