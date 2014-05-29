package com.cr.crafting;

public abstract class Curve {

	protected float ampMod = 1, perMod = 1, offset = 1;
	
	public abstract float getFunctionValue(float x);

	public void testCurve(){
		for(int i = 1; i < 10; i++)
			System.out.println("X = " + i + ": " + getFunctionValue(i));
	}
	
	public float getAmpMod() {
		return ampMod;
	}

	public void addAmpMod(float amount) {
		ampMod = ampMod + amount;
	}

	public float getPerMod() {
		return perMod;
	}

	public void addPerMod(float amount) {
		perMod = perMod + amount;
	}

	public float getOffset() {
		return offset;
	}

	public void addOffset(float amount) {
		offset = offset + amount;
	}
	
	
}
