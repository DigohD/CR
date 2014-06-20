package com.cr.engine.graphics;

import static org.lwjgl.opengl.GL11.*;

import com.cr.game.GameStateManager;
import com.cr.util.Camera;

public class Screen {
	

	
	public Screen(){
		initGL();
		
		
	}
	
	private void initGL(){
		glClearColor(0f,0f,0f,0f);
	
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_ALPHA);
		glEnable(GL_BLEND);
		glEnable(GL_CULL_FACE);
		//glEnable(GL_DEPTH_TEST);
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glCullFace(GL_BACK);
	}
	
	public void render(GameStateManager gsm){
		clearScreen();
		gsm.render(this);
	}
	
	private void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void renderFont(Font font, float x, float y, float scale){
		font.renderFont(x, y, scale);
	}
	
	public void renderSprite(Sprite sprite, float x, float y){
		sprite.getTransform().translate(x, y, 0);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}
	
	public void renderSprite(Sprite sprite, float x, float y, float row, float col){
		sprite.updateTexCoords(row, col);
		sprite.getTransform().translate(x, y, 0);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}
	
	public void renderSprite(Sprite sprite, float x, float y, float row, float col, float xScale, float yScale){
		sprite.updateTexCoords(row, col);
		sprite.getTransform().translate(x, y, 0);
		sprite.getTransform().scale(xScale, yScale, 1);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}
	
	// Renders with coordinates relative to screen, rather than relative to the world
	public void renderStaticSprite(Sprite sprite, float x, float y){
		sprite.getTransform().translate(x + Camera.getCamX(), y + Camera.getCamY(), 0);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}

}
