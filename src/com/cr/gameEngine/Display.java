package com.cr.gameEngine;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Display {
	
	public static Canvas canvas;
	public static JFrame frame;
	private static Window w;
	private static GraphicsDevice gc;
	
	public static void init(){
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gc = env.getDefaultScreenDevice();
	}
			
	public static DisplayMode[] getCompatibleDisplayModes(){
		return gc.getDisplayModes();
	}
			
			//compares DM passed int to gc DM and see if they match
			public static DisplayMode findFirstCompatibleMode(DisplayMode[] modes){
				DisplayMode[] goodModes = gc.getDisplayModes();
				for(int i = 0; i < modes.length; i++)
					for(int j = 0; j < goodModes.length; j++)
						if(displayModesMatch(modes[i], goodModes[j]))
							return modes[i];	
				return null;
			}
			
			//get current DM
			public static DisplayMode getCurrentDisplayMode(){
				return gc.getDisplayMode();
			}
			
			//checks if two modes match each other
			public static boolean displayModesMatch(DisplayMode dm1, DisplayMode dm2){
				if(dm1.getWidth() != dm2.getWidth() || dm1.getHeight() != dm2.getHeight())
					return false;
				
				if(dm1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && dm2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI 
						&& dm1.getBitDepth() != dm2.getBitDepth())
					return false;
				
				if(dm1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && dm2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
						&& dm1.getRefreshRate() != dm2.getRefreshRate())
					return false;
				
				return true;
			}
			
			
			
			public static void setFullScreen(DisplayMode dm){
				frame = new JFrame();
				frame.setUndecorated(true);
				frame.setIgnoreRepaint(true);
				frame.setResizable(false);
				gc.setFullScreenWindow(frame);
				
				if(dm != null && gc.isDisplayChangeSupported()){
					try{
						gc.setDisplayMode(dm);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				frame.createBufferStrategy(3);
			}
			
			public static Graphics2D getGraphics(){
				w = gc.getFullScreenWindow();
				if(w != null){
					BufferStrategy bs = w.getBufferStrategy();
					return (Graphics2D) bs.getDrawGraphics();
				}else
					return null;
			}
			
			//updates display
			public static void update(){
				w = gc.getFullScreenWindow();
				if(w != null){
					BufferStrategy bs = w.getBufferStrategy();
					if(!bs.contentsLost())
						bs.show();
				}
				Toolkit.getDefaultToolkit().sync();
			}
			
			//return full screen window
			public static Window getFullScreenWindow(){
				return gc.getFullScreenWindow();
			}
			
			public static int getWidth(){
				w = gc.getFullScreenWindow();
				if(w != null) return w.getWidth();
				else return 0;
			}
			
			public static int getHeight(){
				w = gc.getFullScreenWindow();
				if(w != null) return w.getHeight();
				else return 0;
			}
			
			//get out of full screen
			public static void restoreScreen(){
				w = gc.getFullScreenWindow();
				if(w != null) w.dispose();
				gc.setFullScreenWindow(null);
			}
			
			public BufferedImage createCompatibleImage(int width, int height, int transparency){
				w = gc.getFullScreenWindow();
				if(w != null){
					GraphicsConfiguration gc = w.getGraphicsConfiguration();
					return gc.createCompatibleImage(width, height, transparency);
				}
				return null;
			}
		
		public static void setCursor(BufferedImage img){
			Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				    img, new Point(0, 0), "custom cursor");
			frame.getContentPane().setCursor(cursor);
		}
		
		public static void hideCursor(){
			BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
					cursorImg, new Point(0, 0), "blank cursor");
			frame.getContentPane().setCursor(blankCursor);
		}
		
//		public static void standardCursor(){
//			Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
//					assets.ImageLoader.loadImage("images/UI", "cursor.png"), new Point(0, 0), "custom cursor");
//			frame.getContentPane().setCursor(cursor);
//		}s
		
		public static void addKeyListener(KeyListener event){
			w.addKeyListener(event);
		}
		
		public static void removeMouseListener(MouseListener event){
			w.removeMouseListener(event);
		}
		
		public static void addMouseListener(MouseListener event){
			w.addMouseListener(event);
		}
		
		public static void addMouseMotionListener(MouseMotionListener event){
			w.addMouseMotionListener(event);
		}
		
		public static void removeKeyListener(KeyListener event){
			w.removeKeyListener(event);
		}
		
		public static void removeMouseMotionListener(MouseMotionListener event){
			w.removeMouseMotionListener(event);
		}
		
		public static Canvas getCanvas(){
			return canvas;
		}
		
		public static JFrame getFrame(){
			return frame;
		}
		
		public static Window getWindow(){
			return w;
		}
		
		public static int getPosX(){
			return frame.getX();
		}
		
		public static int getPosY(){
			return frame.getY();
		}

}
