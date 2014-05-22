package com.cr.states;

import java.util.LinkedList;

public class GameStateManager {
	
	private LinkedList<GameState> states;
	
	public GameStateManager(){
		states = new LinkedList<GameState>();
	}

	public void addState(GameState state){
		states.push(state);
	}
	
	public void tick(float dt){
		tick(states.element(), dt);
	}
	
	public void tick(GameState state, float dt){
		state.tick(dt);
		if(!state.blockTicking){
			
		}
	}
	
	
}
