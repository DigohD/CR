package com.cr.crafting.v2.property;

import java.util.ArrayList;
import java.util.Random;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.material.Material.State;

public class Fragile extends Property{
	
	Random rnd;
	
	@Override
	public void affectParentMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public void affectOtherMaterialPreState(Material material, State state, ArrayList<Material> materials) {
		
	}
	
	@Override
	public void affectParentMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		if(state == State.BLASTED || state == State.FLASHED)
			if(rnd.nextInt(2) == 0)
				material.setState(State.BROKEN);
	}
	
	@Override
	public void affectOtherMaterialPostState(Material material, State state, ArrayList<Material> materials) {
		
	}

	@Override
	public String getName() {
		return "Fragile";
	}

	@Override
	public String getDescription() {
		return "50% Chance to break when blasted or flashed";
	}

}
