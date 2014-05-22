package com.cr.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public interface Renderable {
	
	public void render(Graphics2D g);
	public BufferedImage getImage();
	
}
