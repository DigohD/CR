package com.cr.engine.input;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.cr.engine.core.Vector2f;
import com.cr.engine.graphics.Window;
import com.cr.entity.Tickable;
import com.cr.entity.hero.inventory.Button;
import com.cr.entity.hero.inventory.Focusable;
import com.cr.entity.hero.inventory.Hooverable;

public class Input extends Observable{

		public static final int NUM_KEYCODES = 256;
		public static final int NUM_MOUSEBUTTONS = 5;

		//All these constants come from LWJGL's Keyboard class
		public static final int NONE            = 0x00;
		public static final int ESCAPE          = 0x01;
		public static final int KEY_1               = 0x02;
		public static final int KEY_2               = 0x03;
		public static final int KEY_3               = 0x04;
		public static final int KEY_4               = 0x05;
		public static final int KEY_5               = 0x06;
		public static final int KEY_6               = 0x07;
		public static final int KEY_7               = 0x08;
		public static final int KEY_8               = 0x09;
		public static final int KEY_9               = 0x0A;
		public static final int KEY_0               = 0x0B;
		public static final int MINUS           = 0x0C; /* - on main keyboard */
		public static final int EQUALS          = 0x0D;
		public static final int BACK            = 0x0E; /* backspace */
		public static final int TAB             = 0x0F;
		public static final int Q               = 0x10;
		public static final int W               = 0x11;
		public static final int E               = 0x12;
		public static final int R               = 0x13;
		public static final int T               = 0x14;
		public static final int Y               = 0x15;
		public static final int U               = 0x16;
		public static final int I               = 0x17;
		public static final int O               = 0x18;
		public static final int P               = 0x19;
		public static final int LBRACKET        = 0x1A;
		public static final int RBRACKET        = 0x1B;
		public static final int ENTER          = 0x1C; /* Enter on main keyboard */
		public static final int LCONTROL        = 0x1D;
		public static final int A               = 0x1E;
		public static final int S               = 0x1F;
		public static final int D               = 0x20;
		public static final int F               = 0x21;
		public static final int G               = 0x22;
		public static final int H               = 0x23;
		public static final int J               = 0x24;
		public static final int K               = 0x25;
		public static final int L               = 0x26;
		public static final int SEMICOLON       = 0x27;
		public static final int APOSTROPHE      = 0x28;
		public static final int GRAVE           = 0x29; /* accent grave */
		public static final int LSHIFT          = 0x2A;
		public static final int BACKSLASH       = 0x2B;
		public static final int Z               = 0x2C;
		public static final int X               = 0x2D;
		public static final int C               = 0x2E;
		public static final int V               = 0x2F;
		public static final int B               = 0x30;
		public static final int N               = 0x31;
		public static final int M               = 0x32;
		public static final int COMMA           = 0x33;
		public static final int PERIOD          = 0x34; /* . on main keyboard */
		public static final int SLASH           = 0x35; /* / on main keyboard */
		public static final int RSHIFT          = 0x36;
		public static final int MULTIPLY        = 0x37; /* * on numeric keypad */
		public static final int LMENU           = 0x38; /* left Alt */
		public static final int LALT            = LMENU; /* left Alt */
		public static final int SPACE           = 0x39;
		public static final int CAPITAL         = 0x3A;
		public static final int F1              = 0x3B;
		public static final int F2              = 0x3C;
		public static final int F3              = 0x3D;
		public static final int F4              = 0x3E;
		public static final int F5              = 0x3F;
		public static final int F6              = 0x40;
		public static final int F7              = 0x41;
		public static final int F8              = 0x42;
		public static final int F9              = 0x43;
		public static final int F10             = 0x44;
		public static final int NUMLOCK         = 0x45;
		public static final int SCROLL          = 0x46; /* Scroll Lock */
		public static final int NUMPAD7         = 0x47;
		public static final int NUMPAD8         = 0x48;
		public static final int NUMPAD9         = 0x49;
		public static final int SUBTRACT        = 0x4A; /* - on numeric keypad */
		public static final int NUMPAD4         = 0x4B;
		public static final int NUMPAD5         = 0x4C;
		public static final int NUMPAD6         = 0x4D;
		public static final int ADD             = 0x4E; /* + on numeric keypad */
		public static final int NUMPAD1         = 0x4F;
		public static final int NUMPAD2         = 0x50;
		public static final int NUMPAD3         = 0x51;
		public static final int NUMPAD0         = 0x52;
		public static final int DECIMAL         = 0x53; /* . on numeric keypad */
		public static final int F11             = 0x57;
		public static final int F12             = 0x58;
		public static final int F13             = 0x64; /*                     (NEC PC98) */
		public static final int F14             = 0x65; /*                     (NEC PC98) */
		public static final int F15             = 0x66; /*                     (NEC PC98) */
		public static final int KANA            = 0x70; /* (Japanese keyboard)            */
		public static final int CONVERT         = 0x79; /* (Japanese keyboard)            */
		public static final int NOCONVERT       = 0x7B; /* (Japanese keyboard)            */
		public static final int YEN             = 0x7D; /* (Japanese keyboard)            */
		public static final int NUMPADEQUALS    = 0x8D; /* = on numeric keypad (NEC PC98) */
		public static final int CIRCUMFLEX      = 0x90; /* (Japanese keyboard)            */
		public static final int AT              = 0x91; /*                     (NEC PC98) */
		public static final int COLON           = 0x92; /*                     (NEC PC98) */
		public static final int UNDERLINE       = 0x93; /*                     (NEC PC98) */
		public static final int KANJI           = 0x94; /* (Japanese keyboard)            */
		public static final int STOP            = 0x95; /*                     (NEC PC98) */
		public static final int AX              = 0x96; /*                     (Japan AX) */
		public static final int UNLABELED       = 0x97; /*                        (J3100) */
		public static final int NUMPADENTER     = 0x9C; /* Enter on numeric keypad */
		public static final int RCONTROL        = 0x9D;
		public static final int NUMPADCOMMA     = 0xB3; /* , on numeric keypad (NEC PC98) */
		public static final int DIVIDE          = 0xB5; /* / on numeric keypad */
		public static final int SYSRQ           = 0xB7;
		public static final int RMENU           = 0xB8; /* right Alt */
		public static final int RALT            = RMENU; /* right Alt */
		public static final int PAUSE           = 0xC5; /* Pause */
		public static final int HOME            = 0xC7; /* Home on arrow keypad */
		public static final int UP              = 0xC8; /* UpArrow on arrow keypad */
		public static final int PRIOR           = 0xC9; /* PgUp on arrow keypad */
		public static final int LEFT            = 0xCB; /* LeftArrow on arrow keypad */
		public static final int RIGHT           = 0xCD; /* RightArrow on arrow keypad */
		public static final int END             = 0xCF; /* End on arrow keypad */
		public static final int DOWN            = 0xD0; /* DownArrow on arrow keypad */
		public static final int NEXT            = 0xD1; /* PgDn on arrow keypad */
		public static final int INSERT          = 0xD2; /* Insert on arrow keypad */
		public static final int DELETE          = 0xD3; /* Delete on arrow keypad */
		public static final int LMETA           = 0xDB; /* Left Windows/Option key */
		public static final int LWIN            = LMETA; /* Left Windows key */
		public static final int RMETA           = 0xDC; /* Right Windows/Option key */
		public static final int RWIN            = RMETA; /* Right Windows key */
		public static final int APPS            = 0xDD; /* AppMenu key */
		public static final int POWER           = 0xDE;
		public static final int SLEEP           = 0xDF;

