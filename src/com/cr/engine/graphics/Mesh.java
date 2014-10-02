package com.cr.engine.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_SHORT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glBufferSubData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;

import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;

public class Mesh {
	
	private int vboID, texID, iboID, vaoID;
	private int size;
	
	private FloatBuffer vertexBuffer, texCoordBuffer;
	private ShortBuffer indexBuffer;
	
	private boolean allDynamic;
	
	public Mesh(Vertex[] vertices, short[] indices){
		createVertexBuffer(vertices, indices, true);
		sendData();
	}
	
	public Mesh(Vertex[] vertices, Vector2f[] texCoords, short[] indices, boolean allDynamic){
		this.allDynamic = allDynamic;
		sendData(vertices, texCoords, indices);
	}
	
	private void createVertexBuffer(Vertex[] vertices, short[] indices, boolean texCoords){
		calcNormals(vertices, indices);
		
		if(!texCoords)
			vertexBuffer = BufferUtils.createFloatBuffer(6 * vertices.length);
		else vertexBuffer = BufferUtils.createFloatBuffer(Vertex.SIZE * vertices.length);
		
		for(int i = 0; i < vertices.length; i++){
			vertexBuffer.put(vertices[i].getPos().x);
			vertexBuffer.put(vertices[i].getPos().y);
			vertexBuffer.put(vertices[i].getPos().z);
			
			if(texCoords){
				vertexBuffer.put(vertices[i].getTexCoord().x);
				vertexBuffer.put(vertices[i].getTexCoord().y);
			}
			
			vertexBuffer.put(vertices[i].getNormal().x);
			vertexBuffer.put(vertices[i].getNormal().y);
			vertexBuffer.put(vertices[i].getNormal().z);
		}	
		
		vertexBuffer.flip();
		
		indexBuffer = BufferUtils.createShortBuffer(indices.length);
		indexBuffer.put(indices);
		indexBuffer.flip();
		
		size = indices.length;
	}
	
	private void createTexCoordBuffer(Vector2f[] texCoords){
		texCoordBuffer = BufferUtils.createFloatBuffer(2 * texCoords.length);
		
		for(int i = 0; i < texCoords.length; i++){
			texCoordBuffer.put(texCoords[i].x);
			texCoordBuffer.put(texCoords[i].y);
		}
		
		texCoordBuffer.flip();
	}
	
	private void sendData(){
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
		glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);
		//glVertexAttribPointer(3, 3, GL_FLOAT, false, Vertex.SIZE * 4, 32);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindVertexArray(0);
	}
	
	private void sendData(Vertex[] vertices, Vector2f[] texCoords, short[] indices) {
		createVertexBuffer(vertices, indices, false);
		createTexCoordBuffer(texCoords);
		
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
		//glVertexAttribPointer(3, 3, GL_FLOAT, false, 6*4, 20);
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
	
	public void updateIndexData(short[] indices){
		indexBuffer = BufferUtils.createShortBuffer(indices.length);
		indexBuffer.put(indices);
		indexBuffer.flip();
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, 0, indexBuffer);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void render(){
		glBindVertexArray(vaoID);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		//glEnableVertexAttribArray(3);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_SHORT, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		//glDisableVertexAttribArray(3);
	
		glBindVertexArray(0);
	}
	
	private void calcNormals(Vertex[] vertices, short[] indices){
		for(int i = 0; i < indices.length; i+=3){
			Vertex v0 = vertices[indices[i]];
			Vertex v1 = vertices[indices[i+1]];
			Vertex v2 = vertices[indices[i+2]];
			
			Vector3f edge1 = v1.getPos().sub(v0.getPos());
			Vector3f edge2 = v2.getPos().sub(v0.getPos());
			
			Vector3f normal = edge1.cross(edge2).normalize();
			
			v0.setNormal(v0.getNormal().add(normal));
			v1.setNormal(v1.getNormal().add(normal));
			v2.setNormal(v2.getNormal().add(normal));
		}
		
		for(int i = 0; i < vertices.length; i++)
			vertices[i].setNormal(vertices[i].getNormal().normalize());
		
	}
	
	private void calcTangents(Vertex[] vertices, short[] indices){
		for(int i = 0; i < indices.length; i+=3){
			Vertex v0 = vertices[indices[i]];
			Vertex v1 = vertices[indices[i+1]];
			Vertex v2 = vertices[indices[i+2]];
			
			Vector3f edge1 = v1.getPos().sub(v0.getPos());
			Vector3f edge2 = v2.getPos().sub(v0.getPos());
			
			float deltaU1 = v1.getTexCoord().x - v0.getTexCoord().x;
			float deltaV1 = v1.getTexCoord().y - v0.getTexCoord().y;
			float deltaU2 = v2.getTexCoord().x - v0.getTexCoord().x;
			float deltaV2 = v2.getTexCoord().y - v0.getTexCoord().y;
			
			float f = 1.0f / (deltaU1 * deltaV2 - deltaU2 * deltaV1);
		
			Vector3f tangent = new Vector3f(0,0,0);
			
			tangent.x = f * (deltaV2 * edge1.x - deltaV1 * edge2.x);
			tangent.y = f * (deltaV2 * edge1.y - deltaV1 * edge2.y);
			tangent.z = f * (deltaV2 * edge1.z - deltaV1 * edge2.z);
			
			v0.setTangent(v0.getTangent().add(tangent));
			v1.setTangent(v1.getTangent().add(tangent));
			v2.setTangent(v2.getTangent().add(tangent));
		}
		
		for(int i = 0; i < vertices.length; i++)
			vertices[i].getTangent().normalize();
		
	}

	public FloatBuffer getVertexBuffer() {
		return vertexBuffer;
	}

	public FloatBuffer getTexCoordBuffer() {
		return texCoordBuffer;
	}

	public ShortBuffer getIndexBuffer() {
		return indexBuffer;
	}

}
