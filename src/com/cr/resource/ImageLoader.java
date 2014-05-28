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
		//imageLib.put("tileLayer2", loadImage("image", "tileLayer2.png"));
		
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
		
		// UI
		imageLib.put("stBG", loadImage("image", "semiTransparentBG.png"));
		imageLib.put("inventorybg", loadImage("image", "inventorybg.png"));
		imageLib.put("exitbutton", loadImage("image", "exitbutton.png"));
		imageLib.put("inventorybutton", loadImage("image", "inventorybutton.png"));
		imageLib.put("slot", loadImage("image", "slot.png"));
		imageLib.put("contour", loadImage("image", "contour.png"));
		imageLib.put("cursor", loadImage("image", "cursor.png"));
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
	
//	public static synchronized BufferedImage loadImage(String folder, String name){
//	BufferedImage img = null;
//	try {
//		img = ImageIO.read(ImageLoader.class.getResource(folder + "/" + name));
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	if(OptionsTrack.isPixelIndexing())
//		return loadImage(folder, name, img);
//	return img;
//}
	
//	private static synchronized BufferedImage loadImage(String folder, String name, BufferedImage img){
//		int width = img.getWidth();
//		int height = img.getHeight();
//		
//		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//		int[] pixelData = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
//		
//		img.getRGB(0, 0, width, height, pixelData, 0, width);
//		
//		for(int y = 0; y < height; y++)
//			for(int x = 0; x < width; x++)
//				if(pixelData[x + (y*width)] == Color.WHITE)
//					pixelData[x + (y * width)] = (pixelData[x + (y * width)]>>24) & 0xff;
//			
//		return image;
//	}
}