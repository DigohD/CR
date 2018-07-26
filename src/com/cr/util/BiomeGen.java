package com.cr.util;
import java.util.List;

import com.cr.engine.graphics.ColorRGBA;

public class BiomeGen {
	
	public static void generateProceduralTerrain(List<ColorToHeightPair> colorHeightList, int[] pixels, int mapWidth, int mapHeight, int octaves, 
			float roughness, float layerFrequency, float layerWeight, boolean randomizeSeed, int defaultColor) {
		
		float[] noiseMap = generateNoiseMap(mapWidth, mapHeight, octaves, roughness, 
				layerFrequency, layerWeight, randomizeSeed);
		
		int[] noiseMapPixels = new int[mapWidth * mapHeight];
		
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				float octavedNoisevalue = noiseMap[x + y * mapWidth];
				 for (ColorToHeightPair colorHeightPair : colorHeightList) {
					 if (octavedNoisevalue < colorHeightPair.height) {
						 noiseMapPixels[x + y * mapWidth] = colorHeightPair.color;
						 break;
					 }
				 }
			}
		}
		
		for (int i = 0; i < noiseMapPixels.length; i++) {
			if (noiseMapPixels[i] == ColorRGBA.BLACK) {
				pixels[i] = defaultColor;
				continue;
			}
			
			pixels[i] = noiseMapPixels[i];
		}
	}
	
	private static float[] generateNoiseMap(int width, int height, int octaves, float roughness, 
			float layerFrequency, float layerWeight, boolean randomizeSeed) {
		
		float[] noiseMap = new float[width * height];

		for (int octave = 0; octave < octaves; octave++) {
			int xO = randomizeSeed ? Randomizer.getInt(0, 16000000) : 0;
			int yO = randomizeSeed ? Randomizer.getInt(0, 16000000) : 0;

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					noiseMap[x + y * width] += SimplexNoise.noise((float)(x + xO) * layerFrequency, 
							(float)(y + yO) * layerFrequency) * layerWeight;
				}
			}

			layerFrequency *= 2;
			layerWeight *= roughness;
		}

		return noiseMap;
	}

}
