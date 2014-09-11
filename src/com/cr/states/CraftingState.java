package com.cr.states;

import java.util.ArrayList;

import com.cr.crafting.v2.pattern.Pattern;
import com.cr.crafting.v2.station.AddButton;
import com.cr.crafting.v2.station.CraftButton;
import com.cr.crafting.v2.station.Forge;
import com.cr.crafting.v2.station.PatternButton;
import com.cr.crafting.v2.station.ProcessButton;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.entity.hero.materials.MaterialsBox;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.util.Camera;

public class CraftingState extends GameState{

	private Sprite bg = new Sprite("inventorybg");
	private MaterialsBox materials;
	
	private AddButton add;
	private ProcessButton process;
	private PatternButton pattern;
	private CraftButton craft;
	private ExitButton exit;
	
	private Forge forge;
	
	
	private Sprite slotSprite;
	
	public CraftingState(GameStateManager gsm, Forge forge) {
		super(gsm);
		blockRendering = false;
		
		slotSprite = new Sprite("slot");
		
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		this.forge = forge;
		
		pattern = new PatternButton(600 + xOffset, 294 + yOffset);
		add = new AddButton(600 + xOffset, 354 + yOffset);
		process = new ProcessButton(600 + xOffset, 414 + yOffset);
		craft = new CraftButton(600 + xOffset, 474 + yOffset);
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		add.tick(dt);
		pattern.tick(dt);
		process.tick(dt);
		craft.tick(dt);
		exit.tick(dt);
		
		if(Input.getKey(Input.SPACE) || exit.isClicked()) {
			if(gsm.next() instanceof PlayState){
				PlayState ps = (PlayState) gsm.next();
				ps.bg = false;
			}
			gsm.pop();
		}if(pattern.isClicked()){
			gsm.push(new PatternState(gsm, forge));
		}
	}

	@Override
	public void render(Screen screen){
		int xOffset =  (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		int counter = 0;
		for(int i = 1; i <= 1; i++){
			if(MaterialsBox.getMaterial(i).getAmount() > 0){
				screen.renderStaticSprite(slotSprite, 
						xOffset + 10 + counter * 60, 
						yOffset + 10);
				screen.renderStaticSprite(MaterialsBox.getMaterial(i).getMaterialImage(), 
						xOffset + 10 + counter * 60, 
						yOffset + 10);
				counter++;
			}
		}
		
		add.render(screen);
		process.render(screen);
		pattern.render(screen);
		craft.render(screen);
		exit.render(screen);
	}

}
