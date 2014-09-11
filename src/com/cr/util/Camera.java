package com.cr.util;

import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Window;
import com.cr.game.EntityManager;

public class Camera {
	
	private static Vector3f position;
	private Vector3f targetPos;
	
	private float cameraFollowFactor = 40.0f; 
	
	public Camera(){
		this(new Vector3f(0,0,0));
		targetPos = new Vector3f(0,0,0);
		position.x = EntityManager.getHero().getX() - (Window.getWidth()/2 - EntityManager.getHero().getWidth());
		position.y = EntityManager.getHero().getY() - (Window.getHeight()/2 - EntityManager.getHero().getHeight());
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
		
		position = position.add(diff);
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
