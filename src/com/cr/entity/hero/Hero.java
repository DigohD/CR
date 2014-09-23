package com.cr.entity.hero;

import java.awt.Rectangle;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.input.Input;
import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.entity.hero.body.Body;
import com.cr.entity.hero.body.Head;
import com.cr.entity.hero.body.LeftHand;
import com.cr.entity.hero.body.RightHand;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.entity.hero.materials.MaterialsBox;
import com.cr.entity.hero.misc.FootPrint;
import com.cr.util.Randomizer;
import com.cr.world.World;
import com.cr.world.tile.Tile;

public class Hero extends Mob implements Collideable{
	
	private Sprite sprite;
	private Vector2f targetVel;
	
	private Rectangle rect;

	public static Vector2f position;

	private int stepCounter;
	private static Head head;
	private Body body;
	private static RightHand rightHand;
	private static LeftHand leftHand;

	private int printTimer;

	private static Inventory inventory;
	private static MaterialsBox materialsBox;
	
	public enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}
	
	public static Direction currentDir;
	
	public static Transform t;
	
	HeroInput input;
	
	public Hero(World world) {
		super(position, world);
		input = new HeroInput(this);
		t = new Transform();
		position = new Vector2f((world.getWidth() * Tile.getTileWidth()) / 2 , (world.getHeight() * Tile.getTileHeight()) / 2);
	
		if(world.tileExists((int) (position.x / Tile.getTileWidth()), (int) (position.y / Tile.getTileHeight()))){
			while(!world.getTile((int) (position.x / Tile.getTileWidth()), (int) (position.y / Tile.getTileHeight())).isWalkable()){
				position.y += Tile.getTileHeight();
				if(!world.tileExists((int) (position.x / Tile.getTileWidth()), (int) (position.y / Tile.getTileHeight()))){
					break;
				}
			}
		}
				
		
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
		
		HeroSheet.cleanseSheet();
		maxHP = HeroSheet.getMaxHP();
		currentHP = 1;
		
		inventory = new Inventory();
		materialsBox = new MaterialsBox();
	}
	
	protected boolean collisionWithTile(float x, float y){
		int xOffset = 4;
		int yOffset = 25;
		int nextX = (int)position.x  + (int)x;
		int nextY = (int)position.y  + (int)y - yOffset;
		
		for(int i = 0; i < 4; i++){
			int xPos =  (nextX + i % 2 * 10) / (Tile.getTileWidth());
			int yPos =  (nextY + i / 2 * 10 + 32) / (Tile.getTileHeight());
			if(world.tileExists(xPos, yPos)){
				if(!world.getTile(xPos, yPos).isWalkable()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public void tick(float dt) {
		rect.setLocation((int)position.x,(int)position.y);
		
		input.input();

		velocity.x = approachTarget(targetVel.x, velocity.x, dt*accSpeed);
		velocity.y = approachTarget(targetVel.y, velocity.y, dt*accSpeed);
		
		if(!collisionWithTile(targetVel.x, 0)){
			position.x = position.x + targetVel.x*dt;
		}
		
		if(!collisionWithTile(0, targetVel.y)){
			position.y = position.y + targetVel.y*dt;
		}

		maxHP = HeroSheet.getMaxHP();
		addHealth(0);
		
		move(dt);
		
		head.tick(dt);
		body.tick(dt);
		rightHand.tick(dt);
		leftHand.tick(dt);
		
		if(rightHand.getItem() != null && Input.getMouse(0)){
			rightHand.getItem().activate();
		}if(leftHand.getItem() != null && Input.getMouse(1)){
			leftHand.getItem().activate();
		}
	}

	@Override
	public void render(Screen screen) {
//		g.drawImage(image, (int)position.x - cam.getCamX(), (int)position.y - cam.getCamY(), null);
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
		
//		g.setColor(Color.BLACK);
//		g.fillRect(Game.WIDTH - 100, Game.HEIGHT - 50, 90, 40);
//		g.setColor(Color.WHITE);
//		String cHPS = String.format("%.1f", currentHP);
//		String mHPS = String.format("%.1f", maxHP);
//		g.drawString(cHPS + "/" + mHPS, Game.WIDTH - 90, Game.HEIGHT - 25);
	}
	
	@Override
	protected void move(float dt){
		
		
		if(printTimer++ > 3){
			//new FootPrint();
			printTimer = 0;
		}
		
		if(velocity.length() == 0)
			setBobing(false);
		else{
			setBobing(true);
			stepCounter++;
			if(stepCounter > 18){
				stepCounter = 0;
			}
		}
	}
	
	public static void updateInventory(){
		rightHand.setItem(inventory.getrHSlot().getItem());
		leftHand.setItem(inventory.getlHSlot().getItem());
		head.setItem(inventory.getHeadSlot().getItem());
		
		HeroSheet.cleanseSheet();
		
		if(head.getItem() != null)
			head.getItem().getStats().applyStats();
	}
	
	@Override
	public void death() {
		
	}
	
	@Override
	public Vector2f getCenterPos(){
		float x = position.x + (width / 2);
		float y = position.y + (height / 2);
		
		return new Vector2f(x, y);
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
	public Sprite getSprite() {
		return sprite;
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

	public float getSpeed() {
		return speed;
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

	public static void setCurrentDir(Direction currentDir) {
		Hero.currentDir = currentDir;
	}

	@Override
	public void push(Vector2f distance) {
		position = new Vector2f(position.x + distance.x, position.y + distance.y);
	}

	@Override
	public void playHurtSound() {
		
	}

	
	
	


}
