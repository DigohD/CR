package com.cr.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.cr.crafting.pattern.Pattern;
import com.cr.crafting.station.CraftingStation;
import com.cr.crafting.station.Forge;
import com.cr.crafting.ui.*;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.*;
import com.cr.entity.hero.materials.BaseButton;
import com.cr.entity.hero.materials.BaseSlot;
import com.cr.entity.hero.materials.EssenceSlot;
import com.cr.entity.hero.materials.EssencesButton;
import com.cr.entity.hero.materials.MaterialSlot;
import com.cr.entity.hero.materials.Materials;
import com.cr.entity.hero.materials.Materials.Base;
import com.cr.entity.hero.materials.Materials.Essences;
import com.cr.entity.hero.materials.Materials.MaterialType;
import com.cr.entity.hero.materials.Materials.Minerals;
import com.cr.entity.hero.materials.MineralSlot;
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
	private CraftButton craft;
	private SliderArrow sliderArrow;
	
	private CraftingStation station;
	
	private Materials materials;
	
	private enum Phase {PATTERN, BASE, SECONDARIES};
	private enum Tab {ESSENCES, MINERALS};
	private Phase phase;
	private Tab tab;
	
	private Pattern activePattern;
	
	private Pattern[] possiblePatterns;
	private ArrayList<PatternButton> patterns = new ArrayList<PatternButton>();
	
	private ArrayList<BaseSlot> bases = new ArrayList<BaseSlot>();
	private ArrayList<EssenceSlot> essence = new ArrayList<EssenceSlot>();
	private ArrayList<MineralSlot> mineral = new ArrayList<MineralSlot>();
	
	private BaseSlot chosenBase;
	private int baseAmount;
	private MaterialSlot chosenMat;
	
	private ArrayList<MaterialSlot> mats = new ArrayList<MaterialSlot>();
	private ArrayList<Integer> matsAmounts = new ArrayList<Integer>();
	
	private boolean isCrafted;
	
	private int xOffset = (Game.WIDTH - 800) / 2;
	private int yOffset = (Game.HEIGHT - 600) / 2;
	
	public CraftingState(GameStateManager gsm){
		super(gsm);
		blockRendering = false;
		
		isCrafted = false;
		
		int xOffset = (Game.WIDTH - 800) / 2;
		int yOffset = (Game.HEIGHT - 600) / 2;
		
//		base = new BaseButton(600 + xOffset, 378 + yOffset);
		essences = new EssencesButton(600 + xOffset, 430 + yOffset);
		minerals = new MineralsButton(600 + xOffset, 482 + yOffset);
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
		
		craft = new CraftButton(xOffset + 390, yOffset + 515);
		add = new AddButton(xOffset + 240, yOffset + 515);
		back = new BackButton(xOffset + 90, yOffset + 515);
		
		
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
					System.out.println(bases.size() + " size");
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
				
				baseAmount = amount;
				
				phase = Phase.SECONDARIES;
				bases.clear();
				
				ArrayList<Essences> e = Materials.getEssences();
				for(int i = 0; i < e.size(); i++){
					this.essence.add(new EssenceSlot(i, 0, e.get(i)));
					this.essence.get(i).setAmount(Materials.getEssenceAmount(e.get(i)));
				}
				ArrayList<Minerals> m = Materials.getMinerals();
				for(int i = 0; i < m.size(); i++){
					this.mineral.add(new MineralSlot(i, 0, m.get(i)));
					this.mineral.get(i).setAmount(Materials.getMineralAmount(m.get(i)));
				}
//				essence = new EssenceSlot();
//				mineral = Materials.getMinerals():
			}
		}
				
		if(phase == Phase.SECONDARIES){
			essences.tick(dt);
			craft.tick(dt);
			
			if(essences.isClicked()){
				tab = Tab.ESSENCES;
				chosenMat = null;
			}
			minerals.tick(dt);
			if(minerals.isClicked()){
				tab = Tab.MINERALS;
				chosenMat = null;
			}
			
			if(tab == Tab.ESSENCES)
				for(EssenceSlot es : essence){
					es.tick(dt);
					if(es.isClicked()){
						chosenMat = es;
					}
				}
			if(tab == Tab.MINERALS)
				for(MineralSlot ms : mineral){
					ms.tick(dt);
					if(ms.isClicked()){
						chosenMat = ms;
					}
				}
			
			if(craft.isClicked()){
				Inventory.addItem(activePattern.generateItem());
				isCrafted = true;
			}
			
			if(chosenMat != null && add.isClicked()){
				int amount = (int) (sliderArrow.getRatio() * Materials.getAmount(chosenMat)) + 1;
				activePattern.applyMaterial(chosenMat.getMaterial(), amount);
				
				mats.add(chosenMat);
				matsAmounts.add(amount);
				
				if(tab == Tab.ESSENCES){
					essence.remove(chosenMat);
					ArrayList<EssenceSlot> tmp = new ArrayList<EssenceSlot>();
					for(int i = 0; i < essence.size(); i++){
						tmp.add(new EssenceSlot(i, 0, essence.get(i).getType()));
						tmp.get(i).setAmount(Materials.getEssenceAmount(essence.get(i).getType()));
					}
					essence = tmp;
				}if(tab == Tab.MINERALS){
					mineral.remove(chosenMat);
					ArrayList<MineralSlot> tmp = new ArrayList<MineralSlot>();
					for(int i = 0; i < mineral.size(); i++){
						tmp.add(new MineralSlot(i, 0, mineral.get(i).getType()));
						tmp.get(i).setAmount(Materials.getMineralAmount(mineral.get(i).getType()));
					}
					mineral = tmp;
				}
				chosenMat = null;
			}
		}
		
