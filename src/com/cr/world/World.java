package com.cr.world;

import com.cr.crafting.v2.test.CraftTest;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
import com.cr.entity.enemy.test.MeleeTest;
import com.cr.game.EntityManager;
import com.cr.util.Camera;
import com.cr.util.Randomizer;
import com.cr.world.terrain.Stone;
import com.cr.world.terrain.Tree;
import com.cr.world.tile.GrassTile;
import com.cr.world.tile.Tile;

public class World {
	
	private int width, height;
	private int timer = 0;
	
	private TileMap map;
	private Camera camera;
	private EntityManager em;

	private static Shader shader;
	
	private float time = 0f;
	private float dayNightCycleTime = 100.0f;
	private float targetTimeMax = 2f;
	private float targetTimeMin = 0.2f;
	
	private boolean day = true, night = false;
	public static boolean start = true;
	
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
//		new MeleeTest(new Vector2f(400, 400), this);
//		new MeleeTest(new Vector2f(400, 400), this);
		new MeleeTest(new Vector2f(400, 400), this);
		
		for(int i = 0; i < 100; i++){
			Tree t;
			boolean generated = false;
			while(!generated){
				t = new Tree(-1000, -1000);
				int x = Randomizer.getInt(0, 5800) + 40;
				int y = Randomizer.getInt(0, 3800) + t.getSprite().getSpriteHeight();
				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
					t.setPosition(new Vector2f(x, y));
					generated = true;
				}
			}
		}
		
		for(int i = 0; i < 50; i++){
			Stone t;
			boolean generated = false;
			while(!generated){
				t = new Stone(-1000, -1000);
				int x = Randomizer.getInt(0, 5800) + 40;
				int y = Randomizer.getInt(0, 3800) + t.getSprite().getSpriteHeight();
				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
					t.setPosition(new Vector2f(x, y));
					generated = true;
				}
			}
		}
		
//		CraftTest test = new CraftTest();
//		test.craftTest();
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
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(start)
			time += targetTimeMax / 80 * dt;
		
		if(time >= 1f || !start){
			start = false;
			dayNightCycle(dt);
			map.tick(dt);
		}

		camera.tick(dt);
		em.tick(dt);
	}
	
	private void dayNightCycle(float dt){
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
	}

	public void render(Screen screen) {
		shader.bind();
		shader.setUniformf("time", time);
		shader.unbind();
		map.renderMap(screen);
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
