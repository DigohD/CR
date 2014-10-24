package com.cr.engine.graphics.shader;

import com.cr.engine.core.Transform;
import com.cr.engine.core.Vector3f;
import com.cr.engine.graphics.Window;
import com.cr.game.EntityManager;
import com.cr.util.Camera;
import com.cr.world.World;
import com.cr.world.tile.Tile;

public class PhongShader extends Shader{
	
	private World world;
	
	private Vector3f lightPosition,lightPosition2, ambientLight, eyePosition;
	
	private int timer = 0;
	
	private float t = 0;
	private float amplitudeWave = 5f;
	private float angleWave = 2.86f;
	private float angleWaveSpeed = 0.2f;
	private static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	private float lightX = 0;
	private float lightY = 0;
	private float lightZ = 0;
	
	private float lightX2 = 0;
	private float lightY2 = 0;
	private float lightZ2 = 0;
	
	private float k = 0;

	public PhongShader(World world) {
		super("phongVertShader", "phongFragShader");
		this.world = world;
		initUniforms();
	}
	
	private void initUniforms(){
		addUniform("transformation");
		addUniform("modelMatrix");
		addUniform("time");
		addUniform("waveDataX");
		addUniform("waveDataY");
		addUniform("isWater");
		addUniform("lightPosition");
		addUniform("lightPosition2");
		addUniform("scene_ambient_light");
		addUniform("k");
		
		addUniform("sampler");
		addUniform("normalMapWater");
		addUniform("normalMapGrass");
		addUniform("cubeMap0");
		
		addUniform("material_shininess");
		addUniform("material_diffuse_color");
		addUniform("material_specular_color");
		addUniform("material_emissive_color");
		
		bind();
		setUniformi("sampler", 0);
		setUniformi("normalMapWater", 1);
		setUniformi("normalMapGrass", 2);
		unbind();
	}
	
	public void initLights(World world){
		lightX = -1000;
		lightY = (world.getHeight() * Tile.getTileHeight()) / 2;
		lightZ = 0;
		
		lightX2 = EntityManager.getHero().getX() + 1000;
		lightY2 = EntityManager.getHero().getY();
		lightZ2 = 0;
		
		lightPosition = World.getTransform().getModelMatrix().mul(new Vector3f(lightX, lightY, lightZ));
		lightPosition2 = World.getTransform().getModelMatrix().mul(new Vector3f(lightX2, lightY2, lightZ2));
		
		ambientLight = new Vector3f(0.1f, 0.1f, 0.3f);
	}
	
	private void dayNightCycle(float dt, int timer){
		if(timer % 2 == 0){
			t += dt*0.008f;
			
			if(t >= PI2) t = 0;
			
			if(t > 0 && t <= 3.14f/6.0f)
				k += 0.0008f;
			
			if(t > ((5.0*3.14f) / 6.0f) && t <= 3.14f)
				k -= 0.0008f;

			lightPosition2.y = Camera.getCamY() + Window.getHeight()/2;
			lightPosition2.x = lightX2 + ( -1.0f * (float)(Math.cos((-t-3.14f))) * 2000);
			lightPosition2.z = -600 * (float) Math.sin(t);
			
			lightPosition.x = lightX + (-1.0f * ((float) Math.cos(t)) * (2000 + (world.getWidth() * Tile.getTileWidth())));
			lightPosition.z = -10000 * (float) Math.sin(t);
		}
		
	}
	
	public void updateUniforms(Transform transform){
		bind();
		
		setUniformf("waveDataX", angleWave);
		setUniformf("waveDataY", amplitudeWave);
		setUniform("transformation", transform.getOrthoTransformation());
		setUniform("modelMatrix", transform.getModelMatrix());
		setUniformf("lightPosition", lightPosition);
		setUniformf("lightPosition2", lightPosition2);
		setUniformf("scene_ambient_light", ambientLight);
		setUniformf("time", t);
		setUniformf("k", k);
		
		unbind();
	}
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
	
		dayNightCycle(dt, timer);
		
		angleWave += dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;
	}

}
