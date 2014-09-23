package com.cr.engine.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;

public class Mesh {
	
	private int vboID, texID, iboID, vaoID, nID;
	private int size;
	
	private FloatBuffer vertexBuffer, normalBuffer, texCoordBuffer;
	private IntBuffer indexBuffer;
	
	public Mesh(Vertex[] vertices, int[] indices){
		size = 0;
		sendStaticData(vertices, indices);
	}
	
	public Mesh(Vertex[] vertices, Vector2f[] texCoords, int[] indices, boolean dynamicData){
		size = 0;
		if(dynamicData){
			sendDynamicData(vertices, texCoords, indices);
		}else
			sendData(vertices, texCoords, indices);
	}
	
	public Mesh(Vertex[] vertices, Vector3f[] normals, Vector2f[] texCoords, int[] indices, boolean dynamicData){
		size = 0;
		if(dynamicData){
			sendDynamicData(vertices, normals, texCoords, indices);
		}else
			sendData(vertices, normals, texCoords, indices);
	}
	
	public Mesh(Vertex[] vertices, int[] indices, boolean dynamicData){
		size = 0;
		if(dynamicData){
			//sendDynamicData(vertices, indices);
		}else{
			sendStaticData(vertices, indices);
		}
	}
	
	public void updateTexCoordData(Vector2f[] texCoords){
		texCoordBuffer = BufferUtils.createFloatBuffer(2 * texCoords.length);
		
		for(int i = 0; i < texCoords.length; i++){
			texCoordBuffer.put(texCoords[i].x);
			texCoordBuffer.put(texCoords[i].y);
		}
		
		texCoordBuffer.flip();
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		//second param is the offset in bytes to where the replacement of the data will start
		glBufferSubData(GL_ARRAY_BUFFER, 0, texCoordBuffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
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
	
	public void updateVertexData(Vertex[] vertices){
		vertexBuffer = BufferUtils.createFloatBuffer(3 * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
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
	
	private void sendStaticData(Vertex[] vertices, int[] indices){
		
		vertexBuffer = BufferUtils.createFloatBuffer(Vertex.SIZE * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
			
			vertexBuffer.put(vertices[i].getTexCoord().x);
			vertexBuffer.put(vertices[i].getTexCoord().y);
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
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindVertexArray(0);
	}
	
	private void sendDynamicData(Vertex[] vertices, Vector2f[] texCoords,
			int[] indices) {
		
		vertexBuffer = BufferUtils.createFloatBuffer(3 * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
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
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		texID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glBufferData(GL_ARRAY_BUFFER, texCoordBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		iboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		vertexBuffer = null;
		indexBuffer = null;
		texCoordBuffer = null;
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindVertexArray(0);
	}
	
	private void sendDynamicData(Vertex[] vertices, Vector3f[] normals, Vector2f[] texCoords,
			int[] indices) {
		
		vertexBuffer = BufferUtils.createFloatBuffer(3 * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
		}		
		
		vertexBuffer.flip();
		
		normalBuffer = BufferUtils.createFloatBuffer(3 * normals.length);
		
		for(int i = 0; i < normals.length; i++){
			normalBuffer.put(normals[i].x);
			normalBuffer.put(normals[i].y);
			normalBuffer.put(normals[i].z);
		}
		
		normalBuffer.flip();
		
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
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		nID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, nID);
		glBufferData(GL_ARRAY_BUFFER, normalBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		texID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glBufferData(GL_ARRAY_BUFFER, texCoordBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		iboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		vertexBuffer = null;
		indexBuffer = null;
		texCoordBuffer = null;
		normalBuffer = null;
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, nID);
		glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glBindVertexArray(0);
	}
	
	private void sendData(Vertex[] vertices, Vector2f[] texCoords, int[] indices){
		
		vertexBuffer = BufferUtils.createFloatBuffer(3 * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
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
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		texID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glBufferData(GL_ARRAY_BUFFER, texCoordBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		iboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		vertexBuffer = null;
		indexBuffer = null;
		texCoordBuffer = null;
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindVertexArray(0);
	}
	
	private void sendData(Vertex[] vertices, Vector3f[] normals, Vector2f[] texCoords, int[] indices){
		
		vertexBuffer = BufferUtils.createFloatBuffer(3 * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
		}		
		
		vertexBuffer.flip();
		
		normalBuffer = BufferUtils.createFloatBuffer(3 * normals.length);
		
		for(int i = 0; i < normals.length; i++){
			normalBuffer.put(normals[i].x);
			normalBuffer.put(normals[i].y);
			normalBuffer.put(normals[i].z);
		}
		
		normalBuffer.flip();
		
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
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		nID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, nID);
		glBufferData(GL_ARRAY_BUFFER, normalBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		texID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glBufferData(GL_ARRAY_BUFFER, texCoordBuffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		iboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		vertexBuffer = null;
		normalBuffer = null;
		indexBuffer = null;
		texCoordBuffer = null;
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, nID);
		glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glBindVertexArray(0);
	}
	
	public void render(){
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	
		glBindVertexArray(0);
		
	}

}