//		Hero.updateInventory();
		
		if(KeyInput.space || exit.isClicked() || isCrafted) {
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
		
		if(phase != Phase.PATTERN){
			Font bigFont = new Font("Tahoma", 36, 36);
			g.setFont(bigFont);
			g.setColor(Color.WHITE);
			g.drawString(activePattern.getName(), 
					xOffset + 20, yOffset - 10);
		}
			
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
		for(BaseSlot bs : bases)
			bs.renderHoover(g);
		
		g.drawImage(craftingbg, xOffset + 10, yOffset + 400, null);
		g.drawImage(slider, xOffset + 23, yOffset + 470, null);
		add.render(g);
		back.render(g);
		sliderArrow.render(g);
		
		Font headerFont = new Font("Tahoma", 24, 24);
		g.setFont(headerFont);
		g.setColor(Color.WHITE);
		
		if(chosenBase != null){
			int amount = (int) (sliderArrow.getRatio() * Materials.getBaseAmount(chosenBase.getBaseType())) + 1;
			g.drawString("Use " + amount + " " + chosenBase.getName(), 
					xOffset + 23, yOffset + 450);
		}else
			g.drawString("Choose a base material", xOffset + 23, yOffset + 450);
	}
	
	private void renderSecondaryPhase(Graphics2D g){
		if(tab == Tab.ESSENCES){
			for(EssenceSlot es : essence)
				es.render(g);
			for(EssenceSlot es : essence)
				es.renderHoover(g);
		}
		if(tab == Tab.MINERALS){
			for(MineralSlot ms : mineral)
				ms.render(g);
			for(MineralSlot ms : mineral)
				ms.renderHoover(g);
		}
		g.drawImage(craftingbg, xOffset + 10, yOffset + 400, null);
		g.drawImage(slider, xOffset + 23, yOffset + 470, null);
		craft.render(g);
		add.render(g);
		back.render(g);
		sliderArrow.render(g);
		
		Font headerFont = new Font("Tahoma", 24, 24);
		Font subFont = new Font("Tahoma", 18, 18);
		g.setFont(headerFont);
		g.setColor(Color.WHITE);
		
		essences.render(g);
		minerals.render(g);
		if(tab == Tab.ESSENCES){
			if(chosenMat != null){
				int amount = (int) (sliderArrow.getRatio() * materials.getAmount(chosenMat)) + 1;
				g.drawString("Use " + amount + " " + chosenMat.getName(), 
						xOffset + 23, yOffset + 450);
			}else
				g.drawString("Choose an additional material", xOffset + 23, yOffset + 450);
		}else if(tab == Tab.MINERALS){
			if(chosenMat != null){
				int amount = (int) (sliderArrow.getRatio() * materials.getAmount(chosenMat)) + 1;
				g.drawString("Use " + amount + " " + chosenMat.getName(), 
						xOffset + 23, yOffset + 450);
			}else
				g.drawString("Choose an additional material", xOffset + 23, yOffset + 450);
		}
		
		g.setFont(headerFont);
		g.drawString("Base", 
				xOffset + 603, yOffset + 100);
		g.setFont(subFont);
		g.drawString(chosenBase.getName() + ": " + baseAmount, 
				xOffset + 603, yOffset + 130);
		
		for(int i = 0; i < mats.size(); i++){
			g.setFont(headerFont);
			g.drawString("Additional", 
					xOffset + 603, yOffset + 200);
			g.setFont(subFont);
			g.drawString(mats.get(i).getName() + ": " + matsAmounts.get(i), 
					xOffset + 603, yOffset + 230 + (30 * i));
		}
		
	}

}
