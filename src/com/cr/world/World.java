package com.cr.world;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.BufferUtils;

import com.cr.combat.loot.Loot;
import com.cr.combat.loot.LootTable;
import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Texture;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.shader.Shader;
import com.cr.entity.enemy.forestelf.ForestElf;
import com.cr.entity.enemy.wisp.Wisp;
import com.cr.game.EntityManager;
import com.cr.net.HeroMPServer;
import com.cr.net.NetStatus;
import com.cr.net.packets.Packet19Loot;
import com.cr.net.server.Server;
import com.cr.states.net.MPHostState;
import com.cr.util.Camera;
import com.cr.util.Randomizer;
import com.cr.util.SpriteLoader;
import com.cr.world.misc.FirePlace;
import com.cr.world.terrain.Stone;
import com.cr.world.terrain.Tree;
import com.cr.world.terrain.WorldObject;
import com.cr.world.tile.Tile;

public class World {
	
	private TileMap map;
	private Camera camera;
	private EntityManager em;

	private static Shader shader;
	private static Transform transform;
	
	private Vector3f lightPosition,lightPosition2, ambientLight, eyePosition;
	
	private HashMap<Integer, Byte> byteMap;
	private HashMap<Vector2f, WorldObject> objectPosMap;
	private List<Integer> pixels;
	
	private Tree[] trees;
	private Stone[] stones;
	
	private int width, height;
	private int timer = 0;
	
	private int numOfTrees = 500;
	private int numOfStones = 300;
	
	private float t = 0;
	private float amplitudeWave = 5f;
	private float angleWave = 2.86f;
	private float angleWaveSpeed = 0.2f;
	private static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	private boolean day = true, night = false;
	
	private float lightX = 0;
	private float lightY = 0;
	private float lightZ = 0;
	
	private float lightX2 = 0;
	private float lightY2 = 0;
	private float lightZ2 = 0;
	
	private float k = 0;
	
	private FirePlace fire;
	private Texture normalMapWater, normalMapGrass;
	
	public World(LinkedList<Integer> pixels, int width, int height){
		initShader();
		objectPosMap = new HashMap<Vector2f, WorldObject>();
		map = new TileMap(pixels, width, height);

		this.width = width;
		this.height = height;

		init();
	}
	
	public World(){
		initShader();
	
		

		map = new TileMap(250, 250);
		
		width = map.getWidth();
		height = map.getHeight();
		
		if(NetStatus.isMultiPlayer && NetStatus.isHOST){
			byteMap = new HashMap<Integer, Byte>();
			pixels = new ArrayList<Integer>();
			objectPosMap = new HashMap<Vector2f, WorldObject>();
			
			trees = new Tree[map.getGrassLands().getTreePositions().size()];
			stones = new Stone[numOfStones];
			
			initByteMap();
			
			for(int i = 0; i < width*height; i++)
				pixels.add(map.getBottomLayer().getBitmap().getPixels()[i]);
			
			for(int i = 0; i < width*height; i++)
				pixels.add(map.getMiddleLayer().getBitmap().getPixels()[i]);
		
			for(int i = 0; i < width*height; i++)
				pixels.add(map.getTopLayer().getBitmap().getPixels()[i]);
		}
		
		init();
		generateWorldObjects();
		//generateEnemies();
	}
	
	
	private void init(){
		em = new EntityManager(this);
		
		//fire = new FirePlace(new Vector2f(EntityManager.getHero().getX() , EntityManager.getHero().getY()));
		
		lightX = -1000;
		lightY = (height * Tile.getTileHeight()) / 2;
		lightZ = 0;
		
//		lightX = fire.getX() + (fire.getWidth()/2);
//		lightY = fire.getY() + (fire.getHeight()/2) + 20;
//		lightZ = -100;
		
		lightX2 = EntityManager.getHero().getX() + 1000;
		lightY2 = EntityManager.getHero().getY();
		lightZ2 = 0;
		
		lightPosition = transform.getModelMatrix().mul(new Vector3f(lightX, lightY, lightZ));
		lightPosition2 = transform.getModelMatrix().mul(new Vector3f(lightX2, lightY2, lightZ2));
		
		//lightPosition = lightPosition.rotateZ(center, 45);
		camera = new Camera();
		
		eyePosition = Camera.getPos();
	}
	

