package com.cr.entity.hero;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cr.crafting.material.base.Copper;
import com.cr.crafting.pattern.KnifePattern;
import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.entity.hero.body.Body;
import com.cr.entity.hero.body.Head;
import com.cr.entity.hero.body.LeftHand;
import com.cr.entity.hero.body.RightHand;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.entity.hero.materials.Materials;
import com.cr.entity.hero.misc.FootPrint;
import com.cr.game.Game;
import com.cr.input.KeyInput;
import com.cr.input.Mouse;
import com.cr.util.Vector2f;

public class Hero extends Mob implements Collideable{
	
	private BufferedImage image;
	private Vector2f targetVel;
	
	private Rectangle rect;

	public static Vector2f position;

	private static Head head;
	private Body body;
	private static RightHand rightHand;
	private static LeftHand leftHand;
	
	private float accSpeed = 3.5f;

	private int printTimer;

	private static Inventory inventory;
	private static Materials materials;
	
	public enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}
	
	public static Direction currentDir;
	
	public Hero() {
		super(position);

		position = new Vector2f(50,50);

		head = new Head();
		body = new Body();
		rightHand = new RightHand();
		leftHand = new LeftHand();
		
		velocity = new Vector2f(0f, 0f);
		targetVel = new Vector2f(0, 0);
		
		width = 88/4 + 52/4;
		height = 28+19;
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		currentDir = Direction.SOUTH;
		
		StatsSheet.cleanseSheet();
		maxHP = StatsSheet.getMaxHP();
		currentHP = maxHP;
		
		inventory = new Inventory();
		materials = new Materials();
	}
	
	private void processInput(){
		if(KeyInput.shift){
			if(KeyInput.up){
				currentDir = Direction.SOUTH;
				targetVel.y = -15f;
			}
			if(KeyInput.down){
				currentDir = Direction.NORTH;
				targetVel.y = 15f;
			}
			if(KeyInput.right){
				currentDir = Direction.WEST;
				targetVel.x = 15f;
			}
			if(KeyInput.left){
				currentDir = Direction.EAST;
				targetVel.x = -15f;
			}
			
			if(KeyInput.up && KeyInput.left){
				currentDir = Direction.SOUTH;
				targetVel.x = -10.6f;
				targetVel.y = -10.6f;
			}
			if(KeyInput.up && KeyInput.right){
				currentDir = Direction.SOUTH;
				targetVel.x = 10.6f;
				targetVel.y = -10.6f;
			}
			if(KeyInput.down && KeyInput.left){
				currentDir = Direction.NORTH;
				targetVel.x = -10.6f;
				targetVel.y = 10.6f;
			}
			if(KeyInput.down && KeyInput.right){
				currentDir = Direction.NORTH;
				targetVel.x = 10.6f;
				targetVel.y = 10.6f;
			}
		}else{
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
		rect.setLocation((int)position.x,(int)position.y);
		processInput();

		maxHP = StatsSheet.getMaxHP();
		addHealth(0);
		
		velocity.x = approach(targetVel.x, velocity.x, dt*accSpeed);
		velocity.y = approach(targetVel.y, velocity.y, dt*accSpeed);
		move(dt);
		
		head.tick(dt);
		body.tick(dt);
		rightHand.tick(dt);
		leftHand.tick(dt);
		
		if(rightHand.getItem() != null && Mouse.getButton() == 1){
			rightHand.getItem().activate();
		}if(leftHand.getItem() != null && Mouse.getButton() == 3){
			leftHand.getItem().activate();
		}
	}

	@Override
	public void render(Graphics2D g) {
//		g.drawImage(image, (int)position.x - cam.getCamX(), (int)position.y - cam.getCamY(), null);
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
				head.render(g);
				body.render(g);
				break;
			case WEST:
				rightHand.render(g);
				body.render(g);
				head.render(g);
				leftHand.render(g);
				break;
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(Game.WIDTH - 100, Game.HEIGHT - 50, 90, 40);
		g.setColor(Color.WHITE);
		String cHPS = String.format("%.1f", currentHP);
		String mHPS = String.format("%.1f", maxHP);
		g.drawString(cHPS + "/" + mHPS, Game.WIDTH - 90, Game.HEIGHT - 25);
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
	
	public static void updateInventory(){
		rightHand.setItem(inventory.getrHSlot().getItem());
		leftHand.setItem(inventory.getlHSlot().getItem());
		head.setItem(inventory.getHeadSlot().getItem());
		
		StatsSheet.cleanseSheet();
		
		if(head.getItem() != null)
			head.getItem().getStats().applyStats();
	}
	
	@Override
	public void collisionWith(Collideable obj){
		
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
	
	public Vector2f getPos(){
		return position;
	}
	
	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}
	
	public Vector2f getTargetVel(){
		return targetVel;
	}
	
	public float getAccSpeed(){
		return accSpeed;
	}

	public static RightHand getRightHand() {
		return rightHand;
	}

	public static LeftHand getLeftHand() {
		return leftHand;
	}

	public static Inventory getInventory() {
		return inventory;
	}

	public static Materials getMaterials() {
		return materials;
	}
	
	


}
