package com.cr.states;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.stats.StatsSheet;
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class StatsState extends GameState{

	Transform t = new Transform();
	
	private Sprite bg = new Sprite("inventorybg", Game.shader, t);
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
		
		if(Input.getKey(Input.SPACE) || exit.isClicked()) {
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
	}

	@Override
	public void render(Screen screen){
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		Font f = FontLoader.aquireFont(FontColor.WHITE);
		
		int counter = 0;
		for(StatID id : StatID.values()) {
			if(Hero.getHeroSheet().getStat(id) == null)
				continue;
			if(id == StatID.HP_NOW || id == StatID.ARMOR)
				counter++;
			f.setFont(CRString.create(StatsSheet.getStatString(id) + ": " + Hero.getHeroSheet().getStat(id).getTotal()));
			f.renderFont(xOffset + 24, yOffset + (counter++ * 24), 0.2f);
		}
		
		exit.render(screen);
		FontLoader.releaseFont(f);
//		g.setColor(Color.RED);
//		g.drawString("PRESS ENTER TO RESUME", Window.getWidth()/2-100, Window.getHeight()/2);
//		g.drawString("PRESS C TO RETURN TO MAIN MENU", Window.getWidth()/2-100, Window.getHeight()/2+30);
	}

}
