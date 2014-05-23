package com.cr.game;

import java.awt.Graphics2D;

import com.cr.states.GameState;
import com.cr.util.Node;
import com.cr.util.StateStack;

public class GameStateManager {
	
	private StateStack<GameState> states;
	
	public GameStateManager(){
		states = new StateStack<GameState>();
	}

	public void pushState(GameState state){
		states.push(state);
	}
	
	public void removeTop(){
		states.deleteTop();
	}
	
	public void tick(float dt){
		tick(states.top, dt);
	}
	
	private void tick(Node<GameState> state, float dt){
		state.data.tick(dt);
		if(!state.data.isTickingBlocked())
			tick(state.next, dt);
	}
	
	public void render(Graphics2D g){
		render(states.top, g);
	}
	
	private void render(Node<GameState> state, Graphics2D g){
		state.data.render(g);
		if(!state.data.isRenderingBlocked())
			render(state.next, g);
	}
	
	
}
