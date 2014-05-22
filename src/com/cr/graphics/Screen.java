package com.cr.graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cr.game.Display;
import com.cr.object.hero.Hero;
import com.cr.util.Vector2f;

public class Screen {
	
	private int width, height;
	Hero hero;
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		hero = new Hero(new Vector2f(400, 400));
	}
	
	public void tick(float dt){
		hero.tick(dt);
	}
	
	public void render(){
		Graphics2D g = null;
		try{
			g = Display.getGraphics();
			clearScreen(g);
			
			//draw state here
			hero.render(g);
			
		}finally{ g.dispose();}
		Display.update();
	}
	
	private void clearScreen(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
	}

}
