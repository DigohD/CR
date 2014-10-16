package com.cr.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import com.cr.game.GameStateManager;
import com.cr.util.Camera;

public class Screen {
	
	public Screen(){
		glClearColor(0f,0f,0f,0f);
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_ALPHA);
		glEnable(GL_BLEND);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_ALPHA_TEST);
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glCullFace(GL_BACK);
	}
	
	public void render(GameStateManager gsm){
		clearScreen();
		gsm.render(this);
	}
	
	private void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glAlphaFunc(GL_GREATER, 0);
	}
	
	public void renderFont(Font font, float x, float y, float scale){
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_ALPHA_TEST);
		font.renderFont(x, y, scale);
	}
	
	public void renderSprite(Sprite sprite, float x, float y){
		renderSprite(sprite, x, y, 0);
	}
	
	public void renderSprite(Sprite sprite, float x, float y, int unit){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_ALPHA_TEST);
		sprite.getTransform().translate(x, y, (sprite.getSpriteHeight() + y)  * -1.0f * 0.01f);
		sprite.bind(unit);
		sprite.getMesh().render();
		sprite.unbind();
	}
	
	public void renderSprite(Sprite sprite, float x, float y, int row, int col){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_ALPHA_TEST);
		sprite.updateTexCoords(row, col);
		sprite.getTransform().translate(x, y, (sprite.getSpriteHeight() + y) * -1.0f * 0.01f);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}
	
	public void renderSprite(Sprite sprite, float x, float y, int row, int col, float xScale, float yScale){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_ALPHA_TEST);
		sprite.updateTexCoords(row, col);
		sprite.getTransform().translate(x, y, (sprite.getSpriteHeight() + y) * -1.0f * 0.01f);
		sprite.getTransform().scale(xScale, yScale, 1);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}
	
	public void renderSprite(Sprite sprite, float x, float y, float xScale, float yScale){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_ALPHA_TEST);
		sprite.getTransform().translate(x, y, (sprite.getSpriteHeight() + y) * -1.0f * 0.01f);
		sprite.getTransform().scale(xScale, yScale, 1);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}
	
	// Renders with coordinates relative to screen, rather than relative to the world
	public void renderStaticSprite(Sprite sprite, float x, float y){
		renderStaticSprite(sprite, x, y, 0);
	}
	
	public void renderStaticSprite(Sprite sprite, float x, float y, int unit){
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_ALPHA_TEST);
		sprite.getTransform().translate(x + Camera.getCamX(), y + Camera.getCamY(), 0);
		sprite.bind(unit);
		sprite.getMesh().render();
		sprite.unbind();
	}
	
	public void renderStaticSprite(Sprite sprite, float x, float y, float xScale, float yScale){
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_ALPHA_TEST);
		sprite.getTransform().translate(x + Camera.getCamX(), y + Camera.getCamY(), 0);
		sprite.getTransform().scale(xScale, yScale, 1);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}

}
