package com.cr.crafting.v2.material;

import java.awt.Image;
import java.util.ArrayList;

import com.cr.crafting.v2.property.Property;
import com.cr.engine.graphics.Font;
import com.cr.engine.graphics.Font.FontColor;
import com.cr.engine.graphics.Screen;
import com.cr.engine.graphics.Sprite;
import com.cr.item.Item;
import com.cr.stats.Stat;
import com.cr.stats.StatMod;
import com.cr.util.CRString;
import com.cr.util.FontLoader;
import com.cr.util.Randomizer;

public abstract class Material{

	public enum State{BALANCED, TEMPERED, HARDENED, FLASHED, BLASTED, BROKEN}
	
	public enum Quality{NORMAL, SUPERB, MASTERFUL, LEGENDARY}
	
	protected State state;
	protected Quality quality = Quality.NORMAL;
	
	protected int lowerHeatLimit, higherHeatLimit;
	protected int lowerTimeLimit, higherTimeLimit;
	protected int balancedValue;
	protected int processCount;
	protected int heatMidPoint, timeMidPoint;
	
	protected int newLowerHeatLimit;
	protected int newHigherHeatLimit;
	protected int newLowerTimeLimit;
	protected int newHigherTimeLimit;
	protected int newBalancedValue;
	
	protected int amount, usedAmount;
	protected float secBonus = 1f, baseBonus = 1f, primbonus = 1f;
	
	protected boolean breakable, isPrimary;
	
	protected ArrayList<Property> properties;
	
	public abstract String getName();
	
	public boolean process(int heat, int time, ArrayList<Material> materials){
		breakable = true;
		
		for(Material m : materials){
			m.resetSpans();
			for(Property p : m.getProperties()){
				if(m.getName().equals(getName()))
					p.affectParentMaterialPreState(this, state, materials);
				else
					p.affectOtherMaterialPreState(this, state, materials);
			}
		}
		
		state = calculateState(heat, time, breakable);
		
		for(Material m : materials){
			for(Property p : m.getProperties()){
				if(m.getName().equals(getName()))
					p.affectParentMaterialPostState(this, state, materials);
				else
					p.affectOtherMaterialPostState(this, state, materials);
			}
		}
		
		if(breakable && state == State.BROKEN)
			return false;
		else{
			calculateUnbrokenState(heat, time);
			return true;
		}
	}
	
	public State calculateState(int heat, int time, boolean breakable){
		boolean left = false, right = false, upper = false, lower = false;
		
		if(heat <= heatMidPoint)
			left = true;
		else
			right = true;
		
		if(time <= timeMidPoint)
			lower = true;
		else
			upper = true;
			
		int x = heatMidPoint - heat;
		int y = timeMidPoint - time;
		int hypo = (int) Math.sqrt((x * x) + (y * y));
		
		if(hypo < balancedValue)
			return State.BALANCED;
		
		if(breakable){
			if(heat < newLowerHeatLimit)
				return State.BROKEN;
			if(time < newLowerTimeLimit)
				return State.BROKEN;
			if(heat > newHigherHeatLimit)
				return State.BROKEN;
			if(time > newHigherTimeLimit)
				return State.BROKEN;
		}
		
		if(left && upper)
			return State.TEMPERED;
		if(left && lower)
			return State.FLASHED;
		if(right && lower)
			return State.BLASTED;
		if(right && upper)
			return State.HARDENED;
		
		return null;
	}
	
	protected void calculateMids(){
		heatMidPoint = ((higherHeatLimit - lowerHeatLimit) / 2) + lowerHeatLimit;
		timeMidPoint = ((higherTimeLimit - lowerTimeLimit) / 2) + lowerTimeLimit;
	}
	
