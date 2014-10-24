package com.cr.world.terrain;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;
import java.util.List;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.Mesh;
import com.cr.engine.graphics.Texture;
import com.cr.engine.graphics.shader.Shader;
import com.cr.util.Randomizer;
import com.cr.world.World;

public class WorldObjectBatch {
	
	private Texture texture;
	private Shader shader;
	private Mesh mesh;
	
	private float xLow = 0;
	private float xHigh = 0;
	private float yLow = 0;
	private float yHigh = 0;
	
	private float rows, cols, startRow, startCol;
	
	private int width, height;
	
	private Transform t;
	
	private float counter;
	
	public WorldObjectBatch(Texture texture, float rows, float cols){
		this(texture, rows, cols, 0, 0);
	}
	
	public WorldObjectBatch(Texture texture, float rows, float cols, float row, float col){
		this.texture = texture;
		this.shader = World.getShader();
		
		t = new Transform();
		
		this.rows = rows;
		this.cols = cols;
		this.startRow = row;
		this.startCol = col;
		
		width = (int) (texture.getWidth() / cols); 
		height = (int) (texture.getHeight() / rows);
	
		counter = 0;
	}
	
	public void generateMesh(List<Vector2f> positions, boolean random){
		generateMesh(rows, cols, positions, random);
	}
	
	private void generateMesh(float rows, float cols, List<Vector2f> positions, boolean random){
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
			float col = startCol;
			if(random){
				col = Randomizer.getInt(0, (int)cols);
			}
			
			xLow = col / cols;
			xHigh = xLow + (1 / cols);
			yLow = row / rows;
			yHigh = yLow + (1 / rows);
			
			float y = positions.get(i).y;
			counter = y * -1.0f * 0.01f;
			
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
	
	public void render(float depth){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_ALPHA_TEST);
		t.translate(0, 0, depth);
		shader.bind();
		shader.setUniform("transformation", t.getOrthoTransformation());
		texture.bind();
		mesh.render();
		texture.bind();
		shader.unbind();
	}
	
	public int getObjectWidth() {
		return width;
	}

	public int getObjectHeight() {
		return height;
	}

}
