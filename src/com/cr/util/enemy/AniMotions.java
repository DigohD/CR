package com.cr.util.enemy;

import com.cr.entity.enemy.v2.motion.AniMotion;
import com.cr.entity.enemy.v2.motion.SinusMotion;
import com.cr.entity.enemy.v2.motion.SinusMotion.MotionAxis;

public class AniMotions{

	public static AniMotion getBreathing(float strength, float intensity){
		AniMotion newAM = new AniMotion();
		
		newAM.addMotion(new SinusMotion(3 * strength, 0.4f * intensity, 0, MotionAxis.Y));
		newAM.addMotion(new SinusMotion(2 * (strength * 0.66f), 0.13f * (intensity * 0.66f), 90, MotionAxis.X));
		
		return newAM;
	}
	
}
