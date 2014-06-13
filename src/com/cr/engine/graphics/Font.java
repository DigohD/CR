package com.cr.engine.graphics;

import java.util.ArrayList;
import java.util.HashMap;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.world.World;

public class Font {
	
	private static Sprite fontSheet;
	//private static HashMap<Integer, Integer> fontWidthMap = new HashMap<Integer, Integer>();
	private String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private HashMap<Character, Vector2f> charMap;
	Transform t;
	
	public Font(){
		t = new Transform();
		charMap = new HashMap<Character, Vector2f>();
		fontSheet = new Sprite("uppercase", 3, 8, 0, 0, World.getShader(), t);
	}
	
	public void renderFont(String text){
		char[] charArray = text.toCharArray();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		ArrayList<Vector2f> texCoords = new ArrayList<Vector2f>();
		
		int width = fontSheet.getSpriteWidth();
		int height = fontSheet.getSpriteHeight();
		
		for(int i = 0; i < charArray.length; i++){
			indices.add(vertices.size() + 0);
			indices.add(vertices.size() + 1);
			indices.add(vertices.size() + 2);
			
			indices.add(vertices.size() + 2);
			indices.add(vertices.size() + 3);
			indices.add(vertices.size() + 0);
			
			vertices.add(new Vertex(new Vector3f(i*width, i*height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width, i*height + height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width + width, i*height + height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width + width, i*height, 0)));
			
			
		}
		
	}
	
	public void loadFonts(){
		char[] charArray = chars.toCharArray();
		for(int i = 0; i < charArray.length; i++){
			charMap.put(charArray[i], new Vector2f(4,i%7));
		}
		
	}
	
	
	
	

}
