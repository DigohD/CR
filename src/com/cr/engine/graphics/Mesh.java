package com.cr.engine.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glBufferSubData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;

public class Mesh {
	
	private int vboID, texID, iboID, vaoID;
	private int size;
	
	private FloatBuffer vertexBuffer, texCoordBuffer;
	private IntBuffer indexBuffer;
	
	private boolean allDynamic;
	
	public Mesh(Vertex[] vertices, int[] indices){
		sendData(vertices, indices);
	}
	
	public Mesh(Vertex[] vertices, Vector2f[] texCoords, int[] indices, boolean allDynamic){
		this.allDynamic = allDynamic;
		sendData(vertices, texCoords, indices);
	}
	
	private void calcNormals(Vertex[] vertices, int[] indices){
		for(int i = 0; i < indices.length; i+=3){
			int i0 = indices[i];
			int i1 = indices[i+1];
			int i2 = indices[i+2];
			
			Vector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
			Vector3f v2 = vertices[i2].getPos().sub(vertices[i0].getPos());
			
			Vector3f normal = v1.cross(v2).normalize();
			
			vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
			vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
			vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
			
		}
		for(int i = 0; i < vertices.length; i++){
			vertices[i].setNormal(vertices[i].getNormal().normalize());
		}
	}
	
	private void sendData(Vertex[] vertices, int[] indices){
		
		calcNormals(vertices, indices);
		
		vertexBuffer = BufferUtils.createFloatBuffer(Vertex.SIZE * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
			
			vertexBuffer.put(vertices[i].getTexCoord().x);
			vertexBuffer.put(vertices[i].getTexCoord().y);
			
			vertexBuffer.put(vertices[i].getNormal().x);
			vertexBuffer.put(vertices[i].getNormal().y);
			vertexBuffer.put(vertices[i].getNormal().z);
		}		
		
		vertexBuffer.flip();
		
		indexBuffer = BufferUtils.createIntBuffer(indices.length);
		indexBuffer.put(indices);
		indexBuffer.flip();
		
		size = indices.length;
		
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		iboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		vertexBuffer = null;
		indexBuffer = null;
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, Vertex.SIZE * 4, 20);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindVertexArray(0);
	}
	
	private void sendData(Vertex[] vertices, Vector2f[] texCoords, int[] indices) {
		
		if(!allDynamic) calcNormals(vertices, indices);
		
		vertexBuffer = BufferUtils.createFloatBuffer(6 * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
			
			vertexBuffer.put(vertices[i].getNormal().x);
			vertexBuffer.put(vertices[i].getNormal().y);
			vertexBuffer.put(vertices[i].getNormal().z);
		}		
		
		vertexBuffer.flip();
		
		texCoordBuffer = BufferUtils.createFloatBuffer(2 * texCoords.length);
		
		for(int i = 0; i < texCoords.length; i++){
			texCoordBuffer.put(texCoords[i].x);
			texCoordBuffer.put(texCoords[i].y);
		}
		
		texCoordBuffer.flip();
		
		indexBuffer = BufferUtils.createIntBuffer(indices.length);
		indexBuffer.put(indices);
		indexBuffer.flip();
		
		size = indices.length;
		
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		if(allDynamic)
			glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_DYNAMIC_DRAW);
		else glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		texID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glBufferData(GL_ARRAY_BUFFER, texCoordBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		iboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		if(allDynamic)
			glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_DYNAMIC_DRAW);
		else glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		vertexBuffer = null;
		indexBuffer = null;
		texCoordBuffer = null;
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 6*4, 0);
		glVertexAttribPointer(2, 3, GL_FLOAT, false, 6*4, 12);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindVertexArray(0);
	}
	
	public void updateTexCoordData(Vector2f[] texCoords, int offset){
		
		offset = offset * 8 * 4;
		
		texCoordBuffer = BufferUtils.createFloatBuffer(2 * texCoords.length);
		
		for(int i = 0; i < texCoords.length; i++){
			texCoordBuffer.put(texCoords[i].x);
			texCoordBuffer.put(texCoords[i].y);
		}
		
		texCoordBuffer.flip();
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		//second param is the offset in bytes to where the replacement of the data will start
		glBufferSubData(GL_ARRAY_BUFFER, offset, texCoordBuffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void updateTexCoordData(Vector2f[] texCoords){
		updateTexCoordData(texCoords, 0);
	}
	
	public void updateVertexData(Vertex[] vertices){
		vertexBuffer = BufferUtils.createFloatBuffer(6 * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
			
			vertexBuffer.put(vertices[i].getNormal().x);
			vertexBuffer.put(vertices[i].getNormal().y);
			vertexBuffer.put(vertices[i].getNormal().z);
		}
		
		vertexBuffer.flip();
		
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferSubData(GL_ARRAY_BUFFER, 0, vertexBuffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void updateIndexData(int[] indices){
		indexBuffer = BufferUtils.createIntBuffer(indices.length);
		indexBuffer.put(indices);
		indexBuffer.flip();
		
		glBindBuffer(GL_ARRAY_BUFFER, iboID);
		glBufferSubData(GL_ARRAY_BUFFER, 0, indexBuffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void render(){
		glBindVertexArray(vaoID);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
	
		glBindVertexArray(0);
	}

}
