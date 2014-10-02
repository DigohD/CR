package com.cr.states.crafting;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.station.AddButton;
import com.cr.crafting.v2.station.CraftButton;
import com.cr.crafting.v2.station.Forge;
import com.cr.crafting.v2.station.PatternButton;
import com.cr.crafting.v2.station.ProcessButton;
import com.cr.engine.core.Transform;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.inventory.Hooverable;
import com.cr.entity.hero.materials.MaterialChoice;
import com.cr.entity.hero.materials.MaterialsBox;
import com.cr.game.Game;
import com.cr.game.GameStateManager;
import com.cr.states.GameState;
import com.cr.states.PlayState;
import com.cr.states.inventory.InventoryState;

public class ChooseSecState extends GameState{

	Transform t = new Transform();
	
	private Sprite bg = new Sprite("inventorybg", Game.shader, t);
	
	private CraftButton craft;
	private ExitButton exit;
	
	private Forge forge;
	
	ArrayList<MaterialChoice> matsChoices;
	
	public ChooseSecState(GameStateManager gsm, Forge forge) {
		super(gsm);
		blockRendering = false;
		
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		craft = new CraftButton(xOffset + 690, yOffset + 510);
		exit = new ExitButton(xOffset + 690, yOffset + 550);
		
		matsChoices = new ArrayList<MaterialChoice>();
		
		this.forge = forge;
		
		ArrayList<Material> mats = MaterialsBox.getMaterials();
		int counter = 0;
		for(Material x : mats){
			boolean alreadyAdded = false;
			for(Material m : forge.getMaterials())
				if(x.getName().equals(m.getName())){
					alreadyAdded = true;
					break;
				}
			if(alreadyAdded)
				continue;
			if(!x.isPrimary())
				matsChoices.add(new MaterialChoice(xOffset + 10 + (counter++ * 60), yOffset + 10, x));
		}
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		for(MaterialChoice x : matsChoices){
			x.tick(dt);
			if(x.isClicked()){
				craft.removeFromInput();
				exit.removeFromInput();
				
				for(MaterialChoice m : matsChoices)
					m.removeFromInput();
				
				gsm.pop();
				gsm.push(new AmountState(gsm, forge, x.getMaterial()));
				return;
			}
		}
		
		if(craft.isClicked()) {
			if(gsm.next() instanceof PlayState){
				PlayState ps = (PlayState) gsm.next();
			}
			
			forge.craft();
			
			craft.removeFromInput();
			exit.removeFromInput();
			
			for(MaterialChoice x : matsChoices)
				x.removeFromInput();
			
			gsm.pop();
		}
	}

	@Override
	public void render(Screen screen){
		int xOffset =  (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		for(MaterialChoice x : matsChoices)
			x.render(screen);
		
		craft.render(screen);
		exit.render(screen);
		
		for(MaterialChoice x : matsChoices)
			if(x instanceof Hooverable && x.isHoover())
				x.renderHoover(screen);;
	}
}
