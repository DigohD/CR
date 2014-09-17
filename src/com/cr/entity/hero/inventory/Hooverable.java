package com.cr.entity.hero.inventory;

import com.cr.engine.graphics.Screen;

public interface Hooverable {
	public void renderHoover(Screen screen);
	public void setHoover(boolean bool);
	public boolean isHoover();
}
