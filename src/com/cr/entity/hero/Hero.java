package com.cr.entity.hero;

import java.awt.Rectangle;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.input.Input;
import com.cr.entity.Collideable;
import com.cr.entity.Mob;
import com.cr.entity.hero.body.Head;
import com.cr.entity.hero.body.LeftHand;
import com.cr.entity.hero.body.LowerBody;
import com.cr.entity.hero.body.RightHand;
import com.cr.entity.hero.body.UpperBody;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.entity.hero.materials.MaterialsBox;
import com.cr.net.NetStatus;
import com.cr.net.packets.Packet12Move;
import com.cr.states.net.MPClientState;
import com.cr.states.net.MPHostState;
import com.cr.stats.Stat;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;
import com.cr.world.World;
import com.cr.world.tile.Tile;

public class Hero extends Mob implements Collideable{
	
	private String userName;
	
	private Sprite sprite;
	private Vector2f targetVel;
	
	private Rectangle rect;

	public static Vector2f position;

	private int stepCounter;
	private static Head head;
	private static UpperBody body;
	private static LowerBody lowerBody;
	private static RightHand rightHand;
	private static LeftHand leftHand;

	private int printTimer;

	private static Inventory inventory;
	private static MaterialsBox materialsBox;
	
	private static StatsSheet sheet;
	
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
		if(NetStatus.isMultiPlayer && !NetStatus.isHOST){
			position = new Vector2f(MPClientState.getClient().getStartPos().x + 50, MPClientState.getClient().getStartPos().y);
		}else{
			position = new Vector2f((world.getWidth() * Tile.getTileWidth()) / 2 , (world.getHeight() * Tile.getTileHeight()) / 2);
		}
		
		if(!(NetStatus.isMultiPlayer && !NetStatus.isHOST)){
			//position = new Vector2f(10, 10);
			if(world.tileExists((int) (position.x / Tile.getTileWidth()), (int) (position.y / Tile.getTileHeight()))){
				while(!world.getTile((int) (position.x / Tile.getTileWidth()), (int) (position.y / Tile.getTileHeight())).isWalkable()){
					position.y += Tile.getTileHeight();
					if(!world.tileExists((int) (position.x / Tile.getTileWidth()), (int) (position.y / Tile.getTileHeight()))){
						break;
					}
				}
			}
		}
		
		
				
		head = new Head();
		body = new UpperBody();
		lowerBody = new LowerBody();
		rightHand = new RightHand();
		leftHand = new LeftHand();
		
		velocity = new Vector2f(0f, 0f);
		targetVel = new Vector2f(0, 0);
		
		width = 88/4 + 52/4;
		height = 28+19;
		rect = new Rectangle((int)position.x,(int)position.y, width, height);
		currentDir = Direction.SOUTH;
		
		inventory = new Inventory();
		materialsBox = new MaterialsBox();
		
		sheet = new StatsSheet(true);
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
		
		//System.out.println("X: " + position.x + ", Y: " + position.y);
		
		input.input();
		
		velocity.x = approachTarget(targetVel.x, velocity.x, dt*accSpeed);
		velocity.y = approachTarget(targetVel.y, velocity.y, dt*accSpeed);
		
		//if(!collisionWithTile(targetVel.x, 0))
			position.x = position.x + targetVel.x*dt;
		
		
		//if(!collisionWithTile(0, targetVel.y))
			position.y = position.y + targetVel.y*dt;
			
		if(NetStatus.isMultiPlayer){
			if(!NetStatus.isHOST && MPClientState.getClient().treesLoaded && MPClientState.getClient().stonesLoaded){
				Packet12Move mp  = new Packet12Move(userName, position, currentDir);
				MPClientState.getClient().sendData(mp.getData());
				passiveRegen(dt);
				//System.out.println("MOVE PACKET SENT");
			}
			if(NetStatus.isHOST){
				Packet12Move mp  = new Packet12Move(userName, position, currentDir);
				MPHostState.getServer().sendDataToAllClients(mp.getData());
			}
		}
		
		move(dt);
		
		head.tick(dt);
		body.tick(dt);
		lowerBody.tick(dt);
		rightHand.tick(dt);
		leftHand.tick(dt);
		
		if(rightHand.getItem() != null && Input.getMouse(0)){
			rightHand.getItem().activate();
		}if(leftHand.getItem() != null && Input.getMouse(1)){
			leftHand.getItem().activate();
		}
		
		if(NetStatus.isMultiPlayer && NetStatus.isHOST || !NetStatus.isMultiPlayer)
			passiveRegen(dt);
	}

	@Override
	public void render(Screen screen) {
		switch(currentDir){
			case SOUTH:
				lowerBody.render(screen);
				body.render(screen);
				rightHand.render(screen);
				leftHand.render(screen);
				head.render(screen);
				break;
			case EAST:
				leftHand.render(screen);
				lowerBody.render(screen);
				body.render(screen);
				head.render(screen);
				rightHand.render(screen);
				break;
			case NORTH:
				rightHand.render(screen);
				leftHand.render(screen);
				head.render(screen);
				lowerBody.render(screen);
				body.render(screen);
				break;
			case WEST:
				rightHand.render(screen);
				lowerBody.render(screen);
				body.render(screen);
				head.render(screen);
				leftHand.render(screen);
				break;
		}
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
		body.setItem(inventory.getUpperBodySlot().getItem());
		lowerBody.setItem(inventory.getLowerBodySlot().getItem());
	}
	
	@Override
	public void death() {
		live = false;
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
		lowerBody.getBob().setActive(isBobing);
		body.getBob().setActive(isBobing);
		rightHand.getBob().setActive(isBobing);
		leftHand.getBob().setActive(isBobing);
	}
	
	@Override
	public void takeDamage(float damage){
		playHurtSound();
		
		Stat hpNow = sheet.getStat(StatID.HP_NOW);
		hpNow.setNewBase(hpNow.getTotal() - damage);
		
		if(hpNow.getTotal() < 0)
			death();
	}
	
	private void passiveRegen(float dt){
		Stat hpNow = sheet.getStat(StatID.HP_NOW);
		Stat hpRegen = sheet.getStat(StatID.LIFE_REGEN);

		hpNow.setNewBase(hpNow.getTotal() + (hpRegen.getTotal() / 300f));
		
		if(hpNow.getTotal() > sheet.getStat(StatID.HP_MAX).getTotal())
			hpNow.setNewBase(sheet.getStat(StatID.HP_MAX).getTotal());
	}
	
	public static void onHittingSomething(){
		Stat hpNow = sheet.getStat(StatID.HP_NOW);
		Stat hpOnHit = sheet.getStat(StatID.LIFE_ON_HIT);
		hpNow.setNewBase(hpNow.getTotal() + hpOnHit.getTotal());
		
		if(hpNow.getTotal() > sheet.getStat(StatID.HP_MAX).getTotal())
			hpNow.setNewBase(sheet.getStat(StatID.HP_MAX).getTotal());
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}
	
	public Vector2f getPos(){
		return position;
	}
	
	public static void setPosition2(Vector2f position) {
		Hero.position = position;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getSpeed() {
		return speed * sheet.getStat(StatID.MOVEMENT_SPEED).getTotal();
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

	@Override
	public StatsSheet getSheet() {
		return sheet;
	}
	
	public static StatsSheet getHeroSheet() {
		return sheet;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	


}
