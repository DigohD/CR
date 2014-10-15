package com.cr.states.net;

import java.util.HashMap;
import java.util.LinkedList;

import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.gui.CRButton;
import com.cr.gui.CRTextField;
import com.cr.net.NetStatus;
import com.cr.states.GameState;
import com.cr.util.CRString;
import com.cr.util.FontLoader;

public class ClientInputState extends GameState{
	
	private CRTextField userNameField, ipField, portField;
	private CRButton join, exit;
	
	private String name = "", ipAddress = "";
	private String portNumber;
	
	private boolean userName = false, ip = false, port = false;
	private int timer = 0;
	private int delay = 7;

	private LinkedList<String> userNameChars = new LinkedList<String>();
	private LinkedList<String> ipChars = new LinkedList<String>();
	private LinkedList<String> portChars = new LinkedList<String>();
	
	private HashMap<Integer, Boolean> keyCodeMap = new HashMap<Integer, Boolean>();
	
	private Sprite txtActive, btnActive;
	
	private boolean joinBtn = false, exitBtn = false;
	
	public ClientInputState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		userNameField = new CRTextField("textfield", Window.getWidth()/2 - 200, Window.getHeight()/2 - 200);
		ipField = new CRTextField("textfield", Window.getWidth()/2 - 200, Window.getHeight()/2 - 100);
		portField = new CRTextField("textfield", Window.getWidth()/2 - 200, Window.getHeight()/2);
		
		join = new CRButton("crbutton", Window.getWidth()/2 - 55, Window.getHeight()/2 + 100 - 20);
		exit = new CRButton("crbutton", Window.getWidth()/2 - 55, Window.getHeight()/2 + 170 - 20);
		
		txtActive = new Sprite("textfieldactive", Game.shader, new Transform());
		btnActive = new Sprite("buttonHoover", Game.shader, new Transform());
	}
	
	@Override
	public void tick(float dt) {
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(Input.getKey(Input.ESCAPE))
			Game.stop();
		
		if(join.getRect().contains(Input.getMousePosition().x, Input.getMousePosition().y)){
			joinBtn = true;
		}else joinBtn = false;
		
		if(exit.getRect().contains(Input.getMousePosition().x, Input.getMousePosition().y)){
			exitBtn = true;
		}else exitBtn = false;
		
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
		
		if(join.isClicked()){
			NetStatus.isHOST = false;
			NetStatus.isMultiPlayer = true;
			gsm.push(new MPClientState(gsm, name, ipAddress, Integer.parseInt(portNumber)));
		}
		
		if(exit.isClicked())
			gsm.pop();
		
		if(timer % delay == 0){
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
		}
		

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
		
		portNumber = s2;
	}
	
	private void processInput(int key){
		if(userName)
			processInput(key, userNameChars, 8);
		if(ip)
			processInput(key, ipChars, 15);
		if(port)
			processInput(key, portChars, 8);
	}
	
	private void processInput(int key, LinkedList<String> list, int maxChars){
		if(Input.getKey(key) && key == Input.PERIOD && key != Input.BACK && !Input.getKey(Input.LSHIFT))
			if(list.size() <= maxChars)
				list.addLast(".");
		
		if(Input.getKey(key) && key != Input.PERIOD &&  key != Input.BACK && !Input.getKey(Input.LSHIFT))
			if(list.size() <= maxChars)
				list.addLast((Input.getKeyName(key).toLowerCase()));

		if(Input.getKey(key) && key != Input.PERIOD && key != Input.BACK && Input.getKey(Input.LSHIFT))
			if(list.size() <= maxChars)
				list.addLast(Input.getKeyName(key));

		if(Input.getKey(key) && key == Input.BACK)
			if(list.size() >= 1)
				list.removeLast();
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
		
		if(userName)
			screen.renderSprite(txtActive, userNameField.getxPos(), userNameField.getyPos());
		if(ip)
			screen.renderSprite(txtActive, ipField.getxPos(), ipField.getyPos());
		if(port)
			screen.renderSprite(txtActive, portField.getxPos(), portField.getyPos());
		
		join.render(screen);
		if(joinBtn) screen.renderSprite(btnActive, join.getxPos(), join.getyPos());
		
		exit.render(screen);
		if(exitBtn) screen.renderSprite(btnActive, exit.getxPos(), exit.getyPos());
			
		
		f.setFont(CRString.create("Join"));
		screen.renderFont(f, Window.getWidth()/2 - 50, Window.getHeight()/2 + 57 - 20, 0.4f);
		
		f.setFont(CRString.create("Exit"));
		screen.renderFont(f, Window.getWidth()/2 - 50, Window.getHeight()/2 + 130 - 20, 0.4f);
		
		f.setFont(CRString.create(name));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 240, 0.4f);
		
		f.setFont(CRString.create(ipAddress));
		screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 140, 0.4f);
		
		if(portChars.size() != 0){
			f.setFont(CRString.create(portNumber));
			screen.renderFont(f, Window.getWidth()/2 - 180, Window.getHeight()/2 - 40, 0.4f);
		}
		
		FontLoader.releaseFont(f);
	}

}
