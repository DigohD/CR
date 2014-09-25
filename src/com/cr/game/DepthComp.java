package com.cr.game;

import java.util.Comparator;

import com.cr.entity.Entity;
import com.cr.entity.Renderable;

public class DepthComp implements Comparator<Renderable>{

	@Override
	public int compare(Renderable current, Renderable next){
//		if(current.getPosition().y + ((Renderable) current).getSprite().getSpriteHeight() < 
//				next.getPosition().y + ((Renderable) next).getSprite().getSpriteHeight()){
//			return 1;
//		else
//			return 0;
		System.out.println(current);
		if((((Entity) current).getPosition().y + current.getSprite().getSpriteHeight() > 
				((Entity) next).getPosition().y + next.getSprite().getSpriteHeight()))
			return 1;
		else if((((Entity) current).getPosition().y + current.getSprite().getSpriteHeight() <
				((Entity) next).getPosition().y + next.getSprite().getSpriteHeight()))
			return -1;
		else 
			return 0;
	}

}
