package com.cr.entity.hero.body;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.anim.Bob;
import com.cr.entity.hero.anim.BodyBob;
import com.cr.resource.ImageLoader;

public class Body extends PlayerPart{

//	private BodyBob anim = new BodyBob();
	
	public Body() {
		super("herobody", new BodyBob(), 0, 0, 0, 0);
	}

	


}
