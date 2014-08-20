package com.cr.crafting.v2.property;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Material.State;

public class Explosive extends Property{

	@Override
	public void affectParentMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public void affectOtherMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		for(Material x : materials)
			if(x.getState() == State.BROKEN)
				x.explode();
	}

	@Override
	public void affectParentMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public void affectOtherMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public String getName() {
		return "Explosive";
	}

	@Override
	public String getDescription() {
		return "Destroys the current item if a material breaks during processing.";
	}
	
}
