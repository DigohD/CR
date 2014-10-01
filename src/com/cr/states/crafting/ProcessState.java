package com.cr.states.crafting;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.station.Forge;
import com.cr.crafting.v2.station.ProcessButton;
import com.cr.crafting.v2.station.SliderArrow;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.states.GameState;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class ProcessState extends GameState{

	Transform t = new Transform();
	private Sprite bg = new Sprite("patternbg", Game.shader, t);
	private Sprite slider = new Sprite("slider", Game.shader, t);
	
	private ProcessButton process;
	private SliderArrow heatArrow, timeArrow;
	
	private int heat, time, heatArrowX, timeArrowX;
	
	private Material activeMaterial;
	
	private Font whiteFont;
	
	private Forge forge;
	
	public ProcessState(GameStateManager gsm, Forge forge) {
		super(gsm);
		blockRendering = false;
		
		heat = 0;
		time = 0;
		
		whiteFont = FontLoader.aquireFont(FontColor.WHITE);
		
		int xOffset =  (Window.getWidth() - 150) / 2;
		int yOffset = (Window.getHeight() - 230);
		
		process = new ProcessButton(xOffset, yOffset);
		
		xOffset =  (Window.getWidth()) / 2;
		yOffset = ((Window.getHeight()) / 2) - 160;
		
		heatArrow = new SliderArrow(xOffset - 220, yOffset + 70);
		timeArrow = new SliderArrow(xOffset - 220, yOffset + 170);
		
		this.forge = forge;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {		
		if(process.isClicked()) {
			if(gsm.next() instanceof CraftingState){
				CraftingState cs = (CraftingState) gsm.next();
			}
			
			process.removeFromInput();
			heatArrow.removeFromInput();
			FontLoader.releaseFont(whiteFont);
			
			forge.setHeat(heat);
			forge.setTime(time);
			forge.process();
			
			gsm.pop();
		}
		
	}

	@Override
	public void render(Screen screen) {
		int xOffset =  (Window.getWidth() - 500) / 2;
		int yOffset = (Window.getHeight() - 420) / 2;
		
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		xOffset =  (Window.getWidth()) / 2;
		yOffset = ((Window.getHeight()) / 2) - 160;
		
		whiteFont.setFont(CRString.create("Heat"));
		whiteFont.renderFont(xOffset - 80, yOffset, 0.3f);
		
		heat = (int) (((forge.getMaxHeat() - forge.getMinHeat()) * heatArrow.getPercent()) + forge.getMinHeat());
		whiteFont.setFont(CRString.create(heat + ""));
		whiteFont.renderFont(xOffset + 20, yOffset, 0.3f);
		
		screen.renderStaticSprite(slider, xOffset - 220, yOffset + 80);
		heatArrow.render(screen);
		
		whiteFont.setFont(CRString.create("Time"));
		whiteFont.renderFont(xOffset - 80, yOffset + 100, 0.3f);
		
		time = (int) (((forge.getMaxTime() - forge.getMinTime()) * timeArrow.getPercent()) + forge.getMinTime());
		whiteFont.setFont(CRString.create(time + ""));
		whiteFont.renderFont(xOffset + 20, yOffset + 100, 0.3f);
		
		screen.renderStaticSprite(slider, xOffset - 220, yOffset + 180);
		timeArrow.render(screen);
		
		process.render(screen);
	}
	
}
