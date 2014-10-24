package com.cr.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.cr.combat.loot.Loot;
import com.cr.combat.loot.LootTable;
import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.shader.PhongShader;
import com.cr.engine.graphics.shader.Shader;
import com.cr.game.EntityManager;
import com.cr.net.HeroMPServer;
import com.cr.net.NetStatus;
import com.cr.net.packets.Packet19Loot;
import com.cr.net.server.Server;
import com.cr.states.net.MPHostState;
import com.cr.util.Camera;
import com.cr.util.Randomizer;
import com.cr.util.SpriteLoader;
import com.cr.world.terrain.Stone;
import com.cr.world.terrain.Tree;
import com.cr.world.tile.Tile;

public class World {
	
	private int width, height;
	
	private TileMap map;
	private Camera camera;
	private EntityManager em;
	private WorldObjectManager woManager;

	private static PhongShader shader;
	private static Transform transform;
	
	private HashMap<Integer, Byte> byteMap;
	private List<Integer> pixels;

	public World(LinkedList<Integer> pixels, int width, int height){
		transform = new Transform();
		shader = new PhongShader(this);
		new SpriteLoader();
		
		map = new TileMap(pixels, width, height);

		this.width = width;
		this.height = height;

		init();
	}
	
	public World(){
		transform = new Transform();
		shader = new PhongShader(this);
		new SpriteLoader();
		
		map = new TileMap(100, 100);

		width = map.getWidth();
		height = map.getHeight();

		if(NetStatus.isMultiPlayer && NetStatus.isHOST){
			byteMap = new HashMap<Integer, Byte>();
			pixels = new ArrayList<Integer>();
		
			initByteMap();
			
			for(int i = 0; i < width*height; i++)
				pixels.add(map.getBottomLayer().getBitmap().getPixels()[i]);
			
			for(int i = 0; i < width*height; i++)
				pixels.add(map.getMiddleLayer().getBitmap().getPixels()[i]);
		
			for(int i = 0; i < width*height; i++)
				pixels.add(map.getTopLayer().getBitmap().getPixels()[i]);
		}
		
		init();
		
		woManager = new WorldObjectManager(this);
		woManager.generateTrees(map.getGrassLands().getTreePositions());
		woManager.generateReeds(map.getGrassLands().getReedPositions());
		woManager.generateStones(100);
		woManager.generateEnemies();
	}
	
	private void init(){
		em = new EntityManager(this);
		camera = new Camera();
		shader.initLights(this);
	}
	
	private void initByteMap(){
		byteMap.put(ColorRGBA.BLACK, (byte)-1);
		byteMap.put(ColorRGBA.GRAY, (byte)0);
		byteMap.put(ColorRGBA.GREEN, (byte)1);
		byteMap.put(ColorRGBA.BLUE, (byte)2);
		byteMap.put(ColorRGBA.BROWN, (byte)3);
		byteMap.put(ColorRGBA.YELLOW, (byte)4);
	}
	
	public void tick(float dt){
		shader.tick(dt);
		camera.tick(dt);
		em.tick(dt);
	}
	
	public void render(Screen screen) {
		shader.updateUniforms(transform);
		map.renderMap();
		em.render(screen);
		woManager.render();
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

	public TileMap getMap() {
		return map;
	}

	public WorldObjectManager getWoManager() {
		return woManager;
	}

}
