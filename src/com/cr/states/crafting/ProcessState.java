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
	private Sprite bg = new Sprite("processbg", Game.shader, t);
	private Sprite slider = new Sprite("slider", Game.shader, t);
	
	private ProcessButton process;
	private SliderArrow heatArrow, timeArrow;
	
	private int heat, time;
	
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
		
		process = new ProcessButton(xOffset + 30, yOffset - 170);
		
		xOffset =  (Window.getWidth()) / 2;
		yOffset = ((Window.getHeight()) / 2) - 160;
		
		heatArrow = new SliderArrow(xOffset - 220, yOffset + 50);
		timeArrow = new SliderArrow(xOffset - 220, yOffset + 150);
		
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
			gsm.push(new ChooseSecState(gsm, forge));
			gsm.push(new ProcessReportState(gsm, forge));
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
		
		screen.renderStaticSprite(slider, xOffset - 220, yOffset + 60);
		heatArrow.render(screen);
		
		whiteFont.setFont(CRString.create("Time"));
		whiteFont.renderFont(xOffset - 80, yOffset + 100, 0.3f);
		
		time = (int) (((forge.getMaxTime() - forge.getMinTime()) * timeArrow.getPercent()) + forge.getMinTime());
		whiteFont.setFont(CRString.create(time + ""));
		whiteFont.renderFont(xOffset + 20, yOffset + 100, 0.3f);
		
		screen.renderStaticSprite(slider, xOffset - 220, yOffset + 160);
		timeArrow.render(screen);
		
		process.render(screen);
	}
	
}