	private void initShader(){
		transform = new Transform();
		
		normalMapWater = new Texture("normalMapWater2");
		normalMapGrass = new Texture("normalMapGrass");

		
		//cubeMap = new Texture("cubeMap0");
		
		shader = new Shader("phongVertShader", "phongFragShader");
		
		shader.addUniform("transformation");
		shader.addUniform("modelMatrix");
		shader.addUniform("time");
		shader.addUniform("waveDataX");
		shader.addUniform("waveDataY");
		shader.addUniform("isWater");
		shader.addUniform("lightPosition");
		shader.addUniform("lightPosition2");
		shader.addUniform("scene_ambient_light");
		shader.addUniform("eyePosition");
		shader.addUniform("k");
		
		shader.addUniform("sampler");
		shader.addUniform("normalMapWater");
		shader.addUniform("normalMapGrass");
		shader.addUniform("cubeMap0");
		
		shader.addUniform("material_shininess");
		shader.addUniform("material_diffuse_color");
		shader.addUniform("material_specular_color");
		shader.addUniform("material_emissive_color");
		
		shader.bind();
		shader.setUniformi("sampler", 0);
		shader.setUniformi("normalMapWater", 1);
		shader.setUniformi("normalMapGrass", 2);
		shader.unbind();
		
		normalMapGrass.bind(2);
		normalMapWater.bind(1);
		glActiveTexture(GL_TEXTURE0);
		
		ambientLight = new Vector3f(0.1f, 0.1f, 0.3f);
		new SpriteLoader();
	}
	
	private void initByteMap(){
		byteMap.put(ColorRGBA.BLACK, (byte)-1);
		byteMap.put(ColorRGBA.GRAY, (byte)0);
		byteMap.put(ColorRGBA.GREEN, (byte)1);
		byteMap.put(ColorRGBA.BLUE, (byte)2);
		byteMap.put(ColorRGBA.BROWN, (byte)3);
		byteMap.put(ColorRGBA.YELLOW, (byte)4);
	}
	
	private void generateTrees(int num){
		for(int i = 0; i < num; i++){
			int x = (int)map.getGrassLands().getTreePositions().get(i).x;
			int y = (int)map.getGrassLands().getTreePositions().get(i).y;
			
			Tree t = new Tree(x,y);
			t.init();

			if(t.getX() < 0) t.getPosition().x = 0;
			if(t.getY() < 0) t.getPosition().y = 0;
			
			if(t.getX() + t.getSprite().getSpriteWidth() > (width*Tile.getTileWidth()))
				t.getPosition().x = (width*Tile.getTileWidth()) - t.getSprite().getSpriteWidth();
			
			if(t.getY() + t.getSprite().getSpriteHeight() > (height*Tile.getTileHeight()))
				t.getPosition().y = (height*Tile.getTileHeight()) - t.getSprite().getSpriteHeight();
			
			t.updateRect();
			
			if(NetStatus.isMultiPlayer && NetStatus.isHOST){
				objectPosMap.put(t.getPosition(), t);
				trees[i] = t;
			}
		}
		
//		for(int i = 0; i < map.getGrassLands().getReedPositions().size(); i++){
//			Reeds r = new Reeds((int)map.getGrassLands().getReedPositions().get(i).x, (int)map.getGrassLands().getReedPositions().get(i).y);
//			r.init();
//			
//			if(r.getX() < 0) r.getPosition().x = 0;
//			if(r.getY() < 0) r.getPosition().y = 0;
//			
//			if(r.getX() + r.getSprite().getSpriteWidth() > (width*Tile.getTileWidth()))
//				r.getPosition().x = (width*Tile.getTileWidth()) - r.getSprite().getSpriteWidth();
//			
//			if(r.getY() + r.getSprite().getSpriteHeight() > (height*Tile.getTileHeight()))
//				r.getPosition().y = (height*Tile.getTileHeight()) - r.getSprite().getSpriteHeight();
//			
//			r.updateRect();
//		}
	}
	
