package com.cr.entity.hero.body;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.anim.BodyBob;
import com.cr.entity.hero.anim.HeadBob;
import com.cr.resource.ImageLoader;

public class Body implements Renderable, Tickable{

	private BufferedImage image;
	private BodyBob anim = new BodyBob();
	
	public Body(){
		image = ImageLoader.getImage("herobody");
	}
	
	@Override
	public void render(Graphics2D g){
		int x = (int) Hero.position.x - 5;
		int y = (int) Hero.position.y + 5;
		g.drawImage(image,
				x + (int)anim.getOffset().x,
				y + (int)anim.getOffset().y,
				x + 22 + (int)anim.getOffset().x,
				y + 28 + (int)anim.getOffset().y,
				0, 0, 22, 28, null);
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
