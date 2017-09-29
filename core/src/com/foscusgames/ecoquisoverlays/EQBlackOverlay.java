package com.foscusgames.ecoquisoverlays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.foscusgames.ecoquis.EQAssets;

public class EQBlackOverlay extends WidgetGroup{

	private Image blackOverlay;
	
	public EQBlackOverlay() {

		blackOverlay = new Image(EQAssets.blackOverlay);
		blackOverlay.setColor(new Color(1f,1f,1f,0.75f));
		
		this.addActor(blackOverlay);
		blackOverlay.setFillParent(true);
		this.setFillParent(true);
		
		
	}
	
	protected void removeOverlay() {
		this.addAction(Actions.removeActor());
		
	}
	
//	public void onResize(int width, int height) {
//
//		blackOverlay.setBounds(0, 0, width, height);
//		this.setBounds(0, 0, width, height);
//		
//	}

}
