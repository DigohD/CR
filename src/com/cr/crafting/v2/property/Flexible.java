package com.cr.crafting.v2.property;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Material.State;

public class Flexible extends Property{

	@Override
	public void affectParentMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		int materialCount = materials.size() - 1;
		
		material.modifyHeatSpan(materialCount * 10);
		material.modifyTimeSpan(materialCount * 10);
	}

	@Override
	public void affectOtherMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public void affectParentMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public void affectOtherMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public String getName() {
		return "Flexible";
	}

	@Override
	public String getDescription() {
		return "10% Higher limit spans for each other material being processed.";
	}
	
}