	private void generateStones(int num){
		for(int i = 0; i < num; i++){
			Stone s;
			boolean generated = false;
			while(!generated){
				s = new Stone(-1000, -1000);
				s.init();
				int x = Randomizer.getInt(0, width * Tile.getTileWidth()) + 40;
				int y = Randomizer.getInt(0, height * Tile.getTileHeight()) + s.getSprite().getSpriteHeight();
				if(map.getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
					s.setPosition(new Vector2f(x - 40, y - s.getSprite().getSpriteHeight()));
					if(s.getX() < 0) s.getPosition().x = 0;
					if(s.getY() < 0) s.getPosition().y = 0;
					
					if(s.getX() + s.getSprite().getSpriteWidth() > (width*Tile.getTileWidth()))
						s.getPosition().x = (width*Tile.getTileWidth()) - s.getSprite().getSpriteWidth();
					
					if(s.getY() + s.getSprite().getSpriteHeight() > (height*Tile.getTileHeight()))
						s.getPosition().y = (height*Tile.getTileHeight()) - s.getSprite().getSpriteHeight();
					
					if(NetStatus.isMultiPlayer && NetStatus.isHOST){
						objectPosMap.put(s.getPosition(), s);
						stones[i] = s;
					}
					generated = true;
				}
			}
		}
	}
	
	private void generateEnemies(){
		for(int i = 0; i < 30; i++){
			ForestElf e = null;
			boolean generated = false;
			while(!generated){
				e = new ForestElf(new Vector2f(-1000, -1000), this);
				int x = Randomizer.getInt(0, width * 51) + 40;
				int y = Randomizer.getInt(0, height * 33) + e.getSprite().getSpriteHeight();
				//System.out.println(e.getSprite().getSpriteHeight());
				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
					e.setPosition(new Vector2f(x - 40, y - e.getSprite().getSpriteHeight()));
					generated = true;
				}
			}
		}
	
