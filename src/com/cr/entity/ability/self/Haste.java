package com.cr.entity.ability.self;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Mob;
import com.cr.entity.Renderable;
import com.cr.entity.effect.TimedEffect;
import com.cr.entity.emitter.Particle;
import com.cr.stats.StatsSheet.StatID;
import com.cr.util.Randomizer;

public class Haste extends SelfAbility{
	private class HasteEffect extends TimedEffect{
		
		private final Sprite sprite = new Sprite("white1");
		
		public HasteEffect(){
			super(300, 1, source, source);
		}

		@Override
		public void applyEffect() {
			source.getSheet().getStat(StatID.MOVEMENT_SPEED).addMulmod("haste", 2f);
		}

		@Override
		public void removeEffect() {
			source.getSheet().getStat(StatID.MOVEMENT_SPEED).removeMulmod("haste");
		}

		@Override
		protected void particles() {
			Rectangle rect = owner.getRect();
			
			int x = Randomizer.getInt(rect.x, rect.x + rect.width);
			int y = Randomizer.getInt(rect.y, rect.y + rect.height);
			
			new Particle(new Vector2f(x, y), owner.getVelocity().rotate(180), sprite, 25);
		}
	}
	
	public Haste(Mob source, float cooldown) {
		super("Haste", source, cooldown);
	}

	@Override
	protected void execute(Mob target) {
		new HasteEffect();
	}

	@Override
	public boolean isAIActivate() {
		return source.isMoving();
	}

}
