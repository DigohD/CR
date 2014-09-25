package com.cr.engine.graphics;

import com.cr.engine.core.Vector3f;

public class Material {
	
	private float material_shininess;
	private Vector3f diffuseColor;
	private Vector3f specularColor;
	private Vector3f emissiveColor;
	
	public Material(){
		this(25.0f, new Vector3f(1,1,1), new Vector3f(1,1,1), new Vector3f(0,0,0));
	}
	
	public Material(float material_shininess, Vector3f diffuseColor,
			Vector3f specularColor, Vector3f emissiveColor){
		this.material_shininess = material_shininess;
		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
		this.emissiveColor = emissiveColor;
	}

	public float getMaterialShininess() {
		return material_shininess;
	}

	public void setMaterialShininess(float material_shininess) {
		this.material_shininess = material_shininess;
	}

	public Vector3f getDiffuseColor() {
		return diffuseColor;
	}

	public void setDiffuseColor(Vector3f diffuseColor) {
		this.diffuseColor = diffuseColor;
	}

	public Vector3f getSpecularColor() {
		return specularColor;
	}

	public void setSpecularColor(Vector3f specularColor) {
		this.specularColor = specularColor;
	}

	public Vector3f getEmissiveColor() {
		return emissiveColor;
	}

	public void setEmissiveColor(Vector3f emissiveColor) {
		this.emissiveColor = emissiveColor;
	}

}
