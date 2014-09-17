package com.cr.states;

import java.util.ArrayList;

import com.cr.crafting.v2.material.Material;
import com.cr.crafting.v2.pattern.Pattern;
import com.cr.crafting.v2.station.AddButton;
import com.cr.crafting.v2.station.CraftButton;
import com.cr.crafting.v2.station.Forge;
import com.cr.crafting.v2.station.PatternButton;
import com.cr.crafting.v2.station.ProcessButton;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.engine.graphics.Window;
import com.cr.engine.input.Input;
import com.cr.entity.hero.Hero;
import com.cr.entity.hero.inventory.ExitButton;
import com.cr.entity.hero.inventory.Hooverable;
import com.cr.entity.hero.inventory.Inventory;
import com.cr.entity.hero.materials.MaterialChoice;
import com.cr.entity.hero.materials.MaterialsBox;
import com.cr.game.GameStateManager;
import com.cr.input.KeyInput;
import com.cr.util.Camera;

public class CraftingState extends GameState{

	private Sprite bg = new Sprite("inventorybg");
	private MaterialsBox materials;
	private Material activeMaterial;
	
	private AddButton add;
	private ProcessButton process;
	private PatternButton pattern;
	private CraftButton craft;
	private ExitButton exit;
	
	private Forge forge;
	
	ArrayList<MaterialChoice> matsChoices;
	
	private Sprite slotSprite;
	
	public CraftingState(GameStateManager gsm, Forge forge) {
		super(gsm);
		blockRendering = false;
		
		slotSprite = new Sprite("slot");
		
		int xOffset = (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		
		matsChoices = new ArrayList<MaterialChoice>();
		
		this.forge = forge;
		
		ArrayList<Material> mats = MaterialsBox.getMaterials();
		int counter = 0;
		for(Material x : mats)
			matsChoices.add(new MaterialChoice(xOffset + 10 + (counter * 60), yOffset + 10, x));
		
		pattern = new PatternButton(600 + xOffset, 294 + yOffset);
		add = new AddButton(600 + xOffset, 354 + yOffset);
		process = new ProcessButton(600 + xOffset, 414 + yOffset);
		craft = new CraftButton(600 + xOffset, 474 + yOffset);
		exit = new ExitButton(600 + xOffset, 534 + yOffset);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void tick(float dt) {
		add.tick(dt);
		pattern.tick(dt);
		process.tick(dt);
		craft.tick(dt);
		exit.tick(dt);
		
		for(MaterialChoice x : matsChoices){
			x.tick(dt);
			if(x.isClicked())
				activeMaterial = x.getMaterial();
		}
		
		if(Input.getKey(Input.SPACE) || exit.isClicked()) {
			if(gsm.next() instanceof PlayState){
				PlayState ps = (PlayState) gsm.next();
				ps.bg = false;
			}
			
			add.removeFromInput();
			pattern.removeFromInput();
			process.removeFromInput();
			craft.removeFromInput();
			exit.removeFromInput();
			
			for(MaterialChoice x : matsChoices)
				x.removeFromInput();
			
			gsm.pop();
		}if(pattern.isClicked()){
			gsm.push(new PatternState(gsm, forge));
		}if(process.isClicked()){
			gsm.push(new ProcessState(gsm, forge));
		}if(add.isClicked() && activeMaterial != null){
			gsm.push(new AmountState(gsm, forge, activeMaterial));
		}if(craft.isClicked() && activeMaterial != null){
			forge.craft();
			gsm.pop();
			gsm.push(new InventoryState(gsm));
		}
	}

	@Override
	public void render(Screen screen){
		int xOffset =  (Window.getWidth() - 800) / 2;
		int yOffset = (Window.getHeight() - 600) / 2;
		screen.renderStaticSprite(bg, xOffset, yOffset);
		
		for(MaterialChoice x : matsChoices)
			x.render(screen);
		
		add.render(screen);
		process.render(screen);
		pattern.render(screen);
		craft.render(screen);
		exit.render(screen);
		
		for(MaterialChoice x : matsChoices)
			if(x instanceof Hooverable && x.isHoover())
				x.renderHoover(screen);;
	}

}
