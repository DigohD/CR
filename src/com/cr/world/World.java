package com.cr.world;

import com.cr.crafting.v2.test.CraftTest;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
import com.cr.entity.enemy.test.MeleeTest;
import com.cr.game.EntityManager;
import com.cr.util.Camera;
import com.cr.world.tile.Tile;

public class World {
	
	private int width, height;
	private int timer = 0;
	
	private TileMap map;
	private Camera camera;
	private EntityManager em;

	private static Shader shader;
	
	private float time = 1f;
	private float dayNightCycleTime = 100.0f;
	
	public World(){
		shader = new Shader("vertexshader", "fragmentshader");
		
		shader.addUniform("transformation");
		shader.addUniform("time");
		shader.addUniform("sampler");
		shader.setUniformi("sampler", 0);
		
		map = new TileMap(100, 100);

		width = map.getWidth();
		height = map.getHeight();
		
		em = new EntityManager(this);
		camera = new Camera();
		
//		new LootEmitter(new Vector2f(200,200), 5000);
		
//		RangedTest dummy = new RangedTest(new Vector2f(400, 400), this);
		new MeleeTest(new Vector2f(400, 400), this);
		new MeleeTest(new Vector2f(400, 400), this);
		new MeleeTest(new Vector2f(400, 400), this);
		
		CraftTest test = new CraftTest();
		test.craftTest();
	}
	
	public boolean tileExists(int xp, int yp){
		if(map.getTopLayer().tileExists(xp, yp) || map.getMiddleLayer().tileExists(xp, yp) 
				|| map.getBottomLayer().tileExists(xp, yp))
			return true;
		return false;
	}
	
	public Tile getTile(int xp, int yp){
		if(map.getTopLayer().getBitmap().getPixel(xp, yp) != 0){
			return map.getTopLayer().getTile(xp, yp);
		}else if(map.getMiddleLayer().getBitmap().getPixel(xp, yp) != 0){
			return map.getMiddleLayer().getTile(xp, yp);
		}else if(map.getBottomLayer().getBitmap().getPixel(xp, yp) != 0){
			return map.getBottomLayer().getTile(xp, yp);
		}
			
		return null;
	}
	
	float targetTimeMax = 2f;
	float targetTimeMin = 0.2f;
	
	boolean day = true, night = false;
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(time <= 2.0f && day){
			time += targetTimeMax / dayNightCycleTime * dt;
			if(time > 2.0f) {
				night = true;
				day = false;
			}
		}
		
		if(night){
			time -= targetTimeMax / dayNightCycleTime * dt;
			if(time <= 0.2f){
				day = true;
				night = false;
			}
		}
	
		camera.tick(dt);
		em.tick(dt);
	}

	public void render(Screen screen) {
		shader.bind();
		shader.setUniformf("time", time);
		shader.unbind();
		map.renderMap();
		em.render(screen);
	}

	public static Shader getShader() {
		return shader;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
