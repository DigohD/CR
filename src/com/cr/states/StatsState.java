package com.cr.states;

import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.entity.hero.HeroSheet;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;

public class StatsState extends GameState{

	private Sprite bg = new Sprite("inventorybg");
	private ExitButton exit;
	
	public StatsState(GameStateManager gsm) {
		super(gsm);
		blockRendering = false;
		
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
		
		if(KeyInput.space || exit.isClicked()) {
			if(gsm.next() instanceof PlayState){
				PlayState ps = (PlayState) gsm.next();
		
			}
			gsm.pop();
		}
		
//		if(KeyInput.enter) {
//			if(gsm.next() instanceof PlayState){
//				PlayState ps = (PlayState) gsm.next();
//				ps.bg = false;
//			}
//			gsm.pop();
//		}
//		if(KeyInput.c) {
//			gsm.pop();
//			EntityManager.clear();
//			gsm.pop();
//		}
	}

	@Override
	public void render(Screen screen){
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		screen.renderSprite(bg, xOffset, yOffset);
		
		HeroSheet.render(screen);
		
		exit.render(screen);
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Window.getWidth()/2-100, Window.getHeight()/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Window.getWidth()/2-100, Window.getHeight()/2+30);
	}

}
