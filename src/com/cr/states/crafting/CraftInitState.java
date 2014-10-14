package com.cr.states.crafting;

import java.util.ArrayList;

import com.cr.crafting.v2.pattern.Pattern;
import com.cr.crafting.v2.station.Forge;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.Button;
import com.cr.game.GameStateManager;
import com.cr.states.GameState;

public class CraftInitState extends GameState{
	
	private Button pattern;
	private Button exit;
	
	private ArrayList<Pattern> patterns;
	
	private Forge forge;
	
	public CraftInitState(GameStateManager gsm, Forge forge) {
		super(gsm);
		blockRendering = false;
		
		int xOffset =  (Window.getWidth() - 150) / 2;
		int yOffset = (Window.getHeight() / 2);
		
		exit = new Button("exit", xOffset, yOffset);
		pattern = new Button("patternbutton", xOffset, yOffset - 40);
		
		this.forge = forge;
		patterns = new ArrayList<Pattern>();
		forge.fillPossiblePatterns(patterns);
		
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
			
			pattern.removeFromInput();
			exit.removeFromInput();
			gsm.pop();
		}if(pattern.isClicked()) {
			if(gsm.next() instanceof CraftingState){
				CraftingState cs = (CraftingState) gsm.next();
			}
			
			pattern.removeFromInput();
			exit.removeFromInput();
			gsm.pop();
			
			gsm.push(new PatternState(gsm, forge));
		}
	}

	@Override
	public void render(Screen screen) {
		int xOffset =  (Window.getWidth() - 500) / 2;
		int yOffset = (Window.getHeight() - 420) / 2;
		
//		for(PatternChoice x : patternChoices)
//			x.render(screen);
		
		pattern.render(screen);
		exit.render(screen);
	}
	
}
