package com.cr.states;

import java.util.ArrayList;

import com.cr.crafting.v2.pattern.Pattern;
import com.cr.crafting.v2.station.AddButton;
import com.cr.crafting.v2.station.Forge;
import com.cr.crafting.v2.station.PatternChoice;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.game.GameStateManager;

public class AmountState extends GameState{

	private Sprite bg = new Sprite("patternbg");
	
	private Sprite downarrow = new Sprite("downarrow");
	private Sprite downarrow2 = new Sprite("downarrow2");
	private Sprite uparrow = new Sprite("uparrow");
	private Sprite uparrow2 = new Sprite("uparrow2");
	
	private AddButton add;
	private ArrayList<PatternChoice> patternChoices = new ArrayList<PatternChoice>();
	
	private ArrayList<Pattern> patterns;
	
	private Forge forge;
	
	public AmountState(GameStateManager gsm, Forge forge) {
		super(gsm);
		blockRendering = false;
		
		int xOffset =  (Window.getWidth() - 150) / 2;
		int yOffset = (Window.getHeight() - 230);
		add = new AddButton(xOffset, yOffset);
		
		this.forge = forge;
		patterns = new ArrayList<Pattern>();
		forge.fillPossiblePatterns(patterns);
		
		xOffset =  (Window.getWidth() - 500) / 2;
		yOffset = (Window.getHeight() - 420) / 2;
		for(int i = 0; i < patterns.size(); i++){
			patternChoices.add(new PatternChoice(xOffset + 10 + (i * 90), yOffset + 10, patterns.get(i)));
		}
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		add.tick(dt);
		
		for(PatternChoice x : patternChoices){
			x.tick(dt);
			if(x.isClicked()){
				forge.setPattern(x.getPattern());
				if(gsm.next() instanceof CraftingState){
					CraftingState cs = (CraftingState) gsm.next();
				}
				gsm.pop();
			}
		}
		
		if(Input.getKey(Input.SPACE) || add.isClicked()) {
			if(gsm.next() instanceof CraftingState){
				CraftingState cs = (CraftingState) gsm.next();
			}
			gsm.pop();
		}
	}

	@Override
	public void render(Screen screen) {
		int xOffset =  (Window.getWidth() - 500) / 2;
		int yOffset = (Window.getHeight() - 420) / 2;
		
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		for(PatternChoice x : patternChoices)
			x.render(screen);
		
		add.render(screen);
	}
	
}
