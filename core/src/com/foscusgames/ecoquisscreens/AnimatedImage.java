package com.foscusgames.ecoquisscreens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.foscusgames.ecoquisactors.BlinkAnimation;

public class AnimatedImage extends Image {

	protected BlinkAnimation animation;
	private float stateTime = 0;
	private boolean pauseAnimation=false;
	    

	public AnimatedImage (BlinkAnimation animation) {
		
		//TO DO: REFACTORIZAR PARA PODER USAR AnimatedImage sin BlinkAnimation
		
	        super(animation.getKeyFrame(0));
	        this.animation = animation;
	        
	    }	
	
	
    @Override
    public void act(float delta)
    {
    	if (!pauseAnimation) {
    		this.setDrawable(animation.getDrawableKeyFrame(stateTime+=delta));
    	} 
        super.act(delta);
    }
    
    
    public void pauseAnimation() {
    	pauseAnimation = true;
    }
    
    public void unpauseAnimation() {
    	pauseAnimation = false;
    }


	public boolean isAnimationFinished(float stateTime) {
		return animation.isAnimationFinished(stateTime);
	}

	
}
