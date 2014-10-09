package com.cr.entity;

import java.util.ArrayList;

import com.cr.combat.Damage;
import com.cr.combat.DamagePacket;
import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.enemy.EnemySheet;
import com.cr.entity.hero.Hero.Direction;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.stats.Stat;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;
import com.cr.world.World;
import com.cr.world.tile.Tile;

public abstract class Mob extends Entity implements Tickable, Renderable{
	
	protected World world;

	protected float speedX, speedY;
	protected Vector2f velocity;
	protected Vector2f distance;
	protected Sprite sprite;
	
	protected StatsSheet sheet;
	
	protected Transform transform;
	
	protected Direction currentDir;
	
	protected float accSpeed = 3.5f;
	protected float speed = 15f;
	
	protected boolean moving = false;
	
	public Mob(Vector2f position, World world) {
		super(position);
		this.world = world;
		transform = new Transform();
		distance = new Vector2f(0,0);
		velocity = new Vector2f(0, 0);
	}

	protected float approachTarget(float target, float current, float dt){
		float diff = target - current;
		if(diff > dt)
			return current + dt;
		if(diff < -dt)
			return current - dt;
		return target;
	}

	protected void move(float dt){
		distance = velocity.mul(dt * sheet.getStat(StatID.MOVEMENT_SPEED).getTotal());
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
	
//	public void addHealth(float amount){
//		currentHP = currentHP + amount;
//		if(currentHP > maxHP)
//			currentHP = maxHP;
//	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void takeDamage(float damage){
		playHurtSound();
		
		Stat hpNow = sheet.getStat(StatID.HP_NOW);
		hpNow.setNewBase(hpNow.getTotal() - damage);
		
		if(hpNow.getTotal() < 0)
			death();
	}
	
//	public float getCurrentHP() {
//		return currentHP;
//	}
	
	public Vector2f getCenterPos(){
//		System.out.println(position);
		
		float x = position.x + (width / 2);
		float y = position.y + (height / 2);
		
		return new Vector2f(x, y);
	}
	
	public abstract void death();
	public abstract void push(Vector2f distance);
	public abstract void playHurtSound();

	public StatsSheet getSheet() {
		return sheet;
	}
	
	
	
}
