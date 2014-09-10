package com.cr.crafting.v2.test;

import com.cr.crafting.v2.material.Copper;
import com.cr.crafting.v2.material.Material;

import java.util.ArrayList;
import java.util.Random;

public class CraftTest {

	public static void main(String[] args){
		Copper c = new Copper();
		Random rnd = new Random();
		int x = 100;
		while(x-- > 0){
			int heat = rnd.nextInt(1600);
			int time = rnd.nextInt(330);
			
			c = new Copper();
			c.resetSpans();
			c.process(heat, time, new ArrayList<Material>());
			
			System.out.println(heat + " - " + time);
			System.out.println(c.getState());
		}
	}
	
}
