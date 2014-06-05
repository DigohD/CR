package com.cr.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.cr.crafting.pattern.Pattern;
import com.cr.crafting.station.CraftingStation;
import com.cr.crafting.station.Forge;
import com.cr.crafting.ui.AddButton;
import com.cr.crafting.ui.BackButton;
import com.cr.crafting.ui.PatternButton;
import com.cr.crafting.ui.SliderArrow;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.entity.hero.inventory.InventoryButton;
import com.cr.entity.hero.materials.BaseButton;
import com.cr.entity.hero.materials.BaseSlot;
import com.cr.entity.hero.materials.EssencesButton;
import com.cr.entity.hero.materials.MaterialSlot;
import com.cr.entity.hero.materials.Materials;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.entity.hero.materials.Materials.MaterialType;
import com.cr.entity.hero.materials.MineralsButton;
import com.cr.game.EntityManager;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoader;

public class CraftingState extends GameState{

	private BufferedImage bg = ImageLoader.getImage("inventorybg");
	private BufferedImage craftingbg = ImageLoader.getImage("craftingbg");
	private BufferedImage slider = ImageLoader.getImage("slider");
	
	private ExitButton exit;
//	private BaseButton base;
	private EssencesButton essences;
	private MineralsButton minerals;
	
	private AddButton add;
	private BackButton back;
	private SliderArrow sliderArrow;
	
	private CraftingStation station;
	
	private Materials materials;
	
	private enum Phase {PATTERN, BASE, SECONDARIES};
	private Phase phase;
	
	private Pattern activePattern;
	
	private Pattern[] possiblePatterns;
	private ArrayList<PatternButton> patterns = new ArrayList<PatternButton>();
	
	private ArrayList<BaseSlot> bases = new ArrayList<BaseSlot>();
	private BaseSlot chosenBase;
	private MaterialSlot chosenMat;
	
	private int xOffset = (Game.WIDTH - 800) / 2;
	private int yOffset = (Game.HEIGHT - 600) / 2;
	
	public CraftingState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
		
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
//		base = new BaseButton(600 + xOffset, 378 + yOffset);
		essences = new EssencesButton(600 + xOffset, 430 + yOffset);
		minerals = new MineralsButton(600 + xOffset, 482 + yOffset);
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
		
		add = new AddButton(xOffset + 320, yOffset + 515);
		back = new BackButton(xOffset + 170, yOffset + 515);
		
		sliderArrow = new SliderArrow(xOffset + 25, yOffset + 460);
		
		phase = Phase.PATTERN;
		
		materials = Hero.getMaterials();
		Materials.setActiveTab(MaterialType.BASE);
		
		station = new Forge();
		possiblePatterns = station.getPatterns();
		for(int i = 0; i < possiblePatterns.length; i++)
			patterns.add(new PatternButton(xOffset + 10 + (i * 85), yOffset + 10, possiblePatterns[i].getImage(), possiblePatterns[i]));
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
//		inventory.tick(dt);
		
//		base.tick(dt);
		exit.tick(dt);
		
//		if(base.isClicked()){
//			Materials.setActiveTab(MaterialType.BASE);
//		}if(essences.isClicked()){
//			Materials.setActiveTab(MaterialType.ESSENCES);
//		}if(minerals.isClicked()){
//			Materials.setActiveTab(MaterialType.MINERALS);
//		}
		
		if(phase == Phase.PATTERN)
			for(PatternButton pb : patterns){
				if(pb.isClicked()){
					phase = Phase.BASE;
					activePattern = pb.getPattern();
					activePattern.startNew();
					
					ArrayList<Base> bases = activePattern.getBases();
					for(int i = 0; i < bases.size(); i++){
						this.bases.add(new BaseSlot(i, 0, bases.get(i)));
						this.bases.get(i).setAmount(Materials.getBaseAmount(bases.get(i)));
					}
				}
				pb.tick(dt);
			}
		
