package com.cr.util;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Window;
import com.cr.game.EntityManager;

public class Camera {
	
	private static Vector3f position;
	private Vector3f targetPos;
	
	private float xC, yC, zC;
	
	private float cameraFollowFactor = 60.0f; 
	
	public Camera(){
		this(new Vector3f(0,0,0));
		targetPos = new Vector3f(0,0,0);
		position.x = EntityManager.getHero().getX() - (Window.getWidth()/2 - EntityManager.getHero().getWidth());
		position.y = EntityManager.getHero().getY() - (Window.getHeight()/2 - EntityManager.getHero().getHeight());
		
		xC = 0;
		yC = 0;
	}
	
	public Camera(Vector3f position) {
		Camera.position = position;
	}
	
	public void tick(float dt){
		
//		position.x = EntityManager.getHero().getX() - (Window.getWidth()/2 - EntityManager.getHero().getWidth());
//		position.y = EntityManager.getHero().getY() - (Window.getHeight()/2 - EntityManager.getHero().getHeight());
		
		targetPos.x = EntityManager.getHero().getX() - (Window.getWidth()/2 - EntityManager.getHero().getWidth());
		targetPos.y = EntityManager.getHero().getY() - (Window.getHeight()/2 - EntityManager.getHero().getHeight());
		
		Vector3f diff = targetPos.sub(position).div(cameraFollowFactor);
		
		xC = xC + diff.x;
		yC = yC + diff.y;
		zC = zC + diff.z;
		
		while(xC > 1){
			position = position.add(new Vector3f(1, 0, 0));
			xC = xC - 1;
		}
		while(xC < -1){
			position = position.add(new Vector3f(-1, 0, 0));
			xC = xC + 1;
		}
		
		while(yC > 1){
			position = position.add(new Vector3f(0, 1, 0));
			yC = yC - 1;
		}
		while(yC < -1){
			position = position.add(new Vector3f(0, -1, 0));
			yC = yC + 1;
		}
		
		while(zC > 1){
			position = position.add(new Vector3f(0, 0, 1));
			zC = zC - 1;
		}
		while(zC < -1){
			position = position.add(new Vector3f(0, 0, -1));
			zC = zC + 1;
		}
		
//		position = position.add(diff);
	}
	
	public static float getCamX(){
		return position.x;
	}
	
	public static float getCamY(){
		return position.y;
	}
	
	public static float getCamZ(){
		return position.z;
	}
	
	public static Vector3f getPos() {
		return position;
	}

	public void setPos(Vector3f position) {
		Camera.position = position;
	}


	
	
	
	

}
