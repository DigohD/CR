package com.cr.states;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.core.Vertex;
import com.cr.engine.graphics.FrameBuffer;
import com.cr.engine.graphics.Mesh;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Texture;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;

public class TestState extends GameState{
	
	private Texture dirt, mask;
	private Shader shader;
	private Sprite light, grass;
	private Transform t;
	private FrameBuffer fb;
	
	Mesh mesh;

	public TestState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		
		
		dirt = new Texture("dirttest");
		mask = new Texture("masktest");
		
		t = new Transform();
		
		shader = new Shader("testVert", "testFrag");
		shader.addUniform("transformation");
		shader.addUniform("tex0");
		shader.addUniform("lightMap");
		shader.addUniform("mask");
		shader.addUniform("res");
		
		light = new Sprite("light", shader, t);
		//grass = new Sprite("grass", shader, t);
		
		fb = new FrameBuffer(256, 256, true);
		
		shader.bind();
		shader.setUniformf("res", new Vector3f(Window.getWidth(), Window.getHeight(), 0));
		shader.setUniformi("tex0", 0);
		shader.setUniformi("lightMap", 1);
		shader.setUniformi("mask", 2);
		shader.unbind();
		
		dirt.bind(1);
		mask.bind(2);
		glActiveTexture(GL_TEXTURE0);
		
		Vertex[] vertices = {new Vertex(new Vector3f(0, 0, 0), new Vector2f(0,0)), 
				 new Vertex(new Vector3f(0, 0 + 256, 0), new Vector2f(0,1)),
				 new Vertex(new Vector3f(0 + 256 , 0 + 256, 0), new Vector2f(1,1)),
				 new Vertex(new Vector3f(0 + 256, 0, 0), new Vector2f(1,0))};

		int[] indices = {0,1,2, 
			 2,3,0};

		mesh = new Mesh(vertices, indices);
	}

	@Override
	public void tick(float dt) {
		if(Input.getKey(Input.ESCAPE))
			Game.stop();
	}

	@Override
	public void render(Screen screen) {
		fb.bind();
		glBindTexture(GL_TEXTURE_2D, 0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		screen.renderSprite(light, 0, 0);
		fb.unbind();
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Game.shader.bind();
		glBindTexture(GL_TEXTURE_2D, fb.getTextureID());
		mesh.render();
		glBindTexture(GL_TEXTURE_2D, 0);
		Game.shader.unbind();
		
		
		
	}

}