		if(phase != Phase.PATTERN){
			add.tick(dt);
			back.tick(dt);
			
			sliderArrow.tick(dt);
		}
		
		if(phase == Phase.BASE){
			for(BaseSlot bs : bases){
				bs.tick(dt);
				if(bs.isClicked()){
					chosenBase = bs;
				}
			}
			if(chosenBase != null && add.isClicked()){
				int amount = (int) (sliderArrow.getRatio() * materials.getBaseAmount(chosenBase.getBaseType())) + 1;
				activePattern.applyBaseMaterial(chosenBase.getMaterial(), amount);
				
				phase = Phase.SECONDARIES;
				bases.clear();
			}
		}
				
		if(phase == Phase.SECONDARIES){
			essences.tick(dt);
			minerals.tick(dt);
		}
		
//		Hero.updateInventory();
		
		if(KeyInput.space || exit.isClicked()) {
			if(gsm.next() instanceof PlayState){
				PlayState ps = (PlayState) gsm.next();
				ps.bg = false;
			}
			gsm.pop();
		}
		
//		if(KeyInput.enter) {
//			if(gsm.next() instanceof PlayState){
//				PlayState ps = (PlayState) gsm.next();
//				ps.bg = false;
//			}
//			gsm.pop();
//		}
//		if(KeyInput.c) {
//			gsm.pop();
//			EntityManager.clear();
//			gsm.pop();
//		}
	}

	@Override
	public void render(Graphics2D g){
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
		g.drawImage(bg, xOffset, yOffset, null);
		
		if(phase == Phase.PATTERN)
			renderPatternPhase(g);
		if(phase == Phase.BASE)
			renderBasePhase(g);
		if(phase == Phase.SECONDARIES)
			renderSecondaryPhase(g);
		
//		inventory.render(g);
//		base.render(g);
//		essences.render(g);
//		minerals.render(g);
		exit.render(g);
		
//		materials.render(g);
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Game.WIDTH/2-100, Game.HEIGHT/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Game.WIDTH/2-100, Game.HEIGHT/2+30);
	}
	
	private void renderPatternPhase(Graphics2D g){
		for(PatternButton pb : patterns)
			pb.render(g);
	}
	
	private void renderBasePhase(Graphics2D g){
		for(BaseSlot bs : bases)
			bs.render(g);
		
		g.drawImage(craftingbg, xOffset + 10, yOffset + 400, null);
		g.drawImage(slider, xOffset + 23, yOffset + 470, null);
		add.render(g);
		back.render(g);
		sliderArrow.render(g);
		
		Font headerFont = new Font("Tahoma", 24, 24);
		g.setFont(headerFont);
		g.setColor(Color.WHITE);
		
		if(chosenBase != null){
			int amount = (int) (sliderArrow.getRatio() * materials.getBaseAmount(chosenBase.getBaseType())) + 1;
			g.drawString("Use " + amount + " " + chosenBase.getName(), 
					xOffset + 23, yOffset + 450);
		}else
			g.drawString("Choose a base material", xOffset + 23, yOffset + 450);
	}
	
	private void renderSecondaryPhase(Graphics2D g){
		for(BaseSlot bs : bases)
			bs.render(g);
		
		g.drawImage(craftingbg, xOffset + 10, yOffset + 400, null);
		g.drawImage(slider, xOffset + 23, yOffset + 470, null);
		add.render(g);
		back.render(g);
		sliderArrow.render(g);
		
		Font headerFont = new Font("Tahoma", 24, 24);
		g.setFont(headerFont);
		g.setColor(Color.WHITE);
		
		essences.render(g);
		minerals.render(g);
		
		if(chosenMat != null){
			int amount = (int) (sliderArrow.getRatio() * materials.(chosenMat.getMaterial())) + 1;
			g.drawString("Use " + amount + " " + chosenMat.getName(), 
					xOffset + 23, yOffset + 450);
		}else
			g.drawString("Choose an additional material", xOffset + 23, yOffset + 450);
	}

}
