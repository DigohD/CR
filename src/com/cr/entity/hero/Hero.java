package com.cr.entity.hero;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Mob;
import com.cr.entity.hero.body.Body;
import com.cr.entity.hero.body.Head;
import com.cr.gameEngine.Game;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;
import com.cr.util.Vector2f;

public class Hero extends Mob{
	
	private BufferedImage image;
	private Vector2f targetVel;
	
	public static Vector2f position;

	private Head head;
	private Body body;
	
	public enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}
	
	public static Direction currentDir;
	
	public Hero() {
		super(position);
		position = new Vector2f(100, 100);

		head = new Head();
		body = new Body();
		
		velocity = new Vector2f(0f, 0f);
		targetVel = new Vector2f(0, 0);
		image = ImageLoader.getImage("hero");
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

		velocity.x = approach(targetVel.x, velocity.x, dt*10f);
		velocity.y = approach(targetVel.y, velocity.y, dt*10f);
		move(dt);
		
		head.tick(dt);
		body.tick(dt);
	}

	@Override
	public void render(Graphics2D g) {
//		g.drawImage(image, (int)position.x, (int)position.y, null);
		switch(currentDir){
			case SOUTH:
				body.render(g);
				head.render(g);
				break;
			case EAST:
				body.render(g);
				head.render(g);
				break;
			case NORTH:
				head.render(g);
				body.render(g);
				break;
			case WEST:
				body.render(g);
				head.render(g);
				break;
		}
	}
	
	@Override
	protected void move(float dt){
		position = position.add(velocity.mul(dt));
		
		if(velocity.length() == 0)
			setBobing(false);
		else
			setBobing(true);
	}

	private void setBobing(boolean isBobing){
		head.getBob().setActive(isBobing);
		body.getBob().setActive(isBobing);
	}
	
	@Override
	public BufferedImage getImage() {
		return image;
	}

}
