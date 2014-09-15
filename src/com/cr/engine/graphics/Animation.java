package com.cr.engine.graphics;

import com.cr.engine.core.Vector2f;

public class Animation {
	
	private int animSpeed = 0;
	private int timer = 0;
	private int currentFrame = 0;
	
	private Sprite sprite;
	
	
	public Animation(Sprite sprite, int animSpeed){
		this(animSpeed);
		this.sprite = sprite;
	}
	
	public Animation(int animSpeed){
		this.animSpeed = animSpeed;
	}
	
	public void animateWater(Mesh mesh, float row, float col, float rows, float cols, int offset){
		
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(timer % animSpeed == 0 && currentFrame < rows){
			float xLow = col / cols;
			float xHigh = xLow + (1 / cols);
			float yLow = currentFrame / rows;
			float yHigh = yLow + (1 / rows);
			
			Vector2f[] texCoords = {new Vector2f(xLow, yLow),
				    				new Vector2f(xLow, yHigh),
				    				new Vector2f(xHigh, yHigh),
				    				new Vector2f(xHigh, yLow)};
			
			mesh.updateTexCoordData(texCoords, offset);
			currentFrame++;
		}
		
		if(currentFrame == rows) currentFrame = 0;
	}
	
	public void animateRow(int row){
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(timer % animSpeed == 0 && currentFrame < sprite.getCols()){
			sprite.updateTexCoords(row, currentFrame);
			currentFrame++;
		}
		
		if(currentFrame == sprite.getCols()) currentFrame = 0;
	}
	
	public void animateCol(int col){
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(timer % animSpeed == 0 && currentFrame < sprite.getCols()){
			sprite.updateTexCoords(currentFrame, col);
			currentFrame++;
		}
		
		if(currentFrame == sprite.getCols()) currentFrame = 0;
	}
	
	public void setFrame(int row){
		sprite.updateTexCoords(row, 0);
	}

	public int getAnimSpeed() {
		return animSpeed;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setAnimSpeed(int animSpeed) {
		this.animSpeed = animSpeed;
	}

}
