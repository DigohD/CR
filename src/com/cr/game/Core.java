package com.cr.game;

import java.awt.DisplayMode;
import java.awt.Window;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.cr.input.KeyInput;
import com.cr.input.Mouse;

public abstract class Core implements Runnable{
	
	public static int WIDTH;
	public static int HEIGHT;
	
	public static int TARGET_TPS = 60;
	
	private Thread thread;
	private volatile boolean running = false;
	private volatile boolean paused = false;
	
	private Lock lock = new ReentrantLock();
	private Condition okToRun = lock.newCondition();
	
	public abstract void getInput();
	public abstract void tick(float dt);
	public abstract void render();
	
	protected static final DisplayMode[] modes = {
		new DisplayMode(1920, 1080, 32, 0),
		new DisplayMode(1920, 1080, 24, 0),
		new DisplayMode(1920, 1080, 16, 0),
		new DisplayMode(800, 600, 32, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(640, 480, 32, 0),
		new DisplayMode(640, 480, 24, 0),
		new DisplayMode(640, 480, 16, 0),
	};
	
	private void fullScreenMode(){
		Display.init();
		DisplayMode dm = Display.findFirstCompatibleMode(modes);
		Display.setFullScreen(dm);
		Display.hideCursor();
		WIDTH = Display.getWidth();
		HEIGHT = Display.getHeight();
		Window w = Display.getFullScreenWindow();
		Mouse mouse = new Mouse();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(new KeyInput());
		w.addMouseListener(mouse);
		w.addMouseMotionListener(mouse);
	}

	protected synchronized void start(){
		fullScreenMode();
		running = true;
		if(thread == null){
			thread = new Thread(this, "Game-Thread");
			thread.start();
		}
	}
	
	protected synchronized void stop(){
		running = false;
		if(thread == null) return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void pause() {
		paused = true;
	}

	public synchronized void resume() {
		lock.lock();
		paused = false;
		okToRun.signalAll();
		lock.unlock();
	}
	
	@Override
	public void run(){
		double currentTime = 0;
		double previousTime = System.nanoTime();
		double passedTime = 0;
		double accumulator = 0;
		double frameCounter = 0;
		final double OPTIMAL_TICK_TIME = 1.0 / TARGET_TPS;
		
		float dt = (float) (OPTIMAL_TICK_TIME * 10);

		int tps = 0;
		int fps = 0;
		
		while(running){
			lock.lock();
			while(paused){
				try {
					okToRun.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lock.unlock();
			
			currentTime = System.nanoTime();
			passedTime = (currentTime - previousTime) / 1000000000.0;
			if(passedTime > 0.25) passedTime = 0.25;
			accumulator += passedTime;
			frameCounter += passedTime;
			previousTime = currentTime;
		
			while(accumulator >= OPTIMAL_TICK_TIME){
				getInput();
				tick(dt);
				tps++;
				accumulator -= OPTIMAL_TICK_TIME;
			}
			
			render();
			fps++;

			if(frameCounter >= 1){
				//Display.frame.setTitle(TITLE + " || " + "tps: " + tps + ", fps: " + fps);
				System.out.println("tps: " + tps + ", fps: " + fps);
				tps = 0;
				fps = 0;
				frameCounter = 0;
			}
			
		}
		stop();
	}
	
}
