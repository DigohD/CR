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
		imageLib.put("tree", loadImage("image", "tree.png"));
		
		//Entities
		imageLib.put("hero", loadImage("image", "hero.png"));
		imageLib.put("herohead", loadImage("image", "herohead.png"));
		imageLib.put("stBG", loadImage("image", "semiTransparentBG.png"));
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