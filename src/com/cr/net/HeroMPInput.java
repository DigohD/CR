package com.cr.net;

import com.cr.engine.input.Input;
import com.cr.entity.hero.Hero.Direction;

public class HeroMPInput {
	
	private HeroMPServer hero;
	
	private boolean isW, isA, isS, isD;
	
	public HeroMPInput(HeroMPServer hero){
		this.hero = hero;
	}
	
	private float diagonalSpeed(float speed){
		float res = (float) Math.sqrt((speed*speed) + (speed*speed));
		return res/2.0f + 0.3f;
	}
	
	public void handleClientInput(int keyCode, int pressed){
		switch(keyCode){
			case(Input.W):
				if(pressed == 1)
					isW = true;
				else
					isW = false;
				break;
			case(Input.A):
				if(pressed == 1)
					isA = true;
				else
					isA = false;
				break;
			case(Input.S):
				if(pressed == 1)
					isS = true;
				else
					isS = false;
				break;
			case(Input.D):
				if(pressed == 1)
					isD = true;
				else
					isD = false;
				break;
		
		}
	}
	
	public void input(){
		if(isW){
			hero.getTargetVel().y = -hero.getSpeed();
			hero.setDirection(Direction.NORTH);
			hero.setMoving(true);
		}
		
		if(isS){
			hero.getTargetVel().y = hero.getSpeed();
			hero.setDirection(Direction.SOUTH);
			hero.setMoving(true);
		}
		
		if(isD){
			hero.getTargetVel().x = hero.getSpeed();
			hero.setDirection(Direction.EAST);
			hero.setMoving(true);
		}
		
		if(isA){
			hero.getTargetVel().x = -hero.getSpeed();
			hero.setDirection(Direction.WEST);
			hero.setMoving(true);
		}
		
		if(isW && isA){
			hero.getTargetVel().x = -diagonalSpeed(hero.getSpeed());
			hero.getTargetVel().y = -diagonalSpeed(hero.getSpeed());
			hero.setDirection(Direction.NORTH);
			hero.setMoving(true);
		}
		
		if(isW && isD){
			hero.getTargetVel().x = diagonalSpeed(hero.getSpeed());
			hero.getTargetVel().y  = -diagonalSpeed(hero.getSpeed());
			hero.setDirection(Direction.NORTH);
			hero.setMoving(true);
		}
		
		if(isS && isA){
			hero.getTargetVel().x = -diagonalSpeed(hero.getSpeed());
			hero.getTargetVel().y  = diagonalSpeed(hero.getSpeed());
			hero.setDirection(Direction.SOUTH);
			hero.setMoving(true);
		}
		
		if(isS && isD){
			hero.getTargetVel().x = diagonalSpeed(hero.getSpeed());
			hero.getTargetVel().y  = diagonalSpeed(hero.getSpeed());
			hero.setDirection(Direction.SOUTH);
			hero.setMoving(true);
		}
		
//		if(Input.getMouseDown(1) && Hero.getLeftHand().getItem() != null)
//			Hero.getLeftHand().getItem().activate();
//		if(Input.getMouseDown(0) && Hero.getRightHand().getItem() != null)
//			Hero.getRightHand().getItem().activate();
		
		if(!isW && !isS)
			hero.getTargetVel().y  = 0;
		if(!isD && !isA)
			hero.getTargetVel().x = 0;
		
		if(hero.getTargetVel().y  == 0 && hero.getTargetVel().x == 0)hero.setMoving(false);
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