		private static boolean[] lastKeys = new boolean[NUM_KEYCODES];
		private static boolean[] lastMouse = new boolean[NUM_MOUSEBUTTONS];

		public static void tick(){
			for(int i = 0; i < NUM_KEYCODES; i++)
				lastKeys[i] = getKey(i);

			for(int i = 0; i < NUM_MOUSEBUTTONS; i++)
				lastMouse[i] = getMouse(i);
			
			if(focus != null)
				focus.focus();

			for(Button x : buttons)
        		if(x instanceof Hooverable && x.getRect().contains(getMousePosition().toPoint()))
        			((Hooverable) x).setHoover(true);
        		else if(x instanceof Hooverable)
        			((Hooverable) x).setHoover(false);
		}

		public static boolean getKey(int keyCode){
			return Keyboard.isKeyDown(keyCode);
		}

		public static boolean getKeyDown(int keyCode){
			return getKey(keyCode) && !lastKeys[keyCode];
		}

		public static boolean getKeyUp(int keyCode){
			return !getKey(keyCode) && lastKeys[keyCode];
		}

		private static boolean isForcedRelease = false, lockPress = false;;
		private static ArrayList<Button> buttons = new ArrayList<Button>();
		private static Focusable focus;
		
		public static void addButton(Button b){
			buttons.add(b);
		}
		
		public static void removeButton(Button b){
			buttons.remove(b);
		}
		
		/**
		 * 0 -> left click, 1 -> right click
		 * @param mouseButton
		 * @return
		 */
		public static boolean getMouse(int mouseButton){
			while (Mouse.next()){
			    if (Mouse.getEventButtonState()) {
			        if (Mouse.getEventButton() == 0) {
			        	for(Button x : buttons)
			        		x.recieveClick(new Point(Mouse.getEventX(), Window.getHeight() - Mouse.getEventY()));
			        }
			    }else{
			        if (Mouse.getEventButton() == 0) {
			        	focus = null;
			        }
			    }
			}
			return false;
		}
		
		public static void setFocus(Focusable newFocus){
			focus = newFocus;
		}
		
		public static boolean getMouseDown(int mouseButton){
			return Mouse.isButtonDown(mouseButton) /*&& !lastMouse[mouseButton]*/;
		}

		public static boolean getMouseUp(int mouseButton){
			return !getMouse(mouseButton) /*&& lastMouse[mouseButton]*/;
		}

		public static Vector2f getMousePosition(){
			return new Vector2f(Mouse.getX(), Window.getHeight() - Mouse.getY());
		}

		public static void setMousePosition(Vector2f pos){
			Mouse.setCursorPosition((int)pos.x, (int)pos.y);
		}

		public static void setCursor(boolean enabled){
			Mouse.setGrabbed(!enabled);
		}
		
		public static void forceRelease(){
			isForcedRelease = true;
		}
		
		public static void release(){
			isForcedRelease = false;
		}

		

}
