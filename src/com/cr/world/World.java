package com.cr.world;

import java.util.HashMap;
import java.util.LinkedList;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
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
	private static Transform transform;
	
	private float t = 0;
	private float currentTime = 0.2f;
	private float targetTime = 2f;
	private float dayNightCycleTime = 10.0f;
	private float amplitudeWave = 5f;
	private float angleWave = 2.86f;
	private float angleWaveSpeed = 0.2f;
	private static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	private boolean day = true, night = false;
	private static boolean start = true;
	
	float lightX = 0;
	float lightY = 0;
	float lightZ = 0;
	
	float lightX2 = 0;
	float lightY2 = 0;
	float lightZ2 = 0;
	
	float k = 0;
	
	private Vector3f lightPosition,lightPosition2, ambientLight, eyePosition;
	
	private HashMap<Integer, Byte> byteMap = new HashMap<Integer, Byte>();
	
	
	public World(LinkedList<Integer> pixels, int width, int height){
		transform = new Transform();
		
		shader = new Shader("phongVertShader", "phongFragShader");
		
		shader.addUniform("transformation");
		shader.addUniform("modelMatrix");
		shader.addUniform("time");
		shader.addUniform("sampler");
		shader.addUniform("waveDataX");
		shader.addUniform("waveDataY");
		shader.addUniform("isWater");
		shader.addUniform("lightPosition");
		shader.addUniform("lightPosition2");
		shader.addUniform("scene_ambient_light");
		shader.addUniform("eyePosition");
		shader.addUniform("k");
		
		shader.addUniform("material_shininess");
		shader.addUniform("material_diffuse_color");
		shader.addUniform("material_specular_color");
		shader.addUniform("material_emissive_color");
		
		shader.setUniformi("sampler", 0);

		map = new TileMap(pixels, width, height);

		width = map.getWidth();
		height = map.getHeight();
		
		lightX = -1000;
		lightY = (height * Tile.getTileHeight()) / 2;
		lightZ = 0;

		lightPosition = transform.getModelMatrix().mul(new Vector3f(lightX, lightY, lightZ));
		lightPosition2 = transform.getModelMatrix().mul(new Vector3f(lightX2, lightY2, lightZ2));
		
		ambientLight = new Vector3f(0.2f, 0.2f, 0.2f);
		
		em = new EntityManager(this);
		
		lightX2 = EntityManager.getHero().getX() + 1000;
		lightY2 = EntityManager.getHero().getY();
		lightZ2 = 0;
		camera = new Camera();
		
		eyePosition = Camera.getPos();
		
//		new LootEmitter(new Vector2f(200,200), 5000);
		
//		RangedTest dummy = new RangedTest(new Vector2f(400, 400), this);


//		for(int i = 0; i < 30; i++){
//			ForestElf e = null;
//			boolean generated = false;
//			while(!generated){
//				e = new ForestElf(new Vector2f(-1000, -1000), this);
//				int x = Randomizer.getInt(0, width * 51) + 40;
//				int y = Randomizer.getInt(0, height * 33) + e.getSprite().getSpriteHeight();
//				System.out.println(e.getSprite().getSpriteHeight());
//				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
//					e.setPosition(new Vector2f(x - 40, y - e.getSprite().getSpriteHeight()));
//					generated = true;
//				}
//			}
//		}
//		
//		for(int i = 0; i < 10; i++){
//			Wisp e = null;
//			boolean generated = false;
//			while(!generated){
//				e = new Wisp(new Vector2f(-1000, -1000), this);
//				int x = Randomizer.getInt(0, width * 51) + 40;
//				int y = Randomizer.getInt(0, height * 33) + e.getSprite().getSpriteHeight();
//				System.out.println(e.getSprite().getSpriteHeight());
//				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
//					e.setPosition(new Vector2f(x - 40, y - e.getSprite().getSpriteHeight()));
//					generated = true;
//				}
//			}
//		}
//		
//		for(int i = 0; i < 100; i++){
//			Tree t;
//			boolean generated = false;
//			while(!generated){
//				t = new Tree(-1000, -1000);
//				int x = Randomizer.getInt(0, width * Tile.getTileWidth()) + 40;
//				int y = Randomizer.getInt(0, height * Tile.getTileHeight()) + t.getSprite().getSpriteHeight();
//				//System.out.println(t.getSprite().getSpriteHeight());
//				if(map.getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
//					t.setPosition(new Vector2f(x - 40, y - t.getSprite().getSpriteHeight()));
//					t.updateRect();
//					generated = true;
//				}
//			}
//		}
//		
//		for(int i = 0; i < 100; i++){
//			Stone s;
//			boolean generated = false;
//			while(!generated){
//				s = new Stone(-1000, -1000);
//				int x = Randomizer.getInt(0, width * Tile.getTileWidth()) + 40;
//				int y = Randomizer.getInt(0, height * Tile.getTileHeight()) + s.getSprite().getSpriteHeight();
//				if(map.getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
//					s.setPosition(new Vector2f(x - 40, y - s.getSprite().getSpriteHeight()));
//					generated = true;
//				}
//			}
//		}
		
		
		
//		CraftTest test = new CraftTest();
//		test.craftTest();
		
		
		
		
	}
	
	public World(){
		transform = new Transform();
		
		shader = new Shader("phongVertShader", "phongFragShader");
		
		shader.addUniform("transformation");
		shader.addUniform("modelMatrix");
		shader.addUniform("time");
		shader.addUniform("sampler");
		shader.addUniform("waveDataX");
		shader.addUniform("waveDataY");
		shader.addUniform("isWater");
		shader.addUniform("lightPosition");
		shader.addUniform("lightPosition2");
		shader.addUniform("scene_ambient_light");
		shader.addUniform("eyePosition");
		shader.addUniform("k");
		
		shader.addUniform("material_shininess");
		shader.addUniform("material_diffuse_color");
		shader.addUniform("material_specular_color");
		shader.addUniform("material_emissive_color");
		
		shader.setUniformi("sampler", 0);

		map = new TileMap(100, 100);
		


		width = map.getWidth();
		height = map.getHeight();
		

		
		initByteMap();
		
		lightX = -1000;
		lightY = (height * Tile.getTileHeight()) / 2;
		lightZ = 0;
		
		
		
		
		
		lightPosition = transform.getModelMatrix().mul(new Vector3f(lightX, lightY, lightZ));
		lightPosition2 = transform.getModelMatrix().mul(new Vector3f(lightX2, lightY2, lightZ2));
		
		ambientLight = new Vector3f(0.2f, 0.2f, 0.2f);
		
		em = new EntityManager(this);
		
		lightX2 = EntityManager.getHero().getX() + 1000;
		lightY2 = EntityManager.getHero().getY();
		lightZ2 = 0;
		camera = new Camera();
		
		eyePosition = Camera.getPos();
		
//		new LootEmitter(new Vector2f(200,200), 5000);
		
//		RangedTest dummy = new RangedTest(new Vector2f(400, 400), this);


//		for(int i = 0; i < 30; i++){
//			ForestElf e = null;
//			boolean generated = false;
//			while(!generated){
//				e = new ForestElf(new Vector2f(-1000, -1000), this);
//				int x = Randomizer.getInt(0, width * 51) + 40;
//				int y = Randomizer.getInt(0, height * 33) + e.getSprite().getSpriteHeight();
//				System.out.println(e.getSprite().getSpriteHeight());
//				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
//					e.setPosition(new Vector2f(x - 40, y - e.getSprite().getSpriteHeight()));
//					generated = true;
//				}
//			}
//		}
//		
//		for(int i = 0; i < 10; i++){
//			Wisp e = null;
//			boolean generated = false;
//			while(!generated){
//				e = new Wisp(new Vector2f(-1000, -1000), this);
//				int x = Randomizer.getInt(0, width * 51) + 40;
//				int y = Randomizer.getInt(0, height * 33) + e.getSprite().getSpriteHeight();
//				System.out.println(e.getSprite().getSpriteHeight());
//				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
//					e.setPosition(new Vector2f(x - 40, y - e.getSprite().getSpriteHeight()));
//					generated = true;
//				}
//			}
//		}
//		
//		for(int i = 0; i < 100; i++){
//			Tree t;
//			boolean generated = false;
//			while(!generated){
//				t = new Tree(-1000, -1000);
//				int x = Randomizer.getInt(0, width * Tile.getTileWidth()) + 40;
//				int y = Randomizer.getInt(0, height * Tile.getTileHeight()) + t.getSprite().getSpriteHeight();
//				//System.out.println(t.getSprite().getSpriteHeight());
//				if(map.getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
//					t.setPosition(new Vector2f(x - 40, y - t.getSprite().getSpriteHeight()));
//					t.updateRect();
//					generated = true;
//				}
//			}
//		}
//		
//		for(int i = 0; i < 100; i++){
//			Stone s;
//			boolean generated = false;
//			while(!generated){
//				s = new Stone(-1000, -1000);
//				int x = Randomizer.getInt(0, width * Tile.getTileWidth()) + 40;
//				int y = Randomizer.getInt(0, height * Tile.getTileHeight()) + s.getSprite().getSpriteHeight();
//				if(map.getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
//					s.setPosition(new Vector2f(x - 40, y - s.getSprite().getSpriteHeight()));
//					generated = true;
//				}
//			}
//		}
		
		
		
//		CraftTest test = new CraftTest();
//		test.craftTest();
	}
	
	private Vector3f xAxis = new Vector3f(1,0,0);
	private Vector3f yAxis = new Vector3f(0,1,0);
	private Vector3f zAxis = new Vector3f(0,0,1);
	
	boolean stop = false;
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
	
		t += dt*0.01f;
		
		if(t >= PI2) t = 0;
		
		if(t > 0 && t <= 3.14/6.0)
			k += 0.001f;
		
		
		if(t > ((5.0*3.14) / 6.0) && t <= 3.14)
			k -= 0.001f;

		lightPosition2.y = Camera.getCamY() + Window.getHeight()/2;
		lightPosition2.x = lightX2 + ( -1.0f * (float)(Math.cos((-t-3.14f))) * 2000);
		lightPosition2.z = -400 * (float) Math.sin(t);
		
		lightPosition.x = lightX + (-1.0f * ((float) Math.cos(t)) * (2000 + (width * Tile.getTileWidth())));
		lightPosition.z = -10000 * (float) Math.sin(t);
		
		angleWave += dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;

		camera.tick(dt);
		em.tick(dt);
	}
	
	private void dayNightCycle(float dt){
		t += dt*angleWaveSpeed*0.3f/ dayNightCycleTime;
		
		if(t >= PI2) t = 0;
		lightPosition = lightPosition.rotate(new Vector3f(0,1,0), t*dt*angleWaveSpeed*0.3f/ dayNightCycleTime);
		
		if(currentTime <= 1.2f && day){
			currentTime += dt*angleWaveSpeed*0.3f/dayNightCycleTime;
			if(currentTime > 1.2f || (t >= PI2/2 && t <= PI2)) {
				night = true;
				day = false;
			}
		}
		
		if(night){
			currentTime -= dt*angleWaveSpeed*0.3f/dayNightCycleTime;
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
		shader.setUniformf("lightPosition2", lightPosition2);
		shader.setUniformf("scene_ambient_light", ambientLight);
		shader.setUniformf("time", t);
		shader.setUniformf("k", k);
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
	
	
	
	private void initByteMap(){
		byteMap.put(ColorRGBA.BLACK, (byte)-1);
		byteMap.put(ColorRGBA.GRAY, (byte)0);
		byteMap.put(ColorRGBA.GREEN, (byte)1);
		byteMap.put(ColorRGBA.BLUE, (byte)2);
		byteMap.put(ColorRGBA.BROWN, (byte)3);
		byteMap.put(ColorRGBA.YELLOW, (byte)4);
	}
	
	
	
	public byte[] convertToByteArrays(int packetNumber, byte[] data){
		
	
	
		int rest = ((packetNumber + 1) * 924) % (width*height);
		int pre = 924 - rest;
		
		if(rest < 924 && ((packetNumber+1)*924) > (width*height*2)){
			int start = packetNumber * 924;
			for(int i = 0; i < pre; i++)
				data[i + 100] = byteMap.get(map.getMiddleLayer().getBitmap().getPixels()[start + i]);
			
			for(int i = pre; i < 924; i++){
				data[i + 100] = byteMap.get(map.getTopLayer().getBitmap().getPixels()[i]);
			}
			
			return data;
		}
		
		if(rest < 924){
			int start = packetNumber * 924;
			for(int i = 0; i < pre; i++)
				data[i + 100] = byteMap.get(map.getBottomLayer().getBitmap().getPixels()[start + i]);
			
			for(int i = pre; i < 924; i++){
				data[i + 100] = byteMap.get(map.getMiddleLayer().getBitmap().getPixels()[i]);
			}
			
			return data;
		}
		
		
		
		for(int i = 0; i < 924; i++)
			data[i+100] = byteMap.get(map.getBottomLayer().getBitmap().getPixels()[i + (packetNumber*924)]);
		
		
		return data;
		
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
