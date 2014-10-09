package com.cr.entity.hero;

import com.cr.engine.input.Input;
import com.cr.entity.hero.Hero.Direction;
import com.cr.net.NetStatus;
import com.cr.net.packets.Packet21Input;
import com.cr.states.net.MPClientState;

public class HeroInput {
	
	private Hero hero;
	private int lastW = -1, lastA = -1, lastS = -1, lastD = -1;
	
	public HeroInput(Hero hero){
		this.hero = hero;
	}
	
	private float diagonalSpeed(float speed){
		float res = (float) Math.sqrt((speed*speed) + (speed*speed));
		return res/2.0f + 0.3f;
	}
	
	public void input(){
		
		if(NetStatus.isMultiPlayer && !NetStatus.isHOST){
			sendInputPacket();
		}else{
			if(Input.getKey(Input.W)){
				hero.getTargetVel().y = -hero.getSpeed();
				hero.setCurrentDir(Direction.NORTH);
				hero.setMoving(true);
			}
			
			if(Input.getKey(Input.S)){
				hero.getTargetVel().y = hero.getSpeed();
				hero.setCurrentDir(Direction.SOUTH);
				hero.setMoving(true);
			}
			
			if(Input.getKey(Input.D)){
				hero.getTargetVel().x = hero.getSpeed();
				hero.setCurrentDir(Direction.EAST);
				hero.setMoving(true);
			}
			
			if(Input.getKey(Input.A)){
				hero.getTargetVel().x = -hero.getSpeed();
				hero.setCurrentDir(Direction.WEST);
				hero.setMoving(true);
			}
			
			if(Input.getKey(Input.W) && Input.getKey(Input.A)){
				hero.getTargetVel().x = -diagonalSpeed(hero.getSpeed());
				hero.getTargetVel().y = -diagonalSpeed(hero.getSpeed());
				hero.setCurrentDir(Direction.NORTH);
				hero.setMoving(true);
			}
			
			if(Input.getKey(Input.W) && Input.getKey(Input.D)){
				hero.getTargetVel().x = diagonalSpeed(hero.getSpeed());
				hero.getTargetVel().y  = -diagonalSpeed(hero.getSpeed());
				hero.setCurrentDir(Direction.NORTH);
				hero.setMoving(true);
			}
			
			if(Input.getKey(Input.S) && Input.getKey(Input.A)){
				hero.getTargetVel().x = -diagonalSpeed(hero.getSpeed());
				hero.getTargetVel().y  = diagonalSpeed(hero.getSpeed());
				hero.setCurrentDir(Direction.SOUTH);
				hero.setMoving(true);
			}
			
			if(Input.getKey(Input.S) && Input.getKey(Input.D)){
				hero.getTargetVel().x = diagonalSpeed(hero.getSpeed());
				hero.getTargetVel().y  = diagonalSpeed(hero.getSpeed());
				hero.setCurrentDir(Direction.SOUTH);
				hero.setMoving(true);
			}
			
			if(Input.getMouseDown(1) && Hero.getLeftHand().getItem() != null)
				Hero.getLeftHand().getItem().activate();
			if(Input.getMouseDown(0) && Hero.getRightHand().getItem() != null)
				Hero.getRightHand().getItem().activate();
			
			if(!Input.getKey(Input.W) && !Input.getKey(Input.S))
				hero.getTargetVel().y  = 0;
			if(!Input.getKey(Input.D) && !Input.getKey(Input.A))
				hero.getTargetVel().x = 0;
			
			if(hero.getTargetVel().y  == 0 && hero.getTargetVel().x == 0)hero.setMoving(false);
		}
			
	}
	
	private void sendInputPacket(){
		int cW = -1, cA = -1, cS = -1, cD = -1;
		if(Input.getKey(Input.W)){
			cW = 1;
		}
		if(Input.getKey(Input.A)){
			cA = 1;
		}
		if(Input.getKey(Input.S)){
			cS = 1;
		}
		if(Input.getKey(Input.D)){
			cD = 1;
		}
		
		if(lastW != cW){
			lastW = cW;
			Packet21Input p = new Packet21Input(hero.getUserName(), Input.W, lastW);
			MPClientState.getClient().sendData(p.getData());
		}
		if(lastA != cA){
			lastA = cA;
			Packet21Input p = new Packet21Input(hero.getUserName(), Input.A, lastA);
			MPClientState.getClient().sendData(p.getData());
		}
		if(lastS != cS){
			lastS = cS;
			Packet21Input p = new Packet21Input(hero.getUserName(), Input.S, lastS);
			MPClientState.getClient().sendData(p.getData());
		}
		if(lastD != cD){
			lastD = cD;
			Packet21Input p = new Packet21Input(hero.getUserName(), Input.D, lastD);
			MPClientState.getClient().sendData(p.getData());
		}
	}
	
//	private void processInput(){
//	if(KeyInput.shift){
//		if(KeyInput.up){
//			currentDir = Direction.SOUTH;
//			targetVel.y = -15f;
//		}
//		if(KeyInput.down){
//			currentDir = Direction.NORTH;
//			targetVel.y = 15f;
//		}
//		if(KeyInput.right){
//			currentDir = Direction.WEST;
//			targetVel.x = 15f;
//		}
//		if(KeyInput.left){
//			currentDir = Direction.EAST;
//			targetVel.x = -15f;
//		}
//		
//		if(KeyInput.up && KeyInput.left){
//			currentDir = Direction.SOUTH;
//			targetVel.x = -10.6f;
//			targetVel.y = -10.6f;
//		}
//		if(KeyInput.up && KeyInput.right){
//			currentDir = Direction.SOUTH;
//			targetVel.x = 10.6f;
//			targetVel.y = -10.6f;
//		}
//		if(KeyInput.down && KeyInput.left){
//			currentDir = Direction.NORTH;
//			targetVel.x = -10.6f;
//			targetVel.y = 10.6f;
//		}
//		if(KeyInput.down && KeyInput.right){
//			currentDir = Direction.NORTH;
//			targetVel.x = 10.6f;
//			targetVel.y = 10.6f;
//		}
//	}else{
//		if(KeyInput.up){
//			currentDir = Direction.NORTH;
//			targetVel.y = -15f;
//		}
//		if(KeyInput.down){
//			currentDir = Direction.SOUTH;
//			targetVel.y = 15f;
//		}
//		if(KeyInput.right){
//			currentDir = Direction.EAST;
//			targetVel.x = 15f;
//		}
//		if(KeyInput.left){
//			currentDir = Direction.WEST;
//			targetVel.x = -15f;
//		}
//		
//		if(KeyInput.up && KeyInput.left){
//			currentDir = Direction.NORTH;
//			targetVel.x = -10.6f;
//			targetVel.y = -10.6f;
//		}
//		if(KeyInput.up && KeyInput.right){
//			currentDir = Direction.NORTH;
//			targetVel.x = 10.6f;
//			targetVel.y = -10.6f;
//		}
//		if(KeyInput.down && KeyInput.left){
//			currentDir = Direction.SOUTH;
//			targetVel.x = -10.6f;
//			targetVel.y = 10.6f;
//		}
//		if(KeyInput.down && KeyInput.right){
//			currentDir = Direction.SOUTH;
//			targetVel.x = 10.6f;
//			targetVel.y = 10.6f;
//		}
//	}
//	
//	if(!KeyInput.up && !KeyInput.down){
//		targetVel.y = 0f;
//	}
//	if(!KeyInput.right && !KeyInput.left){
//		targetVel.x = 0f;
//	}
//
//}

}
