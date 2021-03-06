package com.cr.engine.core;

import com.cr.engine.graphics.Window;

public abstract class CoreEngine {
	
	public static int TARGET_TPS = 60;
	public static float dt = (float) ((1.0 / TARGET_TPS) * 10);
	private static volatile boolean running = false;
	
	public abstract void getInput();
	public abstract void tick(float dt);
	public abstract void render();
	public abstract void cleanUp();
	
	protected synchronized void start(){
		running = true;
		gameLoop();
	}
	
	public static synchronized void stop(){
		running = false;
	}
	
	private void gameLoop(){
		double currentTime = 0;
		double previousTime = System.nanoTime();
		double passedTime = 0;
		double accumulator = 0;
		double frameCounter = 0;
		final double OPTIMAL_TICK_TIME = 1.0 / TARGET_TPS;
		
		
		int fps = 0;
		int tps = 0;
		boolean shouldRender;
		
		while(running){
			shouldRender = false;
			currentTime = System.nanoTime();
			passedTime = (currentTime - previousTime) / 1000000000.0;
			accumulator += passedTime;
			frameCounter += passedTime;
			previousTime = currentTime;
		
			while(accumulator >= OPTIMAL_TICK_TIME){
				shouldRender = true;
				getInput();
				tick(dt);
				tps++;
				accumulator -= OPTIMAL_TICK_TIME;
			}
			
			render();
			fps++;
			Window.update();
	
			if(frameCounter >= 1){
				Window.setTitle("Craftmans Revenge Alpha v0.01" + " || " + tps + " tps, " + fps + " fps");
				fps = 0;
				tps = 0;
				frameCounter = 0;
			}
			
			if(Window.isCloseRequested()) stop();
		}
		cleanUp();
		Window.dispose();
	}
}
