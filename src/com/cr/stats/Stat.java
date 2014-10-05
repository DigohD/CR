package com.cr.stats;

import java.util.HashMap;

import com.cr.game.EntityManager;
import com.cr.net.NetStatus;
import com.cr.net.packets.Packet17Stat;
import com.cr.states.net.MPClientState;
import com.cr.stats.StatsSheet.StatID;

public class Stat{

	protected String name;
	protected float base, total;
	protected HashMap<String, Float> addMods = new HashMap<String, Float>();
	protected HashMap<String, Float> mulMods = new HashMap<String, Float>();
	protected boolean isHero;
	
	
	protected StatID id;
	
	public Stat(String name, float base){
		this.name = name;
		this.base = base;
	}
	
	public Stat(StatID id, String name, float base, boolean isHero){
		this.id = id;
		this.name = name;
		this.base = base;
		this.isHero = isHero;
		calculateTotal();
	}
	
	public void calculateTotal(){
		total = base;
		
		for(String x : addMods.keySet()){
			total = total + addMods.get(x);
		}
		
		float mulTotal = 1f;
		for(String x : mulMods.keySet()){
			mulTotal = mulTotal + mulMods.get(x);
		}
		
		total = total * mulTotal;
		
		if(NetStatus.isMultiPlayer && !NetStatus.isHOST && isHero && id != null){
			Packet17Stat packet = new Packet17Stat(MPClientState.getClient().getUserName(), id.name(), total);
			MPClientState.getClient().sendData(packet.getData());
		}
	}
	
	public void addAddmod(String ID, float amount){
		addMods.put(ID, amount);
		calculateTotal();
	}
	
	public void addMulmod(String ID, float amount){
		mulMods.put(ID, amount);
		calculateTotal();
	}
	
	public void removeAddmod(String ID){
		addMods.remove(ID);
		calculateTotal();
	}
	
	public void removeMulmod(String ID){
		mulMods.remove(ID);
		calculateTotal();
	}
	
	public void setNewBase(float newBase){
		base = newBase;
		calculateTotal();
	}

	public float getTotal(){
		if(!isHero)
			calculateTotal();
		return total;
	}

	public float getBase() {
		return base;
	}
}
