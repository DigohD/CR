package com.cr.graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cr.game.Display;

public class Screen {
	
	private int width, height;
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public void render(){
		Graphics2D g = null;
		try{
			g = Display.getGraphics();
			clearScreen(g);
			
		}finally{ g.dispose();}
		Display.update();
	}
	
	private void clearScreen(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Display.getWidth(), Display.getHeight());
	}

}
