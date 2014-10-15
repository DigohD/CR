package com.cr.states.net;

import java.util.LinkedList;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.gui.CRButton;
import com.cr.gui.CRTextField;
import com.cr.states.GameState;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class ServerInputState extends GameState{

	public ServerInputState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	private CRTextField userNameField, portField;
	private CRButton host, exit;
	
	private String name = "";
	private String portNumber;
	
	private boolean userName = false, port = false;
	private int timer = 0;
	private int delay = 7;

	private LinkedList<String> userNameChars = new LinkedList<String>();
	private LinkedList<String> portChars = new LinkedList<String>();
	
	private Sprite txtActive, btnActive;
	
	private boolean hostBtn = false, exitBtn = false;

	@Override
	public void init() {
		userNameField = new CRTextField("textfield", Window.getWidth()/2 - 200, Window.getHeight()/2 - 200);
		portField = new CRTextField("textfield", Window.getWidth()/2 - 200, Window.getHeight()/2 - 100);
		
		host = new CRButton("crbutton", Window.getWidth()/2 - 55, Window.getHeight()/2 + 100 - 80 - 44);
		exit = new CRButton("crbutton", Window.getWidth()/2 - 55, Window.getHeight()/2 + 170 - 80 - 44);
		
		txtActive = new Sprite("textfieldactive", Game.shader, new Transform());
		btnActive = new Sprite("buttonHoover", Game.shader, new Transform());
	}
	
	@Override
	public void tick(float dt) {
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(Input.getKey(Input.ESCAPE))
			Game.stop();
		
		if(host.getRect().contains(Input.getMousePosition().x, Input.getMousePosition().y)){
			hostBtn = true;
		}else hostBtn = false;
		
		if(exit.getRect().contains(Input.getMousePosition().x, Input.getMousePosition().y)){
			exitBtn = true;
		}else exitBtn = false;
		
		if(userNameField.isClicked()){
			userName = true;
			port = false;
		}
	
		if(portField.isClicked()){
			userName = false;
			port = true;
		}
		
		if(host.isClicked()){
			gsm.push(new MPHostState(gsm, name, Integer.parseInt(portNumber)));
		}
		
		if(exit.isClicked())
			gsm.pop();
		
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
		
		processInput(Input.PERIOD);
		processInput(Input.BACK);
		
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

		String s2 = "";
		
		for(String str : portChars)
			s2 = s2 + str;
		
		portNumber = s2;
	}
	
	private void processInput(int key){
		if(userName)
			processInput(key, userNameChars, 8);
		if(port)
			processInput(key, portChars, 8);
	}
	
	private void processInput(int key, LinkedList<String> list, int maxChars){
		if(Input.getKey(key) && key == Input.PERIOD && key != Input.BACK && !Input.getKey(Input.LSHIFT) && timer % delay == 0)
			if(list.size() <= maxChars)
				list.addLast(".");
		
		if(Input.getKey(key) && key != Input.PERIOD &&  key != Input.BACK && !Input.getKey(Input.LSHIFT) && timer % delay == 0)
			if(list.size() <= maxChars)
				list.addLast((Input.getKeyName(key).toLowerCase()));

		if(Input.getKey(key) && key != Input.PERIOD && key != Input.BACK && Input.getKey(Input.LSHIFT) && timer % delay == 0)
			if(list.size() <= maxChars)
				list.addLast(Input.getKeyName(key));

		if(Input.getKey(key) && key == Input.BACK && timer % delay == 0)
			if(list.size() >= 1)
				list.removeLast();
	}

	@Override
	public void render(Screen screen) {
		Font f = FontLoader.aquireFont(FontColor.WHITE);
		
		f.setFont(CRString.create("Enter a username:"));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 290, 0.4f);
		
		f.setFont(CRString.create("Enter port number:"));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 190, 0.4f);
	
		userNameField.render(screen);
		portField.render(screen);
		
		if(userName)
			screen.renderSprite(txtActive, userNameField.getxPos(), userNameField.getyPos());
		if(port)
			screen.renderSprite(txtActive, portField.getxPos(), portField.getyPos());
		
		host.render(screen);
		if(hostBtn) screen.renderSprite(btnActive, host.getxPos(), host.getyPos());
		
		exit.render(screen);
		if(exitBtn) screen.renderSprite(btnActive, exit.getxPos(), exit.getyPos());
		
		f.setFont(CRString.create("Host"));
		screen.renderFont(f, Window.getWidth()/2 - 50, Window.getHeight()/2 + 57 - 80 - 40, 0.4f);
		
		f.setFont(CRString.create("Exit"));
		screen.renderFont(f, Window.getWidth()/2 - 50, Window.getHeight()/2 + 130 - 80 - 44, 0.4f);
		
		f.setFont(CRString.create(name));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 240, 0.4f);
		
		if(portChars.size() != 0){
			f.setFont(CRString.create(portNumber));
			screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 140, 0.4f);
		}
		
		FontLoader.releaseFont(f);
	}

}
