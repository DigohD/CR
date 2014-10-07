package com.cr.states;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Texture;
import com.cr.engine.graphics.shader.Shader;
import com.cr.game.GameStateManager;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;

public class TestState extends GameState{
	
	private Texture dirt, mask;
	private Shader shader;
	private Sprite grass;
	private Transform t;

	public TestState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		//grass = new Texture("grasstest");
		dirt = new Texture("dirttest");
		mask = new Texture("masktest");
		
		t = new Transform();
		
		shader = new Shader("testVert", "testFrag");
		shader.addUniform("transformation");
		shader.addUniform("tex0");
		shader.addUniform("tex1");
		shader.addUniform("mask");
		
		grass = new Sprite("grasstest", shader, t);
		
		shader.bind();
		shader.setUniformi("tex0", 0);
		shader.setUniformi("tex1", 1);
		shader.setUniformi("mask", 2);
		shader.unbind();
		
		dirt.bind(1);
		mask.bind(2);
		glActiveTexture(GL_TEXTURE0);
		
	}

	@Override
	public void tick(float dt) {
		
		
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(grass, 0, 0);
		
	}

}
