package com.cr.entity;

import java.awt.Rectangle;

public interface Collideable {

	public void collisionWith(Collideable obj);
	public Rectangle getRect();

}
