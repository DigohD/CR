package com.cr.world.terrain;

import java.awt.Rectangle;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Material;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.entity.Entity;
import com.cr.entity.Renderable;
import com.cr.world.World;

public abstract class WorldObject extends Entity implements Renderable{
	
	protected Sprite sprite;
	protected Rectangle rect;
	protected Material material;

	public WorldObject(Vector2f position) {
		super(position);
		material = new Material();
	}
	
	public abstract void init();
	public abstract void activate();
	

	public void updateRect(){
		rect.setLocation((int)position.x, (int)position.y);
	}

	@Override
	public void render(Screen screen) {
		World.getShader().bind();
		World.getShader().setUniformf("material_shininess", material.getMaterialShininess());
		World.getShader().setUniformf("material_diffuse_color", material.getDiffuseColor());
		World.getShader().setUniformf("material_specular_color", material.getSpecularColor());
		World.getShader().setUniformf("material_emissive_color", material.getEmissiveColor());
		World.getShader().unbind();
		screen.renderSprite(sprite, position.x, position.y);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}

}
