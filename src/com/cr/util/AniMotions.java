package com.cr.util;

import com.cr.entity.enemy.v2.motion.AniMotion;
import com.cr.entity.enemy.v2.motion.SinusMotion;
import com.cr.entity.enemy.v2.motion.SinusMotion.MotionAxis;

public class AniMotions{

	public static AniMotion getBreathing(){
		AniMotion newAM = new AniMotion();
		
		newAM.addMotion(new SinusMotion(3, 0.4f, 0, MotionAxis.Y));
		newAM.addMotion(new SinusMotion(2, 0.13f, 90, MotionAxis.X));
		
		return newAM;
	}
	
}
