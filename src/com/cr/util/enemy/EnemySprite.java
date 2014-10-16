package com.cr.util.enemy;

import com.cr.engine.graphics.Sprite;

public class EnemySprite {

	private String spriteName;
	private int rows, columns;
	
	public EnemySprite(String spriteName, int rows, int columns) {
		super();
		this.spriteName = spriteName;
		this.rows = rows;
		this.columns = columns;
	}

	public String getSpriteName() {
		return spriteName;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	
	
}
