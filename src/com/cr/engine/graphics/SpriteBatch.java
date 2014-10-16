package com.cr.engine.graphics;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;
import java.util.List;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.shader.Shader;
import com.cr.util.Randomizer;

public class SpriteBatch {
	
	private Texture texture;
	private Shader shader;
	private Mesh mesh;
	private List<Vector2f> positions;
	
	private float xLow = 0;
	private float xHigh = 0;
	private float yLow = 0;
	private float yHigh = 0;
	
	private float rows, cols;
	
	private int width, height;
	
	private Transform t;
	
	private static float counter;
	
	public SpriteBatch(Texture texture, float rows, float cols, List<Vector2f> positions, Shader shader){
		this.texture = texture;
		this.positions = positions;
		this.shader = shader;
		
		t = new Transform();
		
		this.rows = rows;
		this.cols = cols;
		
		width = (int) (texture.getWidth() / cols); 
		height = (int) (texture.getHeight() / rows);
		
		counter = 0;
		
		generateMesh(positions);
	}
	
	private void generateMesh(List<Vector2f> positions){
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Integer> indices = new ArrayList<Integer>();
	
		for(int i = 0; i < positions.size(); i++){
			
			
			indices.add(vertices.size() + 0);
			indices.add(vertices.size() + 1);
			indices.add(vertices.size() + 2);
			
			indices.add(vertices.size() + 2);
			indices.add(vertices.size() + 3);
			indices.add(vertices.size() + 0);
			
			float row = 0;
			float col = Randomizer.getInt(0, 3);
			
			xLow = col / cols;
			xHigh = xLow + (1 / cols);
			yLow = row / rows;
			yHigh = yLow + (1 / rows);
			
			float y = positions.get(i).y;
			counter -= y * 1.0f * 0.0001f;
			
			vertices.add(new Vertex(new Vector3f(positions.get(i).x, y, counter), new Vector2f(xLow, yLow)));
			vertices.add(new Vertex(new Vector3f(positions.get(i).x, y + height,  counter), new Vector2f(xLow, yHigh)));
			vertices.add(new Vertex(new Vector3f(positions.get(i).x + width, y + height,  counter), new Vector2f(xHigh, yHigh)));
			vertices.add(new Vertex(new Vector3f(positions.get(i).x + width, y,   counter), new Vector2f(xHigh, yLow)));
		}
		
		Vertex[] vertexArray = new Vertex[vertices.size()];
		Integer[] indexArray = new Integer[indices.size()];
	
		vertices.toArray(vertexArray);
		indices.toArray(indexArray);
		
		int[] iArray = new int[indexArray.length];
		
		for(int i = 0; i < indexArray.length; i++)
			iArray[i] = indexArray[i];
		
		mesh = new Mesh(vertexArray, iArray);
		
	}
	
	public void calcTexCoords(float row, float col){
		xLow = col / cols;
		xHigh = xLow + (1 / cols);
		yLow = row / rows;
		yHigh = yLow + (1 / rows);
	}
	
	public void render(){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_ALPHA_TEST);
		t.translate(0, 0, -10f);
		shader.bind();
		shader.setUniform("transformation", t.getOrthoTransformation());
		texture.bind();
		mesh.render();
		texture.bind();
		shader.unbind();
	}
	
	public int getSpriteWidth() {
		return width;
	}

	public int getSpriteHeight() {
		return height;
	}

}
