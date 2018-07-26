package com.cr.util;
import java.util.ArrayList;
import java.util.List;

import com.cr.engine.graphics.ColorRGBA;

public class BiomeGenerator {
	
	private int m_Octaves;
	private float m_Roughness;
	private float m_LayerFrequency;
	private float m_LayerWeight;
	
	private List<TileWeight> m_TileWeightList;
	
	public BiomeGenerator(int octaves, float roughness, float layerFrequency, float layerWeight) {
		m_Octaves = octaves;
		m_Roughness = roughness;
		m_LayerFrequency = layerFrequency;
		m_LayerWeight = layerWeight;
		
		m_TileWeightList = new ArrayList<TileWeight>();
	}
	
	public void addTileWeight(Integer tileID, Float weight) {
		m_TileWeightList.add(new TileWeight(tileID, weight));
	}
	
	public void generateTerrain(int[] pixels, int mapWidth, int mapHeight, boolean randomizeSeed, int defaultTileID) {
		float[] noiseMap = generateNoiseMap(mapWidth, mapHeight, randomizeSeed);
		
		int[] noiseMapPixels = new int[mapWidth * mapHeight];
		
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				float octavedNoisevalue = noiseMap[x + y * mapWidth];
				 for (TileWeight tileWeight : m_TileWeightList) {
					 if (octavedNoisevalue < tileWeight.weight) {
						 noiseMapPixels[x + y * mapWidth] = tileWeight.tileID;
						 break;
					 }
				 }
			}
		}
		
		for (int i = 0; i < noiseMapPixels.length; i++) {
			if (noiseMapPixels[i] == ColorRGBA.BLACK) {
				pixels[i] = defaultTileID;
				continue;
			}
			
			pixels[i] = noiseMapPixels[i];
		}
	}
	
	private float[] generateNoiseMap(int width, int height, boolean randomizeSeed) {
		float[] noiseMap = new float[width * height];

		for (int octave = 0; octave < m_Octaves; octave++) {
			int xO = randomizeSeed ? Randomizer.getInt(0, 16000000) : 0;
			int yO = randomizeSeed ? Randomizer.getInt(0, 16000000) : 0;

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					noiseMap[x + y * width] += SimplexNoise.noise((float)(x + xO) * m_LayerFrequency, 
							(float)(y + yO) * m_LayerFrequency) * m_LayerWeight;
				}
			}

			m_LayerFrequency *= 2;
			m_LayerWeight *= m_Roughness;
		}

		return noiseMap;
	}

}
