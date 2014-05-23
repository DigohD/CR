package com.cr.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.cr.gameEngine.Game;
import com.cr.gameEngine.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;
import com.cr.world.World;

public class PlayState extends GameState{

	private World w;
	private BufferedImage img;
	public boolean bg = false;
	private boolean fading = true;
	
	int alpha = 0, timer = 0;
	
	BufferedImage image = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_ARGB);
	int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
		img = ImageLoader.getImage("stBG");
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void setAlpha(byte alpha) {       
	    alpha %= 0xff; 
	    for(int y = 0; y < image.getHeight(); y++){
	    	for(int x = 0; x < image.getWidth(); x++){
		    	int color = img.getRGB(x, y);
		    	int mc = (alpha << 24) | 0x00ffffff;
		    	int newColor = color & mc;
		    	img.setRGB(x, y, newColor);
		    }
	    }
	}
	
	@Override
	public void init() {
		w = new World();
	}

	@Override
	public void tick(float dt) {
	
		
		if(fading){
			timer++;
			
		
			
		}else{
			if(KeyInput.esc){
				bg = true;
				gsm.push(new PauseState(gsm));
			}
			
			w.tick(dt);
		}
		
		
	}

	@Override
	public void render(Graphics2D g) {
		if(fading){
			g.drawImage(image, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		}
		w.render(g);
		if(bg) g.drawImage(img, 7, 6, Game.WIDTH, Game.HEIGHT, null);
	}

}
