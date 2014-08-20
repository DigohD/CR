package com.cr.crafting.v2.property;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Material.State;

public class Sturdy extends Property{

	@Override
	public void affectParentMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		if(material.getProcessCount() > 1){
			material.modifyHeatSpan(25);
			material.modifyTimeSpan(25);
		}
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
		return "Sturdy";
	}

	@Override
	public String getDescription() {
		return "25% higher limit spans after initial processing.";
	}
	
}
