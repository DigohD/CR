package com.cr.crafting.v2.material;

import java.util.ArrayList;

import com.cr.crafting.v2.property.Property;

public abstract class Material{

	public enum State{BALANCED, TEMPERED, HARDENED, FLASHED, BLASTED, BROKEN}
	
	protected State state;
	
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
	
	protected boolean breakable;
	
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
	
	public void resetSpans(){
		newHigherHeatLimit = higherHeatLimit;
		newLowerHeatLimit = lowerHeatLimit;
		newHigherTimeLimit = higherTimeLimit;
		newLowerTimeLimit = lowerTimeLimit;
	}
	
	public void explode(){
		
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
	
	
	
	
	
}
