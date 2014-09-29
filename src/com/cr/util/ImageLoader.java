package com.cr.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	private static HashMap<String, BufferedImage> imageLib = 
			new HashMap<String, BufferedImage>();
	
	public ImageLoader(){
		// Tiles
		imageLib.put("tileatlas", loadImage("/tiles/tileatlas.png"));
		imageLib.put("grass", loadImage("/tiles/grass.png"));
		imageLib.put("grass2", loadImage("/tiles/grass2.png"));
		imageLib.put("dirt", loadImage("/tiles/dirt.png"));
		imageLib.put("dirt2", loadImage("/tiles/dirt2.png"));
		imageLib.put("sand", loadImage("/tiles/sand.png"));
		imageLib.put("water", loadImage("/tiles/water.png"));
		imageLib.put("water2", loadImage("/tiles/water2.png"));
		imageLib.put("ripple", loadImage("/tiles/ripple.png"));
		
		//normal maps
		imageLib.put("normalMap0", loadImage("/tiles/normalMap0.png"));
		imageLib.put("normalMap1", loadImage("/tiles/normalMap1.png"));
		
		//cube maps
		imageLib.put("cubemap0", loadImage("/tiles/cubemap0.png"));
		imageLib.put("cubemap1", loadImage("/tiles/cubemap1.png"));
		
		imageLib.put("mask", loadImage("/tiles/mask.png"));
		imageLib.put("mask1", loadImage("/tiles/mask1.png"));
		
		imageLib.put("yellowMask", loadImage("/tiles/yellowMask.png"));
		imageLib.put("blueMask", loadImage("/tiles/blueMask.png"));
		
		//TileLayers
		imageLib.put("waterlayer", loadBitmap("/tilelayers/waterlayer.png"));
		
		//Fonts
		imageLib.put("uppercase", loadImage("/font/uppercase.png"));
		imageLib.put("black", loadImage("/font/fblack.png"));
		imageLib.put("blue", loadImage("/font/fblue.png"));
		imageLib.put("bluedark", loadImage("/font/fbluedark.png"));
		imageLib.put("green", loadImage("/font/fgreen.png"));
		imageLib.put("greendark", loadImage("/font/fgreendark.png"));
		imageLib.put("magenta", loadImage("/font/fmagenta.png"));
		imageLib.put("orange", loadImage("/font/forange.png"));
		imageLib.put("pink", loadImage("/font/fpink.png"));
		imageLib.put("purple", loadImage("/font/fpurple.png"));
		imageLib.put("red", loadImage("/font/fred.png"));
		imageLib.put("reddark", loadImage("/font/freddark.png"));
		imageLib.put("white", loadImage("/font/fwhite.png"));
		imageLib.put("yellow", loadImage("/font/fyellow.png"));

		// Nature
		imageLib.put("tree1", loadImage("/prop/nature/tree1.png"));
		imageLib.put("tree2", loadImage("/prop/nature/tree2.png"));
		imageLib.put("tree3", loadImage("/prop/nature/tree3.png"));
		imageLib.put("tree4", loadImage("/prop/nature/tree4.png"));
		
		imageLib.put("stone", loadImage("/prop/nature/stone.png"));
		
		// Hero
//		imageLib.put("hero", loadImage("/hero/hero.png"));
		imageLib.put("herohead", loadImage("/hero/herohead.png"));
		imageLib.put("herolowerbody", loadImage("/hero/herolowerbody.png"));
		imageLib.put("heroupperbody", loadImage("/hero/heroupperbody.png"));
		imageLib.put("herorighthand", loadImage("/hero/herorighthand.png"));
		imageLib.put("herolefthand", loadImage("/hero/herolefthand.png"));
		
		imageLib.put("healthball", loadImage("/hero/ball.png"));
		imageLib.put("healthfill", loadImage("/hero/fill.png"));
		imageLib.put("healthfillbm", loadImage("/hero/fillbm.png"));
		
		
		// Hero Footprints
//		imageLib.put("footprintgrass", loadImage("image", "footprintgrass.png"));
		
		// Weapons
		imageLib.put("hammer", loadImage("/items/weapons/hammer.png"));
		imageLib.put("hammericon", loadImage("/items/weapons/hammericon.png"));
		imageLib.put("knife", loadImage("/items/weapons/knife.png"));
		imageLib.put("knifeicon", loadImage("/items/weapons/knifeicon.png"));
		
		// Projectiles
		imageLib.put("rock1", loadImage("/projectile/rock1.png"));
		
		// Armor
		imageLib.put("copperhelm", loadImage("items/armors/copperhelm.png"));
		imageLib.put("copperhelmicon", loadImage("items/armors/copperhelmicon.png"));
		imageLib.put("copperplate", loadImage("items/armors/copperplate.png"));
		imageLib.put("copperplateicon", loadImage("items/armors/copperplateicon.png"));
		imageLib.put("copperleggings", loadImage("items/armors/copperleggings.png"));
		imageLib.put("copperleggingsicon", loadImage("items/armors/copperleggingsicon.png"));
		
		// Inventory UI
		//imageLib.put("stBG", loadImage("image", "semiTransparentBG.png"));
		imageLib.put("inventorybg", loadImage("/inventory/inventorybg.png"));
		imageLib.put("exitbutton", loadImage("/inventory/exitbutton.png"));
		imageLib.put("inventorybutton", loadImage("/inventory/inventorybutton.png"));
		imageLib.put("slot", loadImage("/inventory/slot.png"));
		imageLib.put("contour", loadImage("/inventory/contour.png"));
		imageLib.put("cursor", loadImage("/inventory/cursor.png"));
		
		// Materials UI
		imageLib.put("basebutton", loadImage("/materialui/basebutton.png"));
		imageLib.put("essencesbutton", loadImage("/materialui/essencesbutton.png"));
		imageLib.put("mineralsbutton", loadImage("/materialui/mineralsbutton.png"));
			
		// Materials
		imageLib.put("copper", loadImage("/material/copper.png"));
		imageLib.put("basiccloth", loadImage("/material/basiccloth.png"));
		imageLib.put("pyrite", loadImage("/material/pyrite.png"));
		imageLib.put("quartz", loadImage("/material/quartz.png"));
		imageLib.put("scrapwood", loadImage("/material/scrapwood.png"));
		imageLib.put("strangepowder", loadImage("/material/strangepowder.png"));
		imageLib.put("turquoise", loadImage("/material/turquoise.png"));
		imageLib.put("basicleather", loadImage("/material/basicleather.png"));
		imageLib.put("forestsoul", loadImage("/material/forestsoul.png"));
	
//		// Crafting UI
		imageLib.put("addbutton", loadImage("/craftingui/addbutton.png"));
		imageLib.put("backbutton", loadImage("/craftingui/backbutton.png"));
		imageLib.put("patternbutton", loadImage("/craftingui/patternbutton.png"));
		imageLib.put("processbutton", loadImage("/craftingui/processbutton.png"));
		imageLib.put("craftbutton", loadImage("/craftingui/craftbutton.png"));
		imageLib.put("sliderarrow", loadImage("/craftingui/sliderarrow.png"));
		imageLib.put("slider", loadImage("/craftingui/slider.png"));
		imageLib.put("craftingbg", loadImage("/craftingui/craftingbg.png"));
		imageLib.put("patternbg", loadImage("/craftingui/patternbg.png"));
		
		imageLib.put("downarrow", loadImage("/craftingui/downarrow.png"));
		imageLib.put("downarrow2", loadImage("/craftingui/downarrow2.png"));
		imageLib.put("uparrow", loadImage("/craftingui/uparrow.png"));
		imageLib.put("uparrow2", loadImage("/craftingui/uparrow2.png"));
		
		imageLib.put("flatblack", loadImage("/craftingui/black.png"));
		imageLib.put("flatwhite", loadImage("/craftingui/white.png"));
		imageLib.put("flatdarkred", loadImage("/craftingui/darkred.png"));
		imageLib.put("flatgreen", loadImage("/craftingui/green.png"));
		imageLib.put("blank", loadImage("/craftingui/blank.png"));
//		
//		// Patterns
		imageLib.put("knifepattern", loadImage("/pattern/knifepattern.png"));
		imageLib.put("hammerpattern", loadImage("/pattern/hammerpattern.png"));
		imageLib.put("helmpattern", loadImage("/pattern/helmpattern.png"));
		imageLib.put("breastplatepattern", loadImage("/pattern/breastplatepattern.png"));
		imageLib.put("leggingspattern", loadImage("/pattern/leggingspattern.png"));
//		
		// Enemies
		imageLib.put("critter", loadImage("/enemy/critter.png"));
		imageLib.put("melee", loadImage("/enemy/melee.png"));
		imageLib.put("neutral", loadImage("/enemy/neutral.png"));
		imageLib.put("ranged", loadImage("/enemy/ranged.png"));
		
		imageLib.put("wispbody", loadImage("/enemy/wispbody.png"));
		imageLib.put("wisplimb", loadImage("/enemy/wisplimb.png"));
		
		imageLib.put("felfbody", loadImage("/enemy/forestelf/felfbody.png"));
		imageLib.put("felfhead", loadImage("/enemy/forestelf/felfhead.png"));
		imageLib.put("felflefthand", loadImage("/enemy/forestelf/felflefthand.png"));
		imageLib.put("felfrighthand", loadImage("/enemy/forestelf/felfrighthand.png"));
		
//		// Particles
		imageLib.put("blood", loadImage("/particle/blood.png"));
		imageLib.put("white1", loadImage("/particle/white1.png"));
		imageLib.put("wispp", loadImage("/particle/wispp.png"));
	}
	
	private static synchronized BufferedImage loadImage(String path){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageLoader.class.getResource("/textures/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	private static synchronized BufferedImage loadBitmap(String path){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageLoader.class.getResource("/bitmaps/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static BufferedImage getImage(String name){
		return imageLib.get(name);
	}
}