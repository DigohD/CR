package com.cr.object.hero;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.cr.object.Renderable;
import com.cr.resource.ImageLoader;

public class Head implements Renderable{

	private BufferedImage image;
	
	public Head(){
		image = ImageLoader.getImage("herohead");
	}
	
	@Override
	public void render(Graphics2D g) {
		int x = (int) Hero.position.x;
		int y = (int) Hero.position.y;
		g.drawImage(image, x, y, x + 13, y + 19,
				0, 0, 13, 19, null);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

}
