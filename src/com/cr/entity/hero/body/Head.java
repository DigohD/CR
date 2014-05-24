package com.cr.entity.hero.body;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cr.entity.Renderable;
import com.cr.entity.Tickable;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.anim.Bob;
import com.cr.entity.hero.anim.HeadBob;
import com.cr.item.armor.CopperHelm;
import com.cr.resource.ImageLoader;

public class Head extends PlayerPart{

//	private HeadBob anim = new HeadBob();
	
	public Head() {
		super("herohead", new HeadBob(), 2, 0, 5, -14);
		setItem(new CopperHelm());
	}
}
