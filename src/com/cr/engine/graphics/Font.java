package com.cr.engine.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.shader.Shader;

public class Font {
	
	private Sprite fontSheet;
	private String chars = "!              0123456789     ? ABCDEFGHIJKLMNOPQRSTUVWXYZ      abcdefghijklmnopqrstuvwxyz      ";
	
	public enum FontColor{
		BLACK, BLUE, BLUE_DARK, GREEN, GREEN_DARK, MAGENTA, 
		ORANGE, PINK, PURPLE, RED, RED_DARK, WHITE, YELLOW
	}
	
	private HashMap<Character, Vector2f> charMap;
	
	private	Mesh mesh;
	
	private Transform t;
	private Shader shader;
	private String text;
	
	private FontColor color;
	
	public Font(String text, FontColor color, boolean dynamic){

		this.text = text;
		shader = new Shader("basicVertShader", "basicFragShader");
		
		shader.addUniform("transformation");
		shader.addUniform("sampler");
		shader.setUniformi("sampler", 0);
		
		t = new Transform();
		charMap = new HashMap<Character, Vector2f>();
		
		this.color = color;
		
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
		}else if(color == FontColor.PURPLE){
			fontSheet = new Sprite("purple", 25, 8);
		}else if(color == FontColor.RED){
			fontSheet = new Sprite("red", 25, 8);
		}else if(color == FontColor.RED_DARK){
			fontSheet = new Sprite("reddark", 25, 8);
		}else if(color == FontColor.WHITE){
			fontSheet = new Sprite("white", 25, 8);
		}else if(color == FontColor.YELLOW){
			fontSheet = new Sprite("yellow", 25, 8);
		}

		loadFonts();
		
		char[] charArray = text.toCharArray();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		ArrayList<Vector2f> texCoords = new ArrayList<Vector2f>();
		
		int width = fontSheet.getSpriteWidth();
		int height = fontSheet.getSpriteHeight();
		
		float xOffset = 5f;
		float yOffset = 0f;
		
		float xLow = 0;
		float xHigh = 0;
		float yLow = 0;
		float yHigh = 0;
		
		for(int i = 0; i < charArray.length; i++){
		
//			if(Character.isLowerCase(charArray[i])){
//				xOffset = 70;
//			}else xOffset = 0;
			
			if(i >= 1)
				xOffset = 40;
			else xOffset = 0;

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

			vertices.add(new Vertex(new Vector3f(i*width - xOffset*i, height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width - xOffset*i, height + height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width + width - xOffset*i, height + height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width + width - xOffset*i, height, 0)));
			
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
		
		for(int j = 0; j < indexArray.length; j++)
			iArray[j] = indexArray[j];
		
		if(dynamic) mesh = new Mesh(vertexArray, texCoordArray, iArray, true);
		else mesh = new Mesh(vertexArray, texCoordArray, iArray, false);
		
	}
	
	public void loadFonts(){
		char[] charArray = chars.toCharArray();
		float rows = 0;
		
		for(int i = 0; i < charArray.length; i++){
			charMap.put(charArray[i], new Vector2f(i%fontSheet.getCols(), rows));
			if((i%fontSheet.getCols()) == (fontSheet.getCols() - 1)){
				rows++;
			}
		}
	}
	
	public void setFont(String txt){
		
		char[] chars = txt.toCharArray();
		
		ArrayList<Vector2f> texCoords = new ArrayList<Vector2f>();
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		int width = fontSheet.getSpriteWidth();
		int height = fontSheet.getSpriteHeight();
		
		float xLow = 0;
		float xHigh = 0;
		float yLow = 0;
		float yHigh = 0;
		
		float xOffset = 5f;
		
		for(int i = 0; i < chars.length; i++){
			
//			if(Character.isLowerCase(chars[i])){
//				xOffset = 70;
//			}else xOffset = 0;
			
			if(i >= 1)
				xOffset = 40;
			else xOffset = 0;

			xLow = charMap.get(chars[i]).x / fontSheet.getCols();
			xHigh = xLow + (1 / fontSheet.getCols());
			yLow = charMap.get(chars[i]).y / fontSheet.getRows();
			yHigh = yLow + (1 / fontSheet.getRows());

			indices.add(vertices.size() + 0);
			indices.add(vertices.size() + 1);
			indices.add(vertices.size() + 2);
			
			indices.add(vertices.size() + 2);
			indices.add(vertices.size() + 3);
			indices.add(vertices.size() + 0);

			vertices.add(new Vertex(new Vector3f(i*width - xOffset*i, height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width - xOffset*i, height + height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width + width - xOffset*i, height + height, 0)));
			vertices.add(new Vertex(new Vector3f(i*width + width - xOffset*i, height, 0)));
			
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
		
		for(int j = 0; j < indexArray.length; j++)
			iArray[j] = indexArray[j];
		
		mesh.updateVertexData(vertexArray);
		mesh.updateIndexData(iArray);
		mesh.updateTexCoordData(texCoordArray);
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

	public Sprite getFontSheet() {
		return fontSheet;
	}

	public FontColor getColor() {
		return color;
	}

	
	
	
	
	

}
