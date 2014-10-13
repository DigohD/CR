package com.cr.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.cr.engine.graphics.Sprite;

public class SpriteLoader {

	private static HashMap<String, Sprite> spriteLib = 
			new HashMap<String, Sprite>();
	
	public SpriteLoader(){
		// Tiles
		spriteLib.put("tileatlas", newSprite("tileatlas"));
		spriteLib.put("tileatlas32", newSprite("tileatlas32"));
		spriteLib.put("ripple", newSprite("ripple"));
		
		//normal maps
		spriteLib.put("normalMap0", newSprite("normalMap0"));
		spriteLib.put("normalMap1", newSprite("normalMap1"));
		
		//cube maps
		spriteLib.put("cubemap0", newSprite("cubemap0"));
		spriteLib.put("cubemap1", newSprite("cubemap1"));
		
		
		//TileLayers
//		spriteLib.put("waterlayer", loadBitmap("waterlayer"));
		
		//Fonts
		spriteLib.put("uppercase", newSprite("uppercase"));
		spriteLib.put("black", newSprite("black"));
		spriteLib.put("blue", newSprite("blue"));
		spriteLib.put("bluedark", newSprite("bluedark"));
		spriteLib.put("green", newSprite("green"));
		spriteLib.put("greendark", newSprite("greendark"));
		spriteLib.put("magenta", newSprite("magenta"));
		spriteLib.put("orange", newSprite("orange"));
		spriteLib.put("pink", newSprite("pink"));
		spriteLib.put("purple", newSprite("purple"));
		spriteLib.put("red", newSprite("red"));
		spriteLib.put("reddark", newSprite("reddark"));
		spriteLib.put("white", newSprite("white"));
		spriteLib.put("yellow", newSprite("yellow"));

		// Nature
		spriteLib.put("tree1", newSprite("tree1"));
		spriteLib.put("tree2", newSprite("tree2"));
		spriteLib.put("tree3", newSprite("tree3"));
		spriteLib.put("tree4", newSprite("tree4"));
		
		spriteLib.put("stone1", newSprite("stone1"));
		
		spriteLib.put("reeds", newSprite("reeds"));
		
		// Hero
//		spriteLib.put("hero", newSprite("/hero/hero.png"));
		spriteLib.put("herohead", newSprite("herohead"));
		spriteLib.put("herolowerbody", newSprite("herolowerbody"));
		spriteLib.put("heroupperbody", newSprite("heroupperbody"));
		spriteLib.put("herorighthand", newSprite("herorighthand"));
		spriteLib.put("herolefthand", newSprite("herolefthand"));
		
		spriteLib.put("healthball", newSprite("healthball"));
		spriteLib.put("healthfill", newSprite("healthfill"));
		spriteLib.put("healthfillbm", newSprite("healthfillbm"));
		
		spriteLib.put("mptest", newSprite("mptest"));
		
		
		// Hero Footprints
//		spriteLib.put("footprintgrass", newSprite("image", "footprintgrass.png"));
		
		// Weapons
		spriteLib.put("hammer", newSprite("hammer"));
		spriteLib.put("hammericon", newSprite("hammericon"));
		spriteLib.put("knife", newSprite("knife"));
		spriteLib.put("knifeicon", newSprite("knifeicon"));
		
		// Projectiles
		spriteLib.put("rock1", newSprite("rock1"));
		
		// Armor
		spriteLib.put("copperhelm", newSprite("copperhelm"));
		spriteLib.put("copperhelmicon", newSprite("copperhelmicon"));
		spriteLib.put("copperplate", newSprite("copperplate"));
		spriteLib.put("copperplateicon", newSprite("copperplateicon"));
		spriteLib.put("copperleggings", newSprite("copperleggings"));
		spriteLib.put("copperleggingsicon", newSprite("copperleggingsicon"));
		
		// Inventory UI
		//spriteLib.put("stBG", newSprite("image", "semiTransparentBG.png"));
		spriteLib.put("inventorybg", newSprite("inventorybg"));
		spriteLib.put("exitbutton", newSprite("exitbutton"));
		spriteLib.put("inventorybutton", newSprite("inventorybutton"));
		spriteLib.put("slot", newSprite("slot"));
		spriteLib.put("contour", newSprite("contour"));
		spriteLib.put("cursor", newSprite("cursor"));
		
		// Materials UI
		spriteLib.put("basebutton", newSprite("basebutton"));
		spriteLib.put("essencesbutton", newSprite("essencesbutton"));
		spriteLib.put("mineralsbutton", newSprite("mineralsbutton"));
			
		// Materials
		spriteLib.put("copper", newSprite("copper"));
		spriteLib.put("basiccloth", newSprite("basiccloth"));
		spriteLib.put("pyrite", newSprite("pyrite"));
		spriteLib.put("quartz", newSprite("quartz"));
		spriteLib.put("scrapwood", newSprite("scrapwood"));
		spriteLib.put("strangepowder", newSprite("strangepowder"));
		spriteLib.put("turquoise", newSprite("turquoise"));
		spriteLib.put("basicleather", newSprite("basicleather"));
		spriteLib.put("forestsoul", newSprite("forestsoul"));
		spriteLib.put("blueagate", newSprite("blueagate"));
		spriteLib.put("greenagate", newSprite("greenagate"));
	
//		// Crafting UI
		spriteLib.put("addbutton", newSprite("addbutton"));
		spriteLib.put("backbutton", newSprite("backbutton"));
		spriteLib.put("patternbutton", newSprite("patternbutton"));
		spriteLib.put("processbutton", newSprite("processbutton"));
		spriteLib.put("craftbutton", newSprite("craftbutton"));
		spriteLib.put("sliderarrow", newSprite("sliderarrow"));
		spriteLib.put("slider", newSprite("slider"));
		
		spriteLib.put("craftingbg", newSprite("craftingbg"));
		spriteLib.put("patternbg", newSprite("patternbg"));
		spriteLib.put("amountbg", newSprite("amountbg"));
		spriteLib.put("processbg", newSprite("processbg"));
		
		spriteLib.put("weaponbutton", newSprite("weaponbutton"));
		spriteLib.put("armorbutton", newSprite("armorbutton"));
		spriteLib.put("jewelrybutton", newSprite("jewelrybutton"));
		
		
		spriteLib.put("downarrow", newSprite("downarrow"));
		spriteLib.put("downarrow2", newSprite("downarrow2"));
		spriteLib.put("uparrow", newSprite("uparrow"));
		spriteLib.put("uparrow2", newSprite("uparrow2"));
		
		spriteLib.put("flatblack", newSprite("flatblack"));
		spriteLib.put("flatwhite", newSprite("flatwhite"));
		spriteLib.put("flatdarkred", newSprite("flatdarkred"));
		spriteLib.put("flatgreen", newSprite("flatgreen"));
		spriteLib.put("blank", newSprite("blank"));
//		
//		// Patterns
		spriteLib.put("knifepattern", newSprite("knifepattern"));
		spriteLib.put("hammerpattern", newSprite("hammerpattern"));
		spriteLib.put("helmpattern", newSprite("helmpattern"));
		spriteLib.put("breastplatepattern", newSprite("breastplatepattern"));
		spriteLib.put("leggingspattern", newSprite("leggingspattern"));
//		
		// Enemies
		spriteLib.put("critter", newSprite("critter"));
		spriteLib.put("melee", newSprite("melee"));
		spriteLib.put("neutral", newSprite("neutral"));
		spriteLib.put("ranged", newSprite("ranged"));
		
		spriteLib.put("wispbody", newSprite("wispbody"));
		spriteLib.put("wisplimb", newSprite("wisplimb"));
		
		spriteLib.put("felfbody", newSprite("felfbody"));
		spriteLib.put("felfhead", newSprite("felfhead"));
		spriteLib.put("felflefthand", newSprite("felflefthand"));
		spriteLib.put("felfrighthand", newSprite("felfrighthand"));
		
//		// Particles
		spriteLib.put("blood", newSprite("blood"));
		spriteLib.put("white1", newSprite("white1"));
		spriteLib.put("wispp", newSprite("wispp"));
	}
	
	private static synchronized Sprite newSprite(String path){
		Sprite sprite = new Sprite(path); 
		System.out.println("sprite: " + path + " = " + sprite);
		return sprite;
	}
	
	public static Sprite getSprite(String name){
		if(spriteLib.get(name) == null){
			System.out.println("NO SUCH SPRITE, TERMINATING");
			System.exit(0);
		}
		return spriteLib.get(name);
	}
	
}
