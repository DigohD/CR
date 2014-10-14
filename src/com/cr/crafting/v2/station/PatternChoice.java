package com.cr.crafting.v2.station;

import java.awt.Rectangle;

import com.cr.crafting.v2.pattern.Pattern;
import com.cr.entity.hero.inventory.Button;

public class PatternChoice extends Button{
	
	private Pattern pattern;

	public PatternChoice(int xPos, int yPos, Pattern pattern) {
		super(xPos, yPos);
		sprite = pattern.getSprite();
		rect = new Rectangle(xPos, yPos, sprite.getSpriteWidth(), sprite.getSpriteHeight());
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

}
