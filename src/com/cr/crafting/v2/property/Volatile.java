package com.cr.crafting.v2.property;

import java.util.ArrayList;
import java.util.Random;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Material.State;
import com.cr.util.Randomizer;

public class Volatile extends Property{
	
	@Override
	public void affectParentMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public void affectOtherMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		
	}
	
	@Override
	public void affectParentMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		if(Randomizer.getInt(0, 4) == 0)
			material.setState(State.BROKEN);
	}
	
	@Override
	public void affectOtherMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public String getName() {
		return "Volatile";
	}

	@Override
	public String getDescription() {
		return "This material has a 20% chance to break each processing.";
	}

}
