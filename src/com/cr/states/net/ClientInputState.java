package com.cr.states.net;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.gui.CRButton;
import com.cr.states.GameState;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class ClientInputState extends GameState{
	
	private CRButton userNameField, ipField, portField, finished;
	
	private String name = "", ipAddress = "";
	private int portNumber;
	
	private boolean userName = false, ip = false, port = false;
	private int timer = 0;

	private List<String> userNameChars = new ArrayList<String>();
	private List<String> ipChars = new ArrayList<String>();
	private List<String> portChars = new ArrayList<String>();
	
	public ClientInputState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		userNameField = new CRButton("textfield", Window.getWidth()/2 - 200, Window.getHeight()/2 - 200);
		ipField = new CRButton("textfield", Window.getWidth()/2 - 200, Window.getHeight()/2 - 100);
		portField = new CRButton("textfield", Window.getWidth()/2 - 200, Window.getHeight()/2);
		
	}
	
	@Override
	public void tick(float dt) {
		if(timer < 7500) timer++;
		else timer = 0;
		if(Input.getKey(Input.ESCAPE))
			Game.stop();
		
		if(userNameField.isClicked()){
			userName = true;
			ip = false;
			port = false;
		}
		
		if(ipField.isClicked()){
			userName = false;
			ip = true;
			port = false;
		}
		
		if(portField.isClicked()){
			userName = false;
			ip = false;
			port = true;
		}
		
		processInput(Input.A);
		processInput(Input.B);
		processInput(Input.C);
		processInput(Input.D);
		processInput(Input.E);
		processInput(Input.F);
		processInput(Input.G);
		processInput(Input.H);
		processInput(Input.I);
		processInput(Input.J);
		processInput(Input.K);
		processInput(Input.L);
		processInput(Input.M);
		processInput(Input.N);
		processInput(Input.O);
		processInput(Input.P);
		processInput(Input.Q);
		processInput(Input.R);
		processInput(Input.S);
		processInput(Input.T);
		processInput(Input.U);
		processInput(Input.V);
		processInput(Input.W);
		processInput(Input.Y);
		processInput(Input.Z);
		
		//processInput(Input.PERIOD);
		
		processInput(Input.KEY_0);
		processInput(Input.KEY_1);
		processInput(Input.KEY_2);
		processInput(Input.KEY_3);
		processInput(Input.KEY_4);
		processInput(Input.KEY_5);
		processInput(Input.KEY_6);
		processInput(Input.KEY_7);
		processInput(Input.KEY_8);
		processInput(Input.KEY_9);

		String s = "";
		
		for(String str : userNameChars)
			s = s + str;
		
		name = s;
		
		String s1 = "";
		
		for(String str : ipChars)
			s1 = s1 + str;
		
		ipAddress = s1;
		
		String s2 = "";
		
		for(String str : portChars)
			s2 = s2 + str;
		
		if(s2 != "")
			portNumber = Integer.parseInt(s2);
	}
	
	
	private void processInput(int key){
		if(userName)
			processInput(key, userNameChars);
		if(ip)
			processInput(key, ipChars);
		if(port)
			processInput(key, portChars);
	}
	
	private void processInput(int key, List<String> list){
		if(Input.getKey(key) && !Input.getKey(Input.LSHIFT)&& timer % 7 == 0)
			if(list.size() <= 15)
				list.add(Input.getKeyName(key).toLowerCase());

		if(Input.getKey(key) && Input.getKey(Input.LSHIFT) && timer % 7 == 0)
			if(list.size() <= 15)
				list.add(Input.getKeyName(key));

		if(Input.getKey(Input.BACK) && timer % 7 == 0)
			if(list.size() >= 1)
				list.remove(list.size()-1);
	}

	@Override
	public void render(Screen screen) {
		Font f = FontLoader.aquireFont(FontColor.WHITE);
		
		f.setFont(CRString.create("Enter a username:"));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 290, 0.4f);
		
		f.setFont(CRString.create("Enter server ip:"));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 190, 0.4f);
		
		f.setFont(CRString.create("Enter server port:"));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 90, 0.4f);
		
		userNameField.render(screen);
		ipField.render(screen);
		portField.render(screen);
		
		f.setFont(CRString.create(name));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 240, 0.4f);
		
		f.setFont(CRString.create(ipAddress));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 140, 0.4f);
		
		f.setFont(CRString.create("" + portNumber));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 40, 0.4f);
		
		
		FontLoader.releaseFont(f);
		
	}

}