		for(int i = 0; i < 40; i++){
			Wisp e = null;
			boolean generated = false;
			while(!generated){
				e = new Wisp(new Vector2f(-1000, -1000), this);
				int x = Randomizer.getInt(0, width * 51) + 40;
				int y = Randomizer.getInt(0, height * 33) + e.getSprite().getSpriteHeight();
				//System.out.println(e.getSprite().getSpriteHeight());
				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
					e.setPosition(new Vector2f(x - 40, y - e.getSprite().getSpriteHeight()));
					generated = true;
				}
			}
		}
	}
	
	private void generateWorldObjects(){
//		for(int i = 0; i < 40; i++){
//			FirePlace s = null;
//			boolean generated = false;
//			while(!generated){
//				s = new FirePlace(new Vector2f(-1000, -1000));
//				int x = Randomizer.getInt(0, width * 51) + 40;
//				int y = Randomizer.getInt(0, height * 33) + s.getSprite().getSpriteHeight();
//				if(map.getTopLayer().getTileID(x / 58, y / 38) == ColorRGBA.GREEN){
//					s.setPosition(new Vector2f(x - 40, y - s.getSprite().getSpriteHeight()));
//					if(s.getX() < 0) s.getPosition().x = 0;
//					if(s.getY() < 0) s.getPosition().y = 0;
//					
//					if(s.getX() + s.getSprite().getSpriteWidth() > (width*Tile.getTileWidth()))
//						s.getPosition().x = (width*Tile.getTileWidth()) - s.getSprite().getSpriteWidth();
//					
//					if(s.getY() + s.getSprite().getSpriteHeight() > (height*Tile.getTileHeight()))
//						s.getPosition().y = (height*Tile.getTileHeight()) - s.getSprite().getSpriteHeight();
//					
//					
//					generated = true;
//				}
//			}
//		}
		
//		FloatBuffer buffer = BufferUtils.createFloatBuffer(lightSources.size() * 2);
//		
//		for(int i = 0; i < lightSources.size(); i++){
//			buffer.put(lightSources.get(i).x);
//			buffer.put(lightSources.get(i).y);
//		}
//		
//		buffer.flip();
//		
//		shader.addUniform("lights");
//		shader.bind();
//		shader.setUniformf("lights", buffer);
//		shader.unbind();
		
		generateTrees(map.getGrassLands().getTreePositions().size());
		//System.out.println(map.getGrassLands().getTreePositions().size());
		generateStones(numOfStones);
	}
	
	private void dayNightCycle(float dt){
		t += dt*0.008f;
		
		if(t >= PI2) t = 0;
		
		if(t > 0 && t <= 3.14f/6.0f)
			k += 0.0008f;
		
		if(t > ((5.0*3.14f) / 6.0f) && t <= 3.14f)
			k -= 0.0008f;

		lightPosition2.y = Camera.getCamY() + Window.getHeight()/2;
		lightPosition2.x = lightX2 + ( -1.0f * (float)(Math.cos((-t-3.14f))) * 2000);
		lightPosition2.z = -600 * (float) Math.sin(t);
		
		lightPosition.x = lightX + (-1.0f * ((float) Math.cos(t)) * (2000 + (width * Tile.getTileWidth())));
		lightPosition.z = -10000 * (float) Math.sin(t);
		

	}
	
	
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
	
		dayNightCycle(dt);

		angleWave += dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;

		camera.tick(dt);
		em.tick(dt);
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
	
	public byte[] getBytes(int pNumber, byte[] data){
		for(int i = 0; i < 924; i++)
			if(i + (pNumber*924) < width*height*3)
				data[i+100] = byteMap.get(pixels.get(i + (pNumber*924)));
		return data;
	}

	public static void spawnLoot(int x, int y, int type, int amount){
		new Loot(new Vector2f(x, y), new Vector2f(Randomizer.getFloat(-2f, 2f), -7.5f), type, amount);
	}
	
	public static void spawnLoot(int x, int y, LootTable lt, int amount){
		if(NetStatus.isMultiPlayer && NetStatus.isHOST){
			Server server = MPHostState.getServer();
			HashMap<String, HeroMPServer> players = server.getClientsMap();
			System.out.println("Send Loot");
			for(String s : players.keySet()){
				System.out.println("Loot to: " + players.get(s).getUserName());
				Packet19Loot p = new Packet19Loot(19, x, y, lt.getLootID(), 1);
				server.sendData(p.getData(), players.get(s).getInetAddress(), players.get(s).getPort());
			}
		}
		new Loot(new Vector2f(x, y), new Vector2f(Randomizer.getFloat(-2f, 2f), -7.5f), lt.getLootID(), amount);
	}
	
	public void generateTrees(List<Tree> trees){
		for(Tree t : trees){
			if(t != null){
				t.init();
			}
		}
	}
	
	public void generateStones(List<Stone> stones){
		for(Stone s : stones){
			if(s != null){
				s.init();
			}
		}
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

	public HashMap<Vector2f, WorldObject> getObjectPosMap() {
		return objectPosMap;
	}

	public int getNumOfTrees() {
		return numOfTrees;
	}

	public int getNumOfStones() {
		return numOfStones;
	}

	public Tree[] getTrees() {
		return trees;
	}

	public Stone[] getStones() {
		return stones;
	}

}