	public State calculateUnbrokenState(int heat, int time){
		boolean left = false, right = false, upper = false, lower = false;
		
		if(heat <= heatMidPoint)
			left = true;
		else
			right = true;
		
		if(time <= timeMidPoint)
			lower = true;
		else
			upper = true;
		
		int rnd = Randomizer.getInt(1, 100);
		if(rnd < 2)
			quality = Quality.NORMAL;
		else if(rnd < 3)
			quality = Quality.SUPERB;
		else if(rnd < 4)
			quality = Quality.MASTERFUL;
		else
			quality = Quality.LEGENDARY;
		
		setQualityMods();
		
		if(left && upper)
			return State.TEMPERED;
		if(left && lower)
			return State.FLASHED;
		if(right && lower)
			return State.BLASTED;
		if(right && upper)
			return State.HARDENED;
		
		return null;
	}
	
	public void modifyHeatSpan(float percent){
		percent = percent / 100;
		int diff = (int) ((newHigherHeatLimit - newLowerHeatLimit) * Math.abs(percent));
		if(percent < 0){
			newHigherHeatLimit = (int) (newHigherHeatLimit - diff);
			newLowerHeatLimit = (int) (newLowerHeatLimit + diff);
		}else{
			newHigherHeatLimit = (int) (newHigherHeatLimit + diff);
			newLowerHeatLimit = (int) (newLowerHeatLimit - diff);
		}
	}
	
	public void modifyTimeSpan(float percent){
		percent = percent / 100;
		int diff = (int) ((newHigherTimeLimit - newLowerTimeLimit) * Math.abs(percent));
		if(percent < 0){
			newHigherTimeLimit = (int) (newHigherTimeLimit - diff);
			newLowerTimeLimit = (int) (newLowerTimeLimit + diff);
		}else{
			newHigherTimeLimit = (int) (newHigherTimeLimit + diff);
			newLowerTimeLimit = (int) (newLowerTimeLimit - diff);
		}
	}
	
	public void modifyBaseHeatSpan(float percent){
		percent = percent / 100;
		int diff = (int) ((higherHeatLimit - lowerHeatLimit) * Math.abs(percent));
		if(percent < 0){
			higherHeatLimit = (int) (higherHeatLimit - diff);
			lowerHeatLimit = (int) (lowerHeatLimit + diff);
		}else{
			higherHeatLimit = (int) (higherHeatLimit + diff);
			lowerHeatLimit = (int) (lowerHeatLimit - diff);
		}
	}
	
	public void modifyBaseTimeSpan(float percent){
		percent = percent / 100;
		int diff = (int) ((higherTimeLimit - lowerTimeLimit) * Math.abs(percent));
		if(percent < 0){
			higherTimeLimit = (int) (higherTimeLimit - diff);
			lowerTimeLimit = (int) (lowerTimeLimit + diff);
		}else{
			higherTimeLimit = (int) (higherTimeLimit + diff);
			lowerTimeLimit = (int) (lowerTimeLimit - diff);
		}
	}
	
	public ArrayList<StatMod> generateStat(boolean isWeapon){
		ArrayList<StatMod> stats = new ArrayList<StatMod>();
		if(isWeapon){
			getWeaponStats(stats);
			System.out.println();
			System.out.println(this.getState().name() + " " + this.getName() + " stats!");
			for(StatMod sm : stats)
				System.out.println(sm.getAffectedStat().name() + ": " + sm.getAmount());
			System.out.println();
			return stats;
		}else{
			getArmorStats(stats);
			System.out.println();
			System.out.println(this.getName() + " stats!");
			for(StatMod sm : stats)
				System.out.println(sm.getAffectedStat().name() + ": " + sm.getAmount());
			System.out.println();
			return stats;
		}
	}
	
	public abstract int getID();
	
	public abstract Sprite getMaterialImage();
	public abstract ArrayList<StatMod> getWeaponStats(ArrayList<StatMod> stats);
	public abstract ArrayList<StatMod> getArmorStats(ArrayList<StatMod> stats);
	public abstract void applyQualityBonuses(Item i, String sourceID);
	
