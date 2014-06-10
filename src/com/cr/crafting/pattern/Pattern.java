package com.cr.crafting.pattern;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.cr.crafting.material.Material;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.item.Item;
import com.cr.item.weapon.Weapon;
import com.cr.resource.ImageLoaderOld;

public abstract class Pattern {

	public static ArrayList<Base> bases = new ArrayList<Base>();
	
	protected Item item;
	
	protected Material baseMaterial;
	protected int baseAmount;
	
	protected String name;
	
	protected ArrayList<Material> secs = new ArrayList<Material>();
	protected ArrayList<Integer> secsAmount = new ArrayList<Integer>();
	
	protected Sprite sprite;
	
	public Pattern(String imageName){
		sprite = new Sprite(imageName);
	}
	
	public abstract void startNew();
	public abstract void applyMaterial(Material material, int amount);
	public abstract void applyBaseMaterial(Material material, int amount);
	public abstract Item generateItem();
	
	public abstract String getName();
	
	public Sprite getSprite(){
		return sprite;
	}

	public ArrayList<Base> getBases() {
		return bases;
	}
}
