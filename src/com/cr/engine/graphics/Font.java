package com.cr.engine.graphics;

import java.util.ArrayList;
import java.util.HashMap;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.shader.Shader;

public class Font {
	
	private static Sprite fontSheet;
	//private static HashMap<Integer, Integer> fontWidthMap = new HashMap<Integer, Integer>();
	private String chars = "                                ABCDEFGHIJKLMNOPQRSTUVWXYZ      abcdefghijklmnopqrstuvwxyz      ";
	
	public enum FontColor{
		BLACK, BLUE, BLUE_DARK, GREEN, GREEN_DARK, MAGENTA, 
		ORANGE, PINK, PURPLE, RED, RED_DARK, WHITE, YELLOW
	}
	
	private HashMap<Character, Vector2f> charMap;

	private Mesh mesh;
	public Transform t;
	
	Shader shader;
	
	public Font(String text, FontColor color){
		shader = new Shader("fontVertshader", "fontShader");
		
		shader.addUniform("transformation");
		shader.addUniform("sampler");
		shader.setUniformi("sampler", 0);
		
		t = new Transform();
		charMap = new HashMap<Character, Vector2f>();
		
		if(color == FontColor.BLACK){
			fontSheet = new Sprite("black", 25, 8);
		}else if(color == FontColor.BLUE){
			fontSheet = new Sprite("blue", 25, 8);
		}else if(color == FontColor.BLUE_DARK){
			fontSheet = new Sprite("bluedark", 25, 8);
		}else if(color == FontColor.GREEN){
			fontSheet = new Sprite("green", 25, 8);
		}else if(color == FontColor.GREEN_DARK){
			fontSheet = new Sprite("greendark", 25, 8);
		}else if(color == FontColor.MAGENTA){
			fontSheet = new Sprite("magenta", 25, 8);
		}else if(color == FontColor.ORANGE){
			fontSheet = new Sprite("orange", 25, 8);
		}else if(color == FontColor.PINK){
			fontSheet = new Sprite("pink", 25, 8);
		}

		loadFonts();
		
		char[] charArray = text.toCharArray();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		ArrayList<Vector2f> texCoords = new ArrayList<Vector2f>();
		
		int width = fontSheet.getSpriteWidth();
		int height = fontSheet.getSpriteHeight();
		
		float xLow = 0;
		float xHigh = 0;
		float yLow = 0;
		float yHigh = 0;
		
		for(int i = 0; i < charArray.length; i++){
		
			xLow = charMap.get(charArray[i]).x / fontSheet.getCols();
			xHigh = xLow + (1 / fontSheet.getCols());
			yLow = charMap.get(charArray[i]).y / fontSheet.getRows();
			yHigh = yLow + (1 / fontSheet.getRows());
			
			indices.add(vertices.size() + 0);
			indices.add(vertices.size() + 1);
			indices.add(vertices.size() + 2);
			
			indices.add(vertices.size() + 2);
			indices.add(vertices.size() + 3);
			indices.add(vertices.size() + 0);
			
			vertices.add(new Vertex(new Vector3f(i*width, height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width, height + height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width + width, height + height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width + width, height, 0)));
			
			texCoords.add(new Vector2f(xLow, yLow));
			texCoords.add(new Vector2f(xLow, yHigh));
			texCoords.add(new Vector2f(xHigh, yHigh));
			texCoords.add(new Vector2f(xHigh, yLow));
		}
		
		Vertex[] vertexArray = new Vertex[vertices.size()];
		Integer[] indexArray = new Integer[indices.size()];
		Vector2f[] texCoordArray = new Vector2f[texCoords.size()];
		
		vertices.toArray(vertexArray);
		indices.toArray(indexArray);
		texCoords.toArray(texCoordArray);
		
		int[] iArray = new int[indexArray.length];
		
		for(int i = 0; i < indexArray.length; i++)
			iArray[i] = indexArray[i];
		
		mesh = new Mesh(vertexArray, texCoordArray, iArray);
	}
	
	public void loadFonts(){
		char[] charArray = chars.toCharArray();
		float rows = 0;
		for(int i = 0; i < charArray.length; i++){
			
			charMap.put(charArray[i], new Vector2f(i%fontSheet.getCols(), rows));
		//	System.out.println("col: " + i%fontSheet.getCols() + " row:" + (rows));
			if((i%fontSheet.getCols()) == (fontSheet.getCols() - 1)){
				rows++;
			}
			
			
		}
		
	}
	
	public void renderFont(float x, float y, float scale){
		t.translate(x, y, 0);
		t.scale(scale, scale, 1);
		shader.bind();
		shader.setUniform("transformation", t.getOrthoTransformation2());
		fontSheet.getTextureAtlas().bind();
		mesh.render();
		fontSheet.getTextureAtlas().unbind();
		shader.unbind();
	}

	public static Sprite getFontSheet() {
		return fontSheet;
	}

	public Mesh getM() {
		return mesh;
	}
	
	
	
	

}
