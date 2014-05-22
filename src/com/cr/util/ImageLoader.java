package com.cr.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	private static HashMap<String, Image> imageLib = new HashMap<String, Image>();
	
	public static synchronized BufferedImage loadImage2(String folder, String name){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageLoader.class.getResource(folder + "/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
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