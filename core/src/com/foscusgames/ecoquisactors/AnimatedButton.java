package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * General Button class with an animation, used to represent the underlying button on the Faces.
 * @author Camilo
 *
 */
public class AnimatedButton extends Button {

	protected BlinkAnimation animation;
	private float stateTime = 0;
	private boolean pauseAnimation=false;
	    

	public AnimatedButton (BlinkAnimation animation) {
	        super(new TextureRegionDrawable(animation.getKeyFrame(0)));
	        this.animation = animation;
	    }	
	
	
    @Override
    public void act(float delta)
    {
    	if (!pauseAnimation) {
    		getStyle().up = animation.getDrawableKeyFrame(stateTime+=delta);
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
