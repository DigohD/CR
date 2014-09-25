package com.cr.world;

import com.cr.crafting.v2.test.CraftTest;
import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Screen;
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
	private Transform transform;
	
	private float time = 0f;
	private float dayNightCycleTime = 100.0f;
	private float targetTimeMax = 2f;
	private float targetTimeMin = 0.2f;
	
	private boolean day = true, night = false;
	public static boolean start = true;
	

	float light_theta = 0.0f;
	float light_phi = 3.14f/4.0f; 
	float light_r = 20.0f; 
	
	public Vector3f sphericalToCartesian(float theta, float phi, float r){
		float x = (float) (r * Math.sin(theta) * Math.sin(phi));
		float y = (float) (r * Math.cos(phi));
		float z = (float) (r * Math.cos(theta) * Math.sin(phi));
		
		return new Vector3f(x,y,z);
	}
	
	private Vector3f lightPos;
	private Vector3f viewSpaceLightPos;
	
	public World(){
		transform = new Transform();
		
		
		
		shader = new Shader("vertexshader", "fragmentshader");
		
		shader.addUniform("transformation");
		shader.addUniform("modelViewMatrix");
		shader.addUniform("normalMatrix");
	

		shader.addUniform("time");
		shader.addUniform("sampler");
		shader.setUniformi("sampler", 0);
		
		
		map = new TileMap(100, 100);

		width = map.getWidth();
		height = map.getHeight();
		
		em = new EntityManager(this);
		camera = new Camera();
		
		lightPos = sphericalToCartesian(light_theta, light_phi, light_r);
		viewSpaceLightPos = transform.getViewMatrix().mul(lightPos);
		
		System.out.println(viewSpaceLightPos.toString());
		
		shader.addUniform("viewSpaceLightPos");
		shader.setUniformf("viewSpaceLightPos", viewSpaceLightPos);
		
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
		shader.setUniform("modelViewMatrix", transform.getModelViewMatrix());
	
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
