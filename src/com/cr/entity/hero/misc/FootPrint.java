package com.cr.entity.hero.misc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.gameEngine.EntityManager;
import com.cr.resource.ImageLoader;
import com.cr.util.Vector2f;

public class FootPrint extends Entity implements Renderable, Tickable{

	private BufferedImage image;
	private int timer;
	
	public FootPrint(){
		super(Hero.position.add(new Vector2f(0, 28)));
		image = ImageLoader.getImage("footprintgrass");
		timer = 0;
		EntityManager.addEntity(this);
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, (int) position.x, (int) position.y, null);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void tick(float dt) {
		timer++;
		if(timer > 200)
			live = false;
	}
	
}
