package com.cr.world;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Texture;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
import com.cr.entity.enemy.test.MeleeTest;
import com.cr.entity.enemy.wisp.Wisp;
import com.cr.game.EntityManager;
import com.cr.game.Game;
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
	
	private float t = 0;
	private float currentTime = 0.2f;
	private float targetTime = 2f;
	private float dayNightCycleTime = 10.0f;
	private float amplitudeWave = 2f;
	private float angleWave = 2.86f;
	private float angleWaveSpeed = 0.2f;
	private static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	private boolean day = true, night = false;
	private static boolean start = true;
	
	private Vector3f lightPosition, ambientLight, eyePosition;
	
	public World(){
		transform = new Transform();
		
		lightPosition = transform.getModelMatrix().mul(new Vector3f(Window.getWidth()/2, Window.getHeight()/2, -100));
		ambientLight = new Vector3f(0.2f, 0.2f, 0.2f);
		

		shader = new Shader("phongVertShader", "phongFragShader");
		
		shader.addUniform("transformation");
		shader.addUniform("modelMatrix");
		shader.addUniform("time");
		shader.addUniform("sampler");
		shader.addUniform("waveDataX");
		shader.addUniform("waveDataY");
		shader.addUniform("isWater");
		shader.addUniform("lightPosition");
		shader.addUniform("scene_ambient_light");
		shader.addUniform("eyePosition");
		
		shader.addUniform("material_shininess");
		shader.addUniform("material_diffuse_color");
		shader.addUniform("material_specular_color");
		shader.addUniform("material_emissive_color");
		
		shader.setUniformi("sampler", 0);

		
		map = new TileMap(250, 250);

		width = map.getWidth();
		height = map.getHeight();
		
		em = new EntityManager(this);
		camera = new Camera();
		
		eyePosition = Camera.getPos();
		
//		new LootEmitter(new Vector2f(200,200), 5000);
		
//		RangedTest dummy = new RangedTest(new Vector2f(400, 400), this);

		for(int i = 0; i < 10; i++){
			Wisp e = null;
			boolean generated = false;
			while(!generated){
				e = new Wisp(new Vector2f(-1000, -1000), this);
				int x = Randomizer.getInt(0, width * Tile.getTileWidth()) + 40;
				int y = Randomizer.getInt(0, height * Tile.getTileHeight()) + e.getSprite().getSpriteHeight();
				//System.out.println(e.getSprite().getSpriteHeight());
				if(map.getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
					e.setPosition(new Vector2f(x - 40, y - e.getSprite().getSpriteHeight()));
					generated = true;
				}
			}
		}
		
		
		for(int i = 0; i < 250; i++){
			Tree t;
			boolean generated = false;
			while(!generated){
				t = new Tree(-1000, -1000);
				int x = Randomizer.getInt(0, width * Tile.getTileWidth()) + 40;
				int y = Randomizer.getInt(0, height * Tile.getTileHeight()) + t.getSprite().getSpriteHeight();
				//System.out.println(t.getSprite().getSpriteHeight());
				if(map.getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
					t.setPosition(new Vector2f(x - 40, y - t.getSprite().getSpriteHeight()));
					t.updateRect();
					generated = true;
				}
			}
		}
		
		for(int i = 0; i < 200; i++){
			Stone s;
			boolean generated = false;
			while(!generated){
				s = new Stone(-1000, -1000);
				int x = Randomizer.getInt(0, width * Tile.getTileWidth()) + 40;
				int y = Randomizer.getInt(0, height * Tile.getTileHeight()) + s.getSprite().getSpriteHeight();
				if(map.getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
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
		
		//dayNightCycle(dt);
		
		angleWave += dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;
		
//		if(start)
//			currentTime += targetTime / 80 * dt;
//		
//		if(currentTime >= 1f || !start){
//			start = false;
//			//dayNightCycle(dt);
//		}

		camera.tick(dt);
		em.tick(dt);
	}
	
	private void dayNightCycle(float dt){
		t += (dt*angleWaveSpeed*0.3f)/ dayNightCycleTime;
		
		if(t >= PI2) t = 0;
		lightPosition = lightPosition.rotate(new Vector3f(0,1,0), (t*dt*angleWaveSpeed*0.3f)/ dayNightCycleTime);
		
		if(currentTime <= 1.2f && day){
			currentTime += (dt*angleWaveSpeed*0.3f)/dayNightCycleTime;
			if(currentTime > 1.2f || (t >= PI2/2 && t <= PI2)) {
				night = true;
				day = false;
			}
		}
		
		if(night){
			currentTime -= (dt*angleWaveSpeed*0.3f)/dayNightCycleTime;
			if(currentTime <= 0.18f || (t >= 0 && t <= PI2/2) ){
				day = true;
				night = false;
			}
		}
		
		ambientLight.x = currentTime;
		ambientLight.y = currentTime;
		ambientLight.z = currentTime;
	}

	public void render(Screen screen) {
		shader.bind();
		
		shader.setUniformf("waveDataX", angleWave);
		shader.setUniformf("waveDataY", amplitudeWave);
		shader.setUniform("transformation", transform.getOrthoTransformation());
		shader.setUniform("modelMatrix", transform.getModelMatrix());
		shader.setUniformf("lightPosition", lightPosition);
		shader.setUniformf("scene_ambient_light", ambientLight);
		shader.setUniformf("time", t);
		shader.setUniformf("eyePosition", eyePosition);
		
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
