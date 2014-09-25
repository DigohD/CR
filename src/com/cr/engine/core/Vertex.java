package com.cr.engine.core;

public class Vertex {
	
	private Vector3f pos;
	private Vector3f normal;
	private Vector2f texCoord;
	
	public final static int SIZE = 8;
	
	public Vertex(Vector3f pos){
		this(pos, new Vector2f(0,0));
	}
	
	public Vertex(Vector3f pos, Vector2f texCoord){
		this(pos, texCoord, new Vector3f(0,0,0));
	}
	
	public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal){
		this.pos = pos;
		this.texCoord = texCoord;
		this.normal = normal;
	}

	public Vector3f getPos() {
		return pos;
	}

	public Vector2f getTexCoord() {
		return texCoord;
	}
	
	public Vector3f getNormal() {
		return normal;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}
}
