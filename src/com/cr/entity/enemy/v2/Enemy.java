package com.cr.entity.enemy.v2;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.entity.MotionEntity;
import com.cr.entity.enemy.v2.behaviour.Aggressive;
import com.cr.entity.enemy.v2.behaviour.Behaviour;
import com.cr.entity.enemy.v2.motion.AniMotion;
import com.cr.entity.enemy.v2.motion.AniMotionSet;
import com.cr.entity.enemy.v2.motion.AniMotionSet.MotionStatus;
import com.cr.entity.enemy.v2.motion.Motion;
import com.cr.entity.enemy.v2.motion.SinusMotion;
import com.cr.entity.enemy.v2.motion.SinusMotion.MotionAxis;
import com.cr.game.EntityManager;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.enemy.AniMotions;
import com.cr.util.enemy.EnemyLogic;
import com.cr.world.World;

public class Enemy extends Mob implements Collideable{
	
	protected String name;
	
	protected Rectangle hitBox;
	protected Sprite body;
	
	protected ArrayList<Limb> limbs = new ArrayList<Limb>();
	protected Vector2f centerOffset, renderPosition, moveDirection;
	
	protected AniMotionSet motionSet = new AniMotionSet();
	
	protected Behaviour behaviour;
	
	protected boolean isMoving;
	
	public Enemy(Vector2f position, World world, Rectangle hitBox, Sprite body, String name, StatsSheet sheet) {
		super(position, world);
		this.hitBox = hitBox;
		this.body = body;
		this.name = name;
		this.sheet = sheet;
		
		centerOffset = new Vector2f(body.getSpriteWidth() / 2, body.getSpriteHeight() / 2);
		moveDirection = new Vector2f(0, 0);
		velocity = new Vector2f(0, 0);
		renderPosition = new Vector2f(0, 0);
		
		if(hitBox == null)
			generateHitBox();
	}
	
	@Override
	public void tick(float dt){
		for(Limb x : limbs)
			x.tick(dt);
		
		hitBox.x = (int) position.x;
		hitBox.y = (int) position.y;
		
		behaviour.tick(dt);
		
		motionSet.getActiveMotion().tick(dt);
		if(isMoving){
			motionSet.setActiveMotion(MotionStatus.MOVING);
			move(dt);
		}else
			motionSet.setActiveMotion(MotionStatus.IDLE);
	}
	
	@Override
	protected void move(float dt){
		distance = velocity.mul(sheet.getStat(StatID.MOVEMENT_SPEED).getTotal());
		position = position.add(distance);
	}
	
	public void addLimb(Limb limb){
		limbs.add(limb);
	}
	
	@Override
	public void render(Screen screen) {
		renderPosition = position.clone();
		renderPosition = motionSet.getActiveMotion().applyMotion(renderPosition);
		screen.renderSprite(body, renderPosition.x , renderPosition.y);
		
		for(Limb x : limbs)
			x.renderLimb(screen);
	}
	
	public Vector2f getCenterOffset(){
		return centerOffset;
	}
	
	private void generateHitBox(){
		hitBox = new Rectangle((int) position.x, (int) position.y, body.getSpriteWidth(), body.getSpriteHeight());
	}
	
	@Override
	public Rectangle getRect() {
		return hitBox;
	}

	@Override
	public Sprite getSprite(){
		return body;
	}
	
	@Override
	public void death() {
		live = false;
	}

	@Override
	public void push(Vector2f distance) {
		
	}

	@Override
	public void playHurtSound() {
		
	}

	@Override
	public void collisionWith(Collideable obj) {
		
	}

	public void setMoveDirection(Vector2f moveDirection) {
		this.moveDirection = moveDirection;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public AniMotionSet getMotionSet() {
		return motionSet;
	}

	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	public Vector2f getRenderPosition() {
		return renderPosition;
	}

	
	
	
}
