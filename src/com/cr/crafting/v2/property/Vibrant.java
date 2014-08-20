package com.cr.crafting.v2.property;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Material.State;

public class Vibrant extends Property{

	@Override
	public void affectParentMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public void affectOtherMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		material.modifyBaseHeatSpan(-15);
		material.modifyBaseTimeSpan(-15);
	}

	@Override
	public void affectParentMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public void affectOtherMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public String getName() {
		return "Vibrant";
	}

	@Override
	public String getDescription() {
		return "Lowers limit spans for other materials by 15% each processing.";
	}
	
}