	protected abstract void setQualityMods();
	protected abstract void newMods();
	
	public void renderStatus(Screen screen, int xOffset, int yOffset){
		Font f = FontLoader.aquireFont(FontColor.BLACK);
		
		f.setFont(CRString.create(getName() + ":"));
		screen.renderFont(f, xOffset, yOffset, 0.3f);
		
		String stateS = state.name().toLowerCase();
		stateS = Character.toUpperCase(stateS.charAt(0)) + stateS.substring(1);
		f.setFont(CRString.create(stateS));
		screen.renderFont(f, xOffset + 150, yOffset, 0.3f);
		
		String qualityS = quality.name().toLowerCase();
		qualityS = Character.toUpperCase(qualityS.charAt(0)) + qualityS.substring(1);
		f.setFont(CRString.create(qualityS));
		screen.renderFont(f, xOffset + 300, yOffset, 0.3f);
		
		FontLoader.releaseFont(f);
	}
	
	public void resetSpans(){
		newHigherHeatLimit = higherHeatLimit;
		newLowerHeatLimit = lowerHeatLimit;
		newHigherTimeLimit = higherTimeLimit;
		newLowerTimeLimit = lowerTimeLimit;
	}
	
	public void explode(){
		
	}
	
	public void setUsedAmount(int usedAmount) {
		this.usedAmount = usedAmount;
		newMods();
	}
	
	public int getLowerHeatLimit() {
		return lowerHeatLimit;
	}

	public void setLowerHeatLimit(int lowerHeatLimit) {
		this.lowerHeatLimit = lowerHeatLimit;
	}

	public int getHigherHeatLimit() {
		return higherHeatLimit;
	}

	public void setHigherHeatLimit(int higherHeatLimit) {
		this.higherHeatLimit = higherHeatLimit;
	}

	public int getLowerTimeLimit() {
		return lowerTimeLimit;
	}

	public void setLowerTimeLimit(int lowerTimeLimit) {
		this.lowerTimeLimit = lowerTimeLimit;
	}

	public int getHigherTimeLimit() {
		return higherTimeLimit;
	}

	public void setHigherTimeLimit(int higherTimeLimit) {
		this.higherTimeLimit = higherTimeLimit;
	}

	public int getBalancedValue() {
		return balancedValue;
	}

	public void setBalancedValue(int balancedValue) {
		this.balancedValue = balancedValue;
	}

	public boolean isBreakable() {
		return breakable;
	}

	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	public int getProcessCount() {
		return processCount;
	}

	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}
	
	public int getNewLowerHeatLimit() {
		return newLowerHeatLimit;
	}
	
	public void setNewLowerHeatLimit(int newLowerHeatLimit) {
		this.newLowerHeatLimit = newLowerHeatLimit;
	}
	
	public int getNewHigherHeatLimit() {
		return newHigherHeatLimit;
	}
	
	public void setNewHigherHeatLimit(int newHigherHeatLimit) {
		this.newHigherHeatLimit = newHigherHeatLimit;
	}
	
	public int getNewLowerTimeLimit() {
		return newLowerTimeLimit;
	}
	
	public void setNewLowerTimeLimit(int newLowerTimeLimit) {
		this.newLowerTimeLimit = newLowerTimeLimit;
	}
	
	public int getNewHigherTimeLimit() {
		return newHigherTimeLimit;
	}
	
	public void setNewHigherTimeLimit(int newHigherTimeLimit) {
		this.newHigherTimeLimit = newHigherTimeLimit;
	}
	
	public int getNewBalancedValue() {
		return newBalancedValue;
	}
	
	public void setNewBalancedValue(int newBalancedValue) {
		this.newBalancedValue = newBalancedValue;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void addAmount(int amount) {
		this.amount = this.amount + amount;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public int getUsedAmount() {
		return usedAmount;
	}
}
