package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;


/**
 * Special type of animation made to be replayed in a Ping pong loop every r seconds, where r is random.
 * Extends the general Animation class from LibGDX.
 * @author Camilo
 *
 */
public class BlinkAnimation extends Animation{


	TextureRegionDrawable[] keyFrames;
	float timeBetweenBlinks;
	private boolean blinking=false;
	private boolean firstPass=true;
	private boolean hasBlinked=false;
	private float lastBlinkTime=0;
	private float blinkTimeRange = 2;
	
	public BlinkAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames) {
		super(frameDuration, keyFrames);
		
		this.keyFrames = new TextureRegionDrawable[keyFrames.size];
		for (int i = 0, n = keyFrames.size; i < n; i++) {
			this.keyFrames[i] = new TextureRegionDrawable(keyFrames.get(i));
		}
		
		setPlayMode(PlayMode.LOOP_PINGPONG);
		
		timeBetweenBlinks = MathUtils.random(blinkTimeRange)+0.2f;
	}
	
	public BlinkAnimation (float frameDuration, TextureRegion... keyFrames) {
		super(frameDuration, keyFrames);
		
		this.keyFrames = new TextureRegionDrawable[keyFrames.length];
		for (int i = 0, n = keyFrames.length; i < n; i++) {
			this.keyFrames[i] = new TextureRegionDrawable(keyFrames[i]);
		}

		setPlayMode(PlayMode.LOOP_PINGPONG);
		
		timeBetweenBlinks = MathUtils.random(blinkTimeRange)+0.2f;
	}
	
	public BlinkAnimation (float frameDuration, float blinkTR, TextureRegion... keyFrames) {
		super(frameDuration, keyFrames);
		
		this.keyFrames = new TextureRegionDrawable[keyFrames.length];
		for (int i = 0, n = keyFrames.length; i < n; i++) {
			this.keyFrames[i] = new TextureRegionDrawable(keyFrames[i]);
		}

		setPlayMode(PlayMode.LOOP_PINGPONG);
		
		blinkTimeRange = blinkTR;
		
		timeBetweenBlinks = MathUtils.random(blinkTimeRange)+0.2f;
	}
	
//	@Override
//	public int getKeyFrameIndex (float stateTime) {
//
//		
//			
//			int frameNumber = (int)(stateTime / getFrameDuration());
//			frameNumber = frameNumber % ((keyFrames.length * 2) - 2);
//			if (frameNumber >= keyFrames.length) frameNumber = keyFrames.length - 2 - (frameNumber - keyFrames.length);	
//			
//			
//	}
//	
	public TextureRegionDrawable getDrawableKeyFrame (float stateTime) {
			

		int frameNumber;
		
		if (stateTime - lastBlinkTime > timeBetweenBlinks) {
			
			blinking = true;
			
			if (hasBlinked) {
				lastBlinkTime = stateTime;
				timeBetweenBlinks = MathUtils.random(blinkTimeRange)+0.2f;
				blinking = false;
				firstPass = true;
				hasBlinked = false;
			}
			
		}
		
		if (blinking) {
				frameNumber = getKeyFrameIndex(stateTime);
				if (frameNumber == 0) {
					if (firstPass) {
						firstPass=false;
					} else hasBlinked = true;
				}
				

				return keyFrames[frameNumber];
		
		} else {
				return keyFrames[0];
		}		
		
	}


	
	
	
	
}
