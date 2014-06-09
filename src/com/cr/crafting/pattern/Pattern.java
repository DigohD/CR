package com.cr.crafting.pattern;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.cr.crafting.material.Material;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.item.Item;
import com.cr.item.weapon.Weapon;
import com.cr.resource.ImageLoader;

public abstract class Pattern {

	public static ArrayList<Base> bases = new ArrayList<Base>();
	
	protected Item item;
	
	protected Material baseMaterial;
	protected int baseAmount;
	
	protected String name;
	
	protected ArrayList<Material> secs = new ArrayList<Material>();
	protected ArrayList<Integer> secsAmount = new ArrayList<Integer>();
	
	protected BufferedImage image;
	
	public Pattern(String imageName){
		image = ImageLoader.getImage(imageName);
	}
	
	public abstract void startNew();
	public abstract void applyMaterial(Material material, int amount);
	public abstract void applyBaseMaterial(Material material, int amount);
	public abstract Item generateItem();
	
	public abstract String getName();
	
	public BufferedImage getImage(){
		return image;
	}

	public ArrayList<Base> getBases() {
		return bases;
	}
}
