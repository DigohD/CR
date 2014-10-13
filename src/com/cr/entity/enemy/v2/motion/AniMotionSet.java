package com.cr.entity.enemy.v2.motion;

import java.util.HashMap;

public class AniMotionSet{

	public enum MotionStatus {IDLE, MOVING}
	
	protected AniMotion activeAniMotion;
	protected HashMap<MotionStatus, AniMotion> motionSet = new HashMap<MotionStatus, AniMotion>();
	
	public void addMotion(MotionStatus status, AniMotion motion){
		motionSet.put(status, motion);
	}
	
	public void setActiveMotion(MotionStatus status){
		activeAniMotion = motionSet.get(status);
	}
	
	public AniMotion getActiveMotion(MotionStatus status){
		return motionSet.get(status);
	}
}
