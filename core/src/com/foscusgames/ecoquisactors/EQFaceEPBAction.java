package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class EQFaceEPBAction extends RepeatAction {


	float t = 0.5f;
	float s = 0.5f;
	
	public EQFaceEPBAction() {
		
		
		ScaleByAction scaleAction1 = new ScaleByAction();
		scaleAction1.setAmount(s);
		scaleAction1.setDuration(t);
		scaleAction1.setInterpolation(Interpolation.swingOut);
		ScaleByAction scaleAction2 = new ScaleByAction();
		scaleAction2.setAmount(-s);
		scaleAction2.setDuration(t);
		scaleAction2.setInterpolation(Interpolation.swingIn);
		
		SequenceAction sequenceAction = new SequenceAction(scaleAction1, scaleAction2);
		
		this.setAction(sequenceAction);
		this.setCount(RepeatAction.FOREVER);
		
	}

}
