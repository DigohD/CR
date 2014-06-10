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
		imageLib.put("stone", loadImage("/tiles/stone.png"));
		imageLib.put("water", loadImage("/tiles/water.png"));
		
		//TileLayers
		

		// Hero
	//	imageLib.put("hero", loadImage("/hero/hero.png"));
		imageLib.put("herohead", loadImage("/hero/herohead.png"));
		imageLib.put("herobody", loadImage("/hero/herobody.png"));
		imageLib.put("herorighthand", loadImage("/hero/herorighthand.png"));
		imageLib.put("herolefthand", loadImage("/hero/herolefthand.png"));
		
		
		// Hero Footprints
//		imageLib.put("footprintgrass", loadImage("image", "footprintgrass.png"));
		
		// Weapons
		imageLib.put("hammer", loadImage("/items/weapons/hammer.png"));
		imageLib.put("hammericon", loadImage("/items/weapons/hammericon.png"));
		imageLib.put("knife", loadImage("/items/weapons/knife.png"));
		imageLib.put("knifeicon", loadImage("/items/weapons/knifeicon.png"));
		
		// Armor
		imageLib.put("copperhelm", loadImage("items/armors/copperhelm.png"));
		imageLib.put("copperhelmicon", loadImage("items/armors/copperhelmicon.png"));
		
		// Inventory UI
		//imageLib.put("stBG", loadImage("image", "semiTransparentBG.png"));
		imageLib.put("inventorybg", loadImage("/inventory/inventorybg.png"));
		imageLib.put("exitbutton", loadImage("/inventory/exitbutton.png"));
		imageLib.put("inventorybutton", loadImage("/inventory/inventorybutton.png"));
		imageLib.put("slot", loadImage("/inventory/slot.png"));
		imageLib.put("contour", loadImage("/inventory/contour.png"));
		imageLib.put("cursor", loadImage("/inventory/cursor.png"));
		
//		// Materials UI
//		imageLib.put("basebutton", loadImage("image/materialui", "basebutton.png"));
//		imageLib.put("essencesbutton", loadImage("image/materialui", "essencesbutton.png"));
//		imageLib.put("mineralsbutton", loadImage("image/materialui", "mineralsbutton.png"));
//			
//		// Materials
//		imageLib.put("copper", loadImage("image", "copper.png"));
//		imageLib.put("basiccloth", loadImage("image", "basiccloth.png"));
//		imageLib.put("pyrite", loadImage("image", "pyrite.png"));
//		imageLib.put("quartz", loadImage("image", "quartz.png"));
//		imageLib.put("scrapwood", loadImage("image", "scrapwood.png"));
//		imageLib.put("strangepowder", loadImage("image", "strangepowder.png"));
//		imageLib.put("turquoise", loadImage("image", "turquoise.png"));
//		imageLib.put("basicleather", loadImage("image", "basicleather.png"));
//		imageLib.put("forestsoul", loadImage("image", "forestsoul.png"));
//	
//		// Crafting UI
//		imageLib.put("addbutton", loadImage("image/craftingui", "addbutton.png"));
//		imageLib.put("backbutton", loadImage("image/craftingui", "backbutton.png"));
//		imageLib.put("craftbutton", loadImage("image/craftingui", "craftbutton.png"));
//		imageLib.put("sliderarrow", loadImage("image/craftingui", "sliderarrow.png"));
//		imageLib.put("slider", loadImage("image/craftingui", "slider.png"));
//		imageLib.put("craftingbg", loadImage("image/craftingui", "craftingbg.png"));
//		
//		// Patterns
//		imageLib.put("knifepattern", loadImage("image", "knifepattern.png"));
//		imageLib.put("hammerpattern", loadImage("image", "hammerpattern.png"));
//		imageLib.put("helmpattern", loadImage("image", "helmpattern.png"));
//		
//		// Particles
//		imageLib.put("blood", loadImage("image", "blood.png"));
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