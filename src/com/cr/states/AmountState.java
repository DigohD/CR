package com.cr.states;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.pattern.Pattern;
import com.cr.crafting.v2.station.AddButton;
import com.cr.crafting.v2.station.DownArrow;
import com.cr.crafting.v2.station.DownArrow2;
import com.cr.crafting.v2.station.Forge;
import com.cr.crafting.v2.station.PatternChoice;
import com.cr.crafting.v2.station.UpArrow;
import com.cr.crafting.v2.station.UpArrow2;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.game.GameStateManager;

public class AmountState extends GameState{

	private Sprite bg = new Sprite("patternbg");
	
	private AddButton add;
	
	private DownArrow down1;
	private DownArrow2 down2;
	private UpArrow up1;
	private UpArrow2 up2;
	
	private int amount;
	
	private Material activeMaterial;
	
	private Forge forge;
	
	public AmountState(GameStateManager gsm, Forge forge, Material activeMaterial) {
		super(gsm);
		blockRendering = false;
		
		amount = 1;
		
		int xOffset =  (Window.getWidth() - 150) / 2;
		int yOffset = (Window.getHeight() - 230);
		add = new AddButton(xOffset, yOffset);
		
		this.activeMaterial = activeMaterial;
		
		xOffset =  (Window.getWidth() - 40) / 2;
		yOffset = (Window.getHeight() - 40) / 2;
		
		down1 = new DownArrow(xOffset - 60, yOffset);
		down2 = new DownArrow2(xOffset - 110, yOffset);
		up1 = new UpArrow(xOffset + 60, yOffset);
		up2 = new UpArrow2(xOffset + 110, yOffset);
		
		this.forge = forge;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		add.tick(dt);
		
		if(down1.isClicked() && amount > 1){
			amount -= 1;
		}if(down2.isClicked() && amount > 5){
			amount -= 5;
		}if(up1.isClicked() && amount < activeMaterial.getAmount()){
			amount += 1;
		}if(up2.isClicked() && amount < activeMaterial.getAmount() - 5){
			amount += 5;
		}
		
		down1.tick(dt);
		down2.tick(dt);
		up1.tick(dt);
		up2.tick(dt);
		
		if(add.isClicked()) {
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
		
		Font amountFont = new Font("" + amount, FontColor.YELLOW, false);
		
		down1.render(screen);
		down2.render(screen);
		up1.render(screen);
		up2.render(screen);
		
		xOffset =  (Window.getWidth()) / 2;
		yOffset = (Window.getHeight()) / 2;
		
		amountFont.renderFont(xOffset - 30, yOffset - 100, 0.5f);
		
		add.render(screen);
	}
	
}
