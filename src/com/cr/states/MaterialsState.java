package com.cr.states;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.materials.BaseButton;
import com.cr.entity.hero.materials.EssencesButton;
import com.cr.entity.hero.materials.Materials;
import com.cr.entity.hero.materials.Materials.MaterialType;
import com.cr.entity.hero.materials.MineralsButton;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.resource.ImageLoaderOld;

public class MaterialsState extends GameState{

	private Sprite bg = new Sprite("inventorybg");
	private ExitButton exit;
	private BaseButton base;
	private EssencesButton essences;
	private MineralsButton minerals;
	
	private Materials materials;
	
	public MaterialsState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
		
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		base = new BaseButton(600 + xOffset, 378 + yOffset);
		essences = new EssencesButton(600 + xOffset, 430 + yOffset);
		minerals = new MineralsButton(600 + xOffset, 482 + yOffset);
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
		
		materials = Hero.getMaterials();
		Materials.setActiveTab(MaterialType.BASE);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
//		inventory.tick(dt);
		
		base.tick(dt);
		essences.tick(dt);
		minerals.tick(dt);
		exit.tick(dt);
		
		if(base.isClicked()){
			Materials.setActiveTab(MaterialType.BASE);
		}if(essences.isClicked()){
			Materials.setActiveTab(MaterialType.ESSENCES);
		}if(minerals.isClicked()){
			Materials.setActiveTab(MaterialType.MINERALS);
		}
		
//		Hero.updateInventory();
		
		if(Input.getKey(Input.KEY_SPACE) || exit.isClicked()) {
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
	public void render(Screen screen){
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		screen.renderStaticSprite(bg, xOffset, yOffset);
//		inventory.render(g);
		base.render(screen);
		essences.render(screen);
		minerals.render(screen);
		exit.render(screen);
		
		materials.render(screen);
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Window.getWidth()/2-100, Window.getHeight()/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Window.getWidth()/2-100, Window.getHeight()/2+30);
	}

}
