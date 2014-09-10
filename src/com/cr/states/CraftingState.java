package com.cr.states;

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
	private ExitButton exit;
	
	private Sprite slotSprite;
	
	public CraftingState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
		
		slotSprite = new Sprite("slot");
		
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		exit.tick(dt);
		
		if(Input.getKey(Input.SPACE) || exit.isClicked()) {
			if(gsm.next() instanceof PlayState){
				PlayState ps = (PlayState) gsm.next();
				ps.bg = false;
			}
			gsm.pop();
		}
	}

	@Override
	public void render(Screen screen){
		int xOffset =  (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		int counter = 0;
		for(int i = 1; i <= 1; i++){
			System.out.println(MaterialsBox.getMaterial(i));
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
		
		exit.render(screen);
	}

}
