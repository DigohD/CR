package com.cr.entity.ability.self;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Mob;
import com.cr.entity.Renderable;
import com.cr.entity.ability.Ability;
import com.cr.entity.effect.TimedEffect;
import com.cr.entity.emitter.Particle;
import com.cr.game.EntityManager;
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.Randomizer;

public class WispPrison extends Ability{
	private class WispPrisonEffect extends TimedEffect{
		
		private final Sprite sprite = new Sprite("wispp");
		
		public WispPrisonEffect(Mob target){
			super(150, 1, target, source);
		}

		@Override
		public void applyEffect() {
			owner.getSheet().getStat(StatID.MOVEMENT_SPEED).addMulmod("wispprison", -0.8f);
		}

		@Override
		public void removeEffect() {
			owner.getSheet().getStat(StatID.MOVEMENT_SPEED).removeMulmod("wispprison");
		}

		@Override
		protected void particles() {
			Rectangle rect = owner.getRect();
			
			int x = Randomizer.getInt(rect.x, rect.x + rect.width);
			int y = Randomizer.getInt(rect.y, rect.y + rect.height);
			
			new Particle(new Vector2f(x, y), owner.getVelocity(), sprite, 25);
		}
	}
	
	public WispPrison(Mob source, float cooldown) {
		super("Wisp Prison", source, cooldown);
	}

	@Override
	protected void execute(Mob target) {
		new WispPrisonEffect(target);
	}

	@Override
	public boolean isAIActivate() {
		return source.isMoving();
	}

	@Override
	protected Mob findTarget() {
		Vector2f range = source.getCenterPos().sub(EntityManager.getHero().getCenterPos());
		float length = Math.abs(range.length());
		
		System.out.println("SPELL " + length);
		
		if(length < 3000)
			return EntityManager.getHero();
		return null;
	}

}
