package com.cr.util;

import com.cr.engine.datastructure.LinkedStack;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;

public class FontLoader {

	private static LinkedStack<Font> whiteFonts = new LinkedStack<Font>();
	private static LinkedStack<Font> yellowFonts = new LinkedStack<Font>();
	private static LinkedStack<Font> blackFonts = new LinkedStack<Font>();
	
	public FontLoader(){
		whiteFonts.push(new Font("                               ", FontColor.WHITE, true));
		whiteFonts.push(new Font("                               ", FontColor.WHITE, true));
		whiteFonts.push(new Font("                               ", FontColor.WHITE, true));
		whiteFonts.push(new Font("                               ", FontColor.WHITE, true));
		
		yellowFonts.push(new Font("                               ", FontColor.WHITE, true));
		yellowFonts.push(new Font("                               ", FontColor.WHITE, true));
		yellowFonts.push(new Font("                               ", FontColor.WHITE, true));
		yellowFonts.push(new Font("                               ", FontColor.WHITE, true));
		
		blackFonts.push(new Font("                               ", FontColor.BLACK, true));
	}
	
	public static Font aquireFont(FontColor color){
		if(color == FontColor.WHITE){
			if(whiteFonts.peek() != null){
				Font ret = whiteFonts.peek();
				whiteFonts.pop();
				return ret;
			}
		}else if(color == FontColor.YELLOW){
			if(yellowFonts.peek() != null){
				Font ret = yellowFonts.peek();
				yellowFonts.pop();
				return ret;
			}
		}else if(color == FontColor.BLACK){
			if(blackFonts.peek() != null){
				Font ret = blackFonts.peek();
				blackFonts.pop();
				return ret;
			}
		}
		
		return null;
	}
	
	public static void releaseFont(Font font){
		if(font.getColor() == FontColor.WHITE)
			whiteFonts.push(font);
		else if(font.getColor() == FontColor.YELLOW)
			yellowFonts.push(font);
		else if(font.getColor() == FontColor.BLACK)
			blackFonts.push(font);
	}
	
}
