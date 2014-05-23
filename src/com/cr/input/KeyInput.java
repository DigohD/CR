package com.cr.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{

	public static boolean up, down, right, left, esc, c, v, space, enter;
	private static boolean[] keys = new boolean[150];
	
	public static void tick(){
		up   	= keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
		down 	= keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
		right 	= keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		left 	= keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		c 		= keys[KeyEvent.VK_C];
		v 		= keys[KeyEvent.VK_V];
		space   = keys[KeyEvent.VK_SPACE];
		esc 	= keys[KeyEvent.VK_ESCAPE];
		enter   = keys[KeyEvent.VK_ENTER];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
