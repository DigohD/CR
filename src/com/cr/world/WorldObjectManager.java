package com.cr.world;

import java.util.List;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.ColorRGBA;
import com.cr.engine.graphics.Texture;
import com.cr.entity.enemy.v2.Enemy;
import com.cr.game.EntityManager;
import com.cr.net.NetStatus;
import com.cr.util.Randomizer;
import com.cr.util.enemy.EnemyAssembler;
import com.cr.world.terrain.Stone;
import com.cr.world.terrain.WorldObjectBatch;
import com.cr.world.tile.Tile;

public class WorldObjectManager {
	
	private World world;
	private WorldObjectBatch treeBatch, reedBatch;
	
	private Vector2f[] treePositions, stonePositions, reedsPositions;
	
	public WorldObjectManager(World world){
		this.world = world;
	}
	
	public void generateTrees(List<Vector2f> positions){
		treeBatch = new WorldObjectBatch(new Texture("treeAtlas"), 1, 4);
		inbound(positions, treeBatch);
		if(NetStatus.isMultiPlayer && NetStatus.isHOST){
			treePositions = new Vector2f[positions.size()];
			positions.toArray(treePositions);
		}
		treeBatch.generateMesh(positions, true);
	}
	
	public void generateReeds(List<Vector2f> positions){
		reedBatch = new WorldObjectBatch(new Texture("reeds"), 1, 1);
		inbound(positions, reedBatch);
		if(NetStatus.isMultiPlayer && NetStatus.isHOST){
			reedsPositions = new Vector2f[positions.size()];
			positions.toArray(treePositions);
		}
		reedBatch.generateMesh(positions, false);
	}
	
	public void generateStones(int num){
		stonePositions = new Vector2f[num]; 
		for(int i = 0; i < num; i++){
			Stone s;
			boolean generated = false;
			while(!generated){
				s = new Stone(-1000, -1000);
				s.init();
				int x = Randomizer.getInt(0, world.getWidth() * Tile.getTileWidth()) + 40;
				int y = Randomizer.getInt(0, world.getHeight() * Tile.getTileHeight()) + s.getSprite().getSpriteHeight();
				if(world.getMap().getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
					s.setPosition(new Vector2f(x - 40, y - s.getSprite().getSpriteHeight()));
					if(s.getX() < 0) s.getPosition().x = 0;
					if(s.getY() < 0) s.getPosition().y = 0;
					
					if(s.getX() + s.getSprite().getSpriteWidth() > (world.getWidth()*Tile.getTileWidth()))
						s.getPosition().x = (world.getWidth()*Tile.getTileWidth()) - s.getSprite().getSpriteWidth();
					
					if(s.getY() + s.getSprite().getSpriteHeight() > (world.getHeight()*Tile.getTileHeight()))
						s.getPosition().y = (world.getHeight()*Tile.getTileHeight()) - s.getSprite().getSpriteHeight();
					
					if(NetStatus.isMultiPlayer && NetStatus.isHOST){
						//objectPosMap.put(s.getPosition(), s);
						stonePositions[i] = s.getPosition();
					}
					generated = true;
				}
			}
		}
	}

	public void generateEnemies(){
		for(int i = 0; i < 100; i++){
			Enemy e = null;
			boolean generated = false;
			while(!generated){
				e = EnemyAssembler.getNewEnemy(world);
				int x = Randomizer.getInt(0, world.getWidth() * 51) + 40;
				int y = Randomizer.getInt(0, world.getHeight() * 33) + e.getSprite().getSpriteHeight();
				
				if(world.getMap().getTopLayer().getTileID(x / Tile.getTileWidth(), y / Tile.getTileHeight()) == ColorRGBA.GREEN){
					e.setPosition(new Vector2f(x - 40, y - e.getSprite().getSpriteHeight()));
					EntityManager.addEntity(e);
					generated = true;
				}
			}
		}
	}
	
	private void inbound(List<Vector2f> list, WorldObjectBatch batch){
		for(int i = 0; i < list.size(); i++){
			int x =(int)list.get(i).x;
			int y =(int)list.get(i).y;
			
			if(x < 0) list.get(i).x = 0;
			if(y < 0) list.get(i).y = 0;
			
			if(x + batch.getObjectWidth() > world.getWidth() * Tile.getTileWidth())
				list.get(i).x = (world.getWidth() * Tile.getTileWidth()) - batch.getObjectWidth();
			
			if(y + batch.getObjectHeight() > world.getHeight() * Tile.getTileHeight())
				list.get(i).y = (world.getHeight() * Tile.getTileHeight()) - batch.getObjectHeight();
		}
	}
	
	public void render(){
		treeBatch.render(-3.0f);
		reedBatch.render(-3.0f);
	}

	public Vector2f[] getTreePositions() {
		return treePositions;
	}

	public Vector2f[] getStonePositions() {
		return stonePositions;
	}

	

}
