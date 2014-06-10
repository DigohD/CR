package com.cr.entity;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.world.World;
import com.cr.world.tile.Tile;

public abstract class Mob extends Entity implements Tickable, Renderable{
	
	protected World world;

	protected float speedX, speedY;
	protected Vector2f velocity;
	protected Vector2f distance;
	protected Sprite sprite;
	
	protected Transform transform;
	
	public enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}
	
	protected Direction currentDir;
	protected float maxHP, currentHP;
	
	protected boolean moving = false;
	
	public Mob(Vector2f position, World world) {
		super(position);
		this.world = world;
		transform = new Transform();
		distance = new Vector2f(0,0);

	}

	protected float approachTarget(float target, float current, float dt){
		float diff = target - current;
		if(diff > dt)
			return current + dt;
		if(diff < -dt)
			return current - dt;
		return target;
	}
	
	protected boolean collisionWithTile(float x, float y){
		int nextX = (int)position.x  + (int)x;
		int nextY = (int)position.y  + (int)y;
		
		for(int i = 0; i < 4; i++){
			int xPos =  (nextX + i % 2 * 12) / (Tile.getTileWidth());
			int yPos =  (nextY + i / 2 * 12 + 32) / (Tile.getTileHeight());
			if(world.tileExists(xPos, yPos)){
				if(!world.getTile(xPos, yPos).isWalkable()){
					return true;
				}
			}
		}
		
		return false;
	}

	protected void move(float dt){
		distance = velocity.mul(dt);
		position = position.add(distance);
	}
	
	@Override
	public void tick(float dt){
		
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x , position.y);
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
	@Override
	public Sprite getSprite(){
		return sprite;
	}

	public Direction getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(Direction currentDir) {
		this.currentDir = currentDir;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void addHealth(float amount){
		currentHP = currentHP + amount;
		if(currentHP > maxHP)
			currentHP = maxHP;
	}

}
