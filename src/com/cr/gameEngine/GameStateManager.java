package com.cr.gameEngine;

import java.awt.Graphics2D;

import com.cr.states.GameState;
import com.cr.util.Node;
import com.cr.util.StateStack;

public class GameStateManager {
	
	private StateStack<GameState> states;
	
	public GameStateManager(){
		states = new StateStack<GameState>();
	}

	/**
	 * Adds a state at the top of the state stack
	 * @param state the game state that will be added to the top of the state stack
	 */
	public void push(GameState state){
		states.push(state);
	}
	
	/**
	 * Deletes the state at the top of the state stack
	 */
	public void pop(){
		states.pop();
	}
	
	public GameState next(){
		return states.top.next.data;
	}
	
	public Node<GameState> peek(){
		return states.top;
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
		if(!state.data.isRenderingBlocked())
			render(state.next, g);
		state.data.render(g);
	}

}
