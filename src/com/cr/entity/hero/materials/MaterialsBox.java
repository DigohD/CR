package com.cr.entity.hero.materials;

import java.util.ArrayList;
import java.util.HashMap;

import com.cr.crafting.v2.material.Copper;
import com.cr.crafting.v2.material.ForestSoul;
import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Pyrite;
import com.cr.crafting.v2.material.Quartz;
import com.cr.states.GameState;

public class MaterialsBox {
	protected static HashMap<Integer, Material> materials;
	
	public MaterialsBox(){
		materials = new HashMap<Integer, Material>();
		materials.put(1, new Copper());
		materials.put(2, new Pyrite());
		materials.put(3, new Quartz());
		materials.put(4, new ForestSoul());
	}
	
	public static Material getMaterial(int ID){
		return materials.get(ID);
	}
	
	public static ArrayList<Material> getMaterials(){
		ArrayList<Material> materialsList = new ArrayList<Material>();
		
		for(int i = 1; i <= 4; i++)
			if(materials.get(i).getAmount() > 0)
				materialsList.add(materials.get(i));
		
		return materialsList;
	}
}