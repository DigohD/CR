package com.cr.states.crafting;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.station.Forge;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.entity.hero.inventory.Button;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.states.GameState;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class AmountState extends GameState{

	private Sprite bg = new Sprite("amountbg", Game.shader, new Transform());
	
	private Button add;
	
	private Button down1;
	private Button down2;
	private Button up1;
	private Button up2;
	
	private int amount;
	
	private Material activeMaterial;
	
	private Font whiteFont;
	
	private Forge forge;
	
	public AmountState(GameStateManager gsm, Forge forge, Material activeMaterial) {
		super(gsm);
		blockRendering = false;
		
		amount = 1;
		
		whiteFont = FontLoader.aquireFont(FontColor.WHITE);
		
		int xOffset =  (Window.getWidth() - 240) / 2;
		int yOffset = (Window.getHeight() - 200) / 2;
		add = new Button("addbutton", xOffset + 70, yOffset + 135);
		
		this.activeMaterial = activeMaterial;
		
		xOffset =  (Window.getWidth() - 40) / 2;
		yOffset = (Window.getHeight() - 40) / 2;
		
		down1 = new Button("downarrow", xOffset - 48, yOffset);
		down2 = new Button("downarrow2", xOffset - 80, yOffset);
		up1 = new Button("uparrow", xOffset + 48, yOffset);
		up2 = new Button("uparrow2", xOffset + 90, yOffset);
		
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
			gsm.push(new ProcessState(gsm, forge));
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
		int xOffset =  (Window.getWidth() - 240) / 2;
		int yOffset = (Window.getHeight() - 200) / 2;
		
		screen.renderStaticSprite(bg, xOffset + 2, yOffset);
		
		down1.render(screen);
		down2.render(screen);
		up1.render(screen);
		up2.render(screen);
		
		xOffset =  (Window.getWidth()) / 2;
		yOffset = (Window.getHeight()) / 2;
		
		whiteFont.setFont(CRString.create(activeMaterial.getName()));
		whiteFont.renderFont(xOffset - 100, yOffset - 100, 0.3f);
		
		whiteFont.setFont(CRString.create(amount + ""));
		whiteFont.renderFont(xOffset - 34, yOffset - 80, 0.5f);
		
		add.render(screen);
	}
	
}
