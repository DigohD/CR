package com.cr.resource;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	private static HashMap<String, BufferedImage> imageLib = 
			new HashMap<String, BufferedImage>();
	
	public ImageLoader(){
		// Tiles
		imageLib.put("grass", loadImage("image", "grass.png"));
		imageLib.put("grass2", loadImage("image", "grass2.png"));
		imageLib.put("tree", loadImage("image", "tree.png"));
		imageLib.put("poison", loadImage("image", "poison.png"));
		imageLib.put("sand", loadImage("image", "sand.png"));
		imageLib.put("dirt", loadImage("image", "dirt.png"));
		imageLib.put("water", loadImage("image", "water.png"));
		imageLib.put("stone", loadImage("image", "stone.png"));
		
		//TileLayers
		imageLib.put("tileLayer", loadImage("image", "tileLayer.png"));
		imageLib.put("grasslayer", loadImage("image", "grasslayer.png"));
		imageLib.put("stonelayer", loadImage("image", "stonelayer.png"));
		imageLib.put("sandlayer", loadImage("image", "sandlayer.png"));
		imageLib.put("waterlayer", loadImage("image", "waterlayer.png"));
		imageLib.put("dirtlayer", loadImage("image", "dirtlayer.png"));

		// Hero
		imageLib.put("hero", loadImage("image", "hero.png"));
		imageLib.put("herohead", loadImage("image", "herohead.png"));
		imageLib.put("herobody", loadImage("image", "herobody.png"));
		imageLib.put("herorighthand", loadImage("image", "herorighthand.png"));
		imageLib.put("herolefthand", loadImage("image", "herolefthand.png"));
		
		//camera
		imageLib.put("camera", loadImage("image", "cameraBox.png"));
		
		// Hero Footprints
		imageLib.put("footprintgrass", loadImage("image", "footprintgrass.png"));
		
		// Weapons
		imageLib.put("hammer", loadImage("image", "hammer.png"));
		imageLib.put("hammericon", loadImage("image", "hammericon.png"));
		imageLib.put("knife", loadImage("image", "knife.png"));
		imageLib.put("knifeicon", loadImage("image", "knifeicon.png"));
		
		// Armor
		imageLib.put("copperhelm", loadImage("image", "copperhelm.png"));
		imageLib.put("copperhelmicon", loadImage("image", "copperhelmicon.png"));
		
		// Inventory UI
		imageLib.put("stBG", loadImage("image", "semiTransparentBG.png"));
		imageLib.put("inventorybg", loadImage("image", "inventorybg.png"));
		imageLib.put("exitbutton", loadImage("image", "exitbutton.png"));
		imageLib.put("inventorybutton", loadImage("image", "inventorybutton.png"));
		imageLib.put("slot", loadImage("image", "slot.png"));
		imageLib.put("contour", loadImage("image", "contour.png"));
		imageLib.put("cursor", loadImage("image", "cursor.png"));
		
		// Materials UI
		imageLib.put("basebutton", loadImage("image/materialui", "basebutton.png"));
		imageLib.put("essencesbutton", loadImage("image/materialui", "essencesbutton.png"));
		imageLib.put("mineralsbutton", loadImage("image/materialui", "mineralsbutton.png"));
			
		// Materials
		imageLib.put("copper", loadImage("image", "copper.png"));
		imageLib.put("basiccloth", loadImage("image", "basiccloth.png"));
		imageLib.put("pyrite", loadImage("image", "pyrite.png"));
		imageLib.put("quartz", loadImage("image", "quartz.png"));
		imageLib.put("scrapwood", loadImage("image", "scrapwood.png"));
		imageLib.put("strangepowder", loadImage("image", "strangepowder.png"));
		imageLib.put("turquoise", loadImage("image", "turquoise.png"));
		imageLib.put("basicleather", loadImage("image", "basicleather.png"));
		imageLib.put("forestsoul", loadImage("image", "forestsoul.png"));
	
		// Crafting UI
		imageLib.put("addbutton", loadImage("image/craftingui", "addbutton.png"));
		imageLib.put("backbutton", loadImage("image/craftingui", "backbutton.png"));
		imageLib.put("craftbutton", loadImage("image/craftingui", "craftbutton.png"));
		imageLib.put("sliderarrow", loadImage("image/craftingui", "sliderarrow.png"));
		imageLib.put("slider", loadImage("image/craftingui", "slider.png"));
		imageLib.put("craftingbg", loadImage("image/craftingui", "craftingbg.png"));
		
		// Patterns
		imageLib.put("knifepattern", loadImage("image", "knifepattern.png"));
		imageLib.put("hammerpattern", loadImage("image", "hammerpattern.png"));
		imageLib.put("helmpattern", loadImage("image", "helmpattern.png"));
		
		// Particles
		imageLib.put("blood", loadImage("image", "blood.png"));
	}
	
	private static synchronized BufferedImage loadImage(String folder, String name){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageLoader.class.getResource(folder + "/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static BufferedImage getImage(String name){
		return imageLib.get(name);
	}	
}