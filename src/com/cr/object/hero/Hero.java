package com.cr.object.hero;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.object.DynamicObject;
import com.cr.util.ImageLoader;
import com.cr.util.Vector2f;

public class Hero extends DynamicObject{
	
	private BufferedImage image;

	public Hero(Vector2f position) {
		super(position);
		
	}

	@Override
	public void tick(float dt) {
		
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, (int)position.x, (int)position.y, null);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

}
