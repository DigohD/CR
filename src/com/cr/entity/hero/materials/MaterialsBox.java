package com.cr.entity.hero.materials;

import java.util.HashMap;

import com.cr.crafting.v2.material.Copper;
import com.cr.crafting.v2.material.Material;

public class MaterialsBox {
	protected static HashMap<Integer, Material> materials = new HashMap<Integer, Material>();
	
	public MaterialsBox(){
		materials.put(1, new Copper());
	}
	
	public static Material getMaterial(int ID){
		return materials.get(ID);
	}
}