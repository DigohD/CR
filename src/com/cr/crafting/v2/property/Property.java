package com.cr.crafting.v2.property;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Material.State;

public abstract class Property{

	public abstract void affectParentMaterialPreState(Material material, State state, ArrayList<Material> materials);
	public abstract void affectOtherMaterialPreState(Material material, State state, ArrayList<Material> materials);
	
	public abstract void affectParentMaterialPostState(Material material, State state, ArrayList<Material> materials);
	public abstract void affectOtherMaterialPostState(Material material, State state, ArrayList<Material> materials);
	
	public abstract String getName();
	public abstract String getDescription();
	
}
