package com.foscusgames.ecoquisactors;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.foscusgames.ecoquis.EQSounds;

/**
 * General class for particle explosions, derived from LibGDX Group (an Actor).
 * @author Camilo
 *
 */
public class ParticleExplosion extends Group {

	Array<Image> particles;
	int numParticles = 40;
	float particleWidth, particleHeight;
	float scale;
	Random r;
	//private boolean soundReady=false;
	
	
	public ParticleExplosion(AtlasRegion particle, float scale) {
		
		this.r = new Random();
		this.scale = scale;
		this.particleWidth = particle.getRegionWidth()*scale;
		this.particleHeight = particle.getRegionHeight()*scale;

		
		//Gdx.app.log("ParticleExplosion", "particleWidth="+particleWidth);
		
		particles = new Array<Image>();
		
		for (int i =0; i<numParticles; i++) {
			Image im = new Image (particle);
			im.setBounds(-particleWidth/2, -particleHeight/2,particleWidth, particleHeight);
			im.setColor(1,1,1,0);
			im.setTouchable(Touchable.disabled);
			particles.add(im);
			this.addActor(im);
			
		}
		
		//setActions();
		
	}
	
	private void setActions() {
		
		//soundReady = true;
		
		for (Image p : particles) {
			
			float distance = 200*scale*5+r.nextFloat()*200*scale*5;
			float duration = 2.5f+r.nextFloat()*0.5f;
			double theta = r.nextFloat()*2*Math.PI;
			
			p.addAction(Actions.moveBy((float)Math.cos(theta)*distance,(float)Math.sin(theta)*distance,duration,Interpolation.exp5Out));
			p.addAction(Actions.alpha(0,duration, Interpolation.exp5In));
			
		}
		
	}
	
	public void resetExplosion() {
		
		///soundReady = false;
		
		for (Image p : particles) {
					
			p.setPosition(-particleWidth/2, -particleHeight/2);
			p.setColor(1, 1, 1, 1);
			
		}
		
	}
	
//	@Override
//	public void act(float delta) {
//		super.act(delta);
//		
//		if (soundReady) {
//			soundReady = false;
//			EQSounds.playSound(EQSounds.faceCompleted);
//		}
//	}


	public void explode() {
		
		resetExplosion();
		setActions();
		
	}
    
    
	
}
