package com.cr.states;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.station.AddButton;
import com.cr.crafting.v2.station.DownArrow;
import com.cr.crafting.v2.station.DownArrow2;
import com.cr.crafting.v2.station.Forge;
import com.cr.crafting.v2.station.UpArrow;
import com.cr.crafting.v2.station.UpArrow2;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.game.GameStateManager;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class AmountState extends GameState{

	private Sprite bg = new Sprite("patternbg");
	
	private AddButton add;
	
	private DownArrow down1;
	private DownArrow2 down2;
	private UpArrow up1;
	private UpArrow2 up2;
	
	private int amount;
	
	private Material activeMaterial;
	
	private Font whiteFont;
	
	private Forge forge;
	
	public AmountState(GameStateManager gsm, Forge forge, Material activeMaterial) {
		super(gsm);
		blockRendering = false;
		
		amount = 1;
		
		whiteFont = FontLoader.aquireFont(FontColor.WHITE);
		
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
		if(add.isClicked()) {
			if(gsm.next() instanceof CraftingState){
				CraftingState cs = (CraftingState) gsm.next();
			}
			
			add.removeFromInput();
			down1.removeFromInput();
			down2.removeFromInput();
			up1.removeFromInput();
			up2.removeFromInput();
			
			FontLoader.releaseFont(whiteFont);
			
			forge.addMaterial(activeMaterial, amount);
			
			gsm.pop();
		}
		
		if(down1.isClicked() && amount > 1){
			amount = amount - 1;
		}else if(down2.isClicked() && amount > 5){
			amount = amount - 5;
		}else if(up1.isClicked() && amount < activeMaterial.getAmount()){
			amount = amount + 1;
		}else if(up2.isClicked() && amount < activeMaterial.getAmount() - 5){
			amount = amount + 5;
		}
		
	}

	@Override
	public void render(Screen screen) {
		int xOffset =  (Window.getWidth() - 500) / 2;
		int yOffset = (Window.getHeight() - 420) / 2;
		
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		down1.render(screen);
		down2.render(screen);
		up1.render(screen);
		up2.render(screen);
		
		xOffset =  (Window.getWidth()) / 2;
		yOffset = (Window.getHeight()) / 2;
		
		whiteFont.setFont(CRString.create(activeMaterial.getName()));
		whiteFont.renderFont(xOffset - 115, yOffset - 80, 0.2f);
		
		whiteFont.setFont(CRString.create(amount + ""));
		whiteFont.renderFont(xOffset - 60, yOffset - 40, 0.5f);
		
		add.render(screen);
	}
	
}
