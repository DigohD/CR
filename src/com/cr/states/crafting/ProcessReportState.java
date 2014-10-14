package com.cr.states.crafting;

import com.cr.crafting.v2.station.Forge;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.Button;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.states.GameState;

public class ProcessReportState extends GameState{

	private Sprite bg = new Sprite("patternbg", Game.shader, new Transform());
	private Button exit;
	
	private Forge forge;
	
	public ProcessReportState(GameStateManager gsm, Forge forge) {
		super(gsm);
		blockRendering = false;
		
		int xOffset =  (Window.getWidth() - 150) / 2;
		int yOffset = (Window.getHeight() - 230);
		exit = new Button("exit", xOffset, yOffset);
		
		this.forge = forge;
		
		xOffset =  (Window.getWidth() - 500) / 2;
		yOffset = (Window.getHeight() - 420) / 2;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		//exit.tick(dt);
		
		if(Input.getKey(Input.SPACE) || exit.isClicked()) {
			if(gsm.next() instanceof CraftingState){
				CraftingState cs = (CraftingState) gsm.next();
			}
			exit.removeFromInput();
			gsm.pop();
		}
	}

	@Override
	public void render(Screen screen) {
		int xOffset =  (Window.getWidth() - 500) / 2;
		int yOffset = (Window.getHeight() - 420) / 2;
		
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		forge.renderProcess(screen, xOffset + 40, yOffset);
		
		exit.render(screen);
	}
	
}
