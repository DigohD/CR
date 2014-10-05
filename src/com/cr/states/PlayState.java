package com.cr.states;

import com.cr.crafting.v2.station.Forge;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.input.Input;
import com.cr.entity.hero.Hero;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.states.crafting.CraftInitState;
import com.cr.states.inventory.InventoryState;
import com.cr.stats.StatsSheet.StatID;
import com.cr.world.World;

public class PlayState extends GameState{

	private static World w;
	

	private Sprite healthBar, health;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	@Override
	public void init() {
		w = new World();
		healthBar = new Sprite("flatdarkred");
		health = new Sprite("flatgreen");
	}
	
	@Override
	public void tick(float dt) {
		if(Input.getKey(Input.C))
			gsm.push(new InventoryState(gsm));
		if(Input.getKey(Input.M))
			gsm.push(new CraftInitState(gsm, new Forge()));
		if(Input.getKey(Input.Q))
			gsm.push(new StatsState(gsm));
		if(Input.getKey(Input.ESCAPE))
			Game.stop();
		
		w.tick(dt);
	}

	@Override
	public void render(Screen screen) {
		w.render(screen);
		
		screen.renderStaticSprite(healthBar, 10, 10, 14, 2);
		
		float percent = Hero.getHeroSheet().getStat(StatID.HP_NOW).getTotal() / Hero.getHeroSheet().getStat(StatID.HP_MAX).getTotal();
		float scaleX = 14 * percent;
		
		screen.renderStaticSprite(healthBar, 10, 10, 14, 2);
		screen.renderStaticSprite(health, 10, 10, scaleX, 2);
	}

	public static World getWorld() {
		return w;
	}

}
