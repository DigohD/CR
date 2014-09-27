package com.cr.world;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Texture;
import com.cr.engine.graphics.shader.Shader;
import com.cr.entity.enemy.test.MeleeTest;
import com.cr.game.EntityManager;
import com.cr.util.Camera;
import com.cr.util.Randomizer;
import com.cr.world.terrain.Stone;
import com.cr.world.terrain.Tree;
import com.cr.world.tile.Tile;

public class World {
	
	private int width, height;
	private int timer = 0;
	
	private TileMap map;
	private Camera camera;
	private EntityManager em;

	private static Shader shader;
	private static Transform transform;
	
	private float currentTime = 0f;
	private float targetTime = 2f;
	private float dayNightCycleTime = 100.0f;
	private float amplitudeWave = 2f;
	private float angleWave = 2.86f;
	private float angleWaveSpeed = 0.3f;
	private static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	private boolean day = true, night = false;
	private static boolean start = true;
	
	float light_theta = -10.0f;
	float light_phi = (PI2/2.0f) / 4.0f; 
	float light_r = -2000.0f; 
	
	public Vector3f sphericalToCartesian(float theta, float phi, float r){
		float x = (float) (r * Math.sin(theta) * Math.sin(phi));
		float y = (float) (r * Math.cos(phi));
		float z = (float) (r * Math.cos(theta) * Math.sin(phi));
		
		return new Vector3f(x,y,z);
	}
	
	private Vector3f lightPos, viewSpaceLightPos;
	
	private Texture normalMap;
	
	public World(){
		transform = new Transform();
		
		lightPos = sphericalToCartesian(light_theta, light_phi, light_r);
		viewSpaceLightPos = transform.getViewMatrix().mul(lightPos);//new Vector3f(-0.1f, -100, 100);
		
		normalMap = new Texture("normalMap1");
		
		shader = new Shader("phongVertShader", "phongFragShader");
		
		shader.addUniform("transformation");
		shader.addUniform("modelViewMatrix");
		shader.addUniform("time");
		shader.addUniform("sampler");
		shader.addUniform("normalMap");
		shader.addUniform("waveDataX");
		shader.addUniform("waveDataY");
		shader.addUniform("isWater");
		shader.addUniform("viewSpaceLightPos");
		
		shader.addUniform("material_shininess");
		shader.addUniform("material_diffuse_color");
		shader.addUniform("material_specular_color");
		shader.addUniform("material_emissive_color");
		
	

		shader.setUniformi("sampler", 0);
		shader.setUniformi("normalMap", 1);
		
		
		
		
		
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
				int x = Randomizer.getInt(0, width * 51) + 40;
				int y = Randomizer.getInt(0, height * 33) + t.getSprite().getSpriteHeight();
				System.out.println(t.getSprite().getSpriteHeight());
				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
					t.setPosition(new Vector2f(x - 40, y - t.getSprite().getSpriteHeight()));
					generated = true;
				}
			}
		}
		
		for(int i = 0; i < 50; i++){
			Stone s;
			boolean generated = false;
			while(!generated){
				s = new Stone(-1000, -1000);
				int x = Randomizer.getInt(0, width * 51) + 40;
				int y = Randomizer.getInt(0, height * 33) + s.getSprite().getSpriteHeight();
				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
					s.setPosition(new Vector2f(x - 40, y - s.getSprite().getSpriteHeight()));
					generated = true;
				}
			}
		}
		
//		CraftTest test = new CraftTest();
//		test.craftTest();
	}
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
		
		angleWave += dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;
		
		if(start)
			currentTime += targetTime / 80 * dt;
		
		if(currentTime >= 1f || !start){
			start = false;
			dayNightCycle(dt);
		}

		camera.tick(dt);
		em.tick(dt);
	}
	
	private void dayNightCycle(float dt){
		if(currentTime <= 2.0f && day){
			currentTime += targetTime / dayNightCycleTime * dt;
			if(currentTime > 2.0f) {
				night = true;
				day = false;
			}
		}
		
		if(night){
			currentTime -= targetTime / dayNightCycleTime * dt;
			if(currentTime <= 0.2f){
				day = true;
				night = false;
			}
		}
	}

	public void render(Screen screen) {
		shader.bind();
		
		shader.setUniformf("waveDataX", angleWave);
		shader.setUniformf("waveDataY", amplitudeWave);
		shader.setUniform("transformation", transform.getOrthoTransformation());
		shader.setUniform("modelViewMatrix", transform.getModelViewMatrix());
		shader.setUniformf("viewSpaceLightPos", viewSpaceLightPos);
		shader.setUniformf("time", currentTime);
		
//		glActiveTexture(GL_TEXTURE1);
//		glBindTexture(GL_TEXTURE_2D, normalMap.getID());
		
		map.renderMap();
		shader.unbind();
		em.render(screen);
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

	public static Shader getShader() {
		return shader;
	}

	public static Transform getTransform() {
		return transform;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
