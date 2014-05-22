package com.cr.object.hero;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.cr.object.Renderable;
import com.cr.object.Tickable;
import com.cr.object.hero.anim.HeadBob;
import com.cr.resource.ImageLoader;

public class Head implements Renderable, Tickable{

	private BufferedImage image;
	private HeadBob anim = new HeadBob();
	
	public Head(){
		image = ImageLoader.getImage("herohead");
	}
	
	@Override
	public void render(Graphics2D g) {
		int x = (int) Hero.position.x;
		int y = (int) Hero.position.y;
		g.drawImage(image,
				x + (int)anim.getOffset().x,
				y + (int)anim.getOffset().y,
				x + 13 + (int)anim.getOffset().x,
				y + 19 + (int)anim.getOffset().y,
				0, 0, 13, 19, null);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void tick(float dt) {
		anim.tick(dt);
	}

}
