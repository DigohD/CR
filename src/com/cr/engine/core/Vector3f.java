package com.cr.engine.core;

public class Vector3f {
	
	public float x;
	public float y;
	public float z;

	public Vector3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float length(){
		return (float)Math.sqrt(x * x + y * y + z * z);
	}

	public float dot(Vector3f r){
		return x * r.getX() + y * r.getY() + z * r.getZ();
	}

	public Vector3f cross(Vector3f r){
		float x_ = y * r.getZ() - z * r.getY();
		float y_ = z * r.getX() - x * r.getZ();
		float z_ = x * r.getY() - y * r.getX();

		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalize(){
		float length = length();
		return new Vector3f(x / length, y / length, z / length);
	}
	
	/**
	 * Rotates a vector about the x axis of a given point in 3d space
	 * @param point the point to rotate about
	 * @param angle the angle of rotation
	 * @return the new rotated vector
	 */
	public Vector3f rotateX(Vector3f point, float angle){
		float[][] m = new float[3][3];
		
		float rad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		//the x component
		m[0][0] = 1.0f; 
		m[0][1] = 0; 
		m[0][2] = 0;
		
		//the y component
		m[1][0] = 0; 
		m[1][1] = (this.y - point.y) * cos; 
		m[1][2] = (this.y - point.y) * sin;
		
		//the z component
		m[2][0] = 0; 
		m[2][1] = -1.0f * (this.z - point.z) * sin; 
		m[2][2] = (this.z - point.z) * cos;
		
		float x = m[0][0] + m[0][1] + m[0][2];
		float y = m[1][0] + m[1][1] + m[1][2];
		float z = m[2][0] + m[2][1] + m[2][2];
		
		Vector3f v = new Vector3f(x, y, z);
		
		return v;
	}
	
	/**
	 * Rotates a vector about the y axis of a given point in 3d space
	 * @param point the point to rotate about
	 * @param angle the angle of rotation
	 * @return the new rotated vector
	 */
	public Vector3f rotateY(Vector3f point, float angle){
		float[][] m = new float[3][3];
		
		float rad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		//the x component
		m[0][0] = (this.x - point.x) * cos; 
		m[0][1] = 0; 
		m[0][2] = -1.0f * (this.x - point.x) * sin;
		
		//the y component
		m[1][0] = 0; 
		m[1][1] = 1.0f; 
		m[1][2] = 0;
		
		//the z component
		m[2][0] = (this.z - point.z) * sin; 
		m[2][1] = 0; 
		m[2][2] = (this.z - point.z) * cos;
		
		float x = m[0][0] + m[0][1] + m[0][2];
		float y = m[1][0] + m[1][1] + m[1][2];
		float z = m[2][0] + m[2][1] + m[2][2];
		
		Vector3f v = new Vector3f(x, y, z);
		
		return v;
	}
	
	/**
	 * Rotates a vector about the z axis of a given point in 3d space
	 * @param point the point to rotate about
	 * @param angle the angle of rotation
	 * @return the new rotated vector
	 */
	public Vector3f rotateZ(Vector3f point, float angle){
		
		float[][] m = new float[3][3];
		
		float rad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		x = x - point.x;
		y = y - point.y;
		z = z - point.z;
		
		//the x component
		m[0][0] = cos; 
		m[0][1] = sin; 
		m[0][2] = 0;
				
		//the y component
		m[1][0] = -1.0f*sin; 
		m[1][1] = cos; 
		m[1][2] = 0;
				
		//the z component
		m[2][0] = 0; 
		m[2][1] = 0; 
		m[2][2] = 1;
		
		float newX = x*m[0][0] + y*m[1][0] + z*m[2][0];
		float newY = x*m[0][1] + y*m[1][1] + z*m[2][1];
		float newZ = x*m[0][2] + y*m[1][2] + z*m[2][2];

		return new Vector3f(newX + point.x, newY + point.y, newZ + point.z);
	}

	public Vector3f rotate(Vector3f axis, float angle){
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.cross(axis.mul(sinAngle)).add(           //Rotation on local X
				(this.mul(cosAngle)).add(                     //Rotation on local Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
	}

	public Vector3f add(Vector3f r){
		return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
	}

	public Vector3f add(float r){
		return new Vector3f(x + r, y + r, z + r);
	}

	public Vector3f sub(Vector3f r){
		return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
	}

	public Vector3f sub(float r){
		return new Vector3f(x - r, y - r, z - r);
	}

	public Vector3f mul(Vector3f r){
		return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
	}

	public Vector3f mul(float r){
		return new Vector3f(x * r, y * r, z * r);
	}

	public Vector3f div(Vector3f r){
		return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
	}

	public Vector3f div(float r){
		return new Vector3f(x / r, y / r, z / r);
	}

	public Vector3f abs(){
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}

	public String toString(){
		return "(" + x + " " + y + " " + z + ")";
	}

	public Vector2f getXY() { return new Vector2f(x, y); }
	public Vector2f getYZ() { return new Vector2f(y, z); }
	public Vector2f getZX() { return new Vector2f(z, x); }

	public Vector2f getYX() { return new Vector2f(y, x); }
	public Vector2f getZY() { return new Vector2f(z, y); }
	public Vector2f getXZ() { return new Vector2f(x, z); }

	public Vector3f set(float x, float y, float z) { this.x = x; this.y = y; this.z = z; return this; }
	public Vector3f set(Vector3f r) { set(r.getX(), r.getY(), r.getZ()); return this; }

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public boolean equals(Vector3f r){
		return x == r.getX() && y == r.getY() && z == r.getZ();
	}

}
