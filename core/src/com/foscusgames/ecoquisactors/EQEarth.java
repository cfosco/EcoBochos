package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQNormalMatch;
import com.foscusgames.ecoquis.EQTimeMatch;
import com.foscusgames.ecoquisscreens.GameScreen;

/**
 * Class representing the clickable earth of the gamemodes. Contains the rotating mini faces and knows how to position itself.
 * @author Camilo
 *
 */
public class EQEarth extends Group {
	

	private Image  miniMobilFace, miniEnergyFace, miniBiodivFace, miniConsumFace, miniWaterFace;
	private Group rotatingFaces;
	private AnimatedButton earth;
	private float timeSinceLastBlink=0;
	private float frameTime;
	private float earthWidth;
	private float earthHeight;
	private float x,y;
	private boolean pulsating = false;

	public EQEarth(EQNormalMatch match, GameScreen gscreen, float x, float y, float h, float w) {
		
		super();
		init(x,y,w,h);
		earth.addListener(new EQEarthClickListener(match,this));
		
		
	}
	
	public EQEarth(EQTimeMatch match, GameScreen gscreen, float x, float y, float h, float w) {
		
		super();
		init(x,y,w,h);
		earth.addListener(new EQEarthClickListener(match,this));
		
		
	}
	
	public void init(float x, float y, float h, float w) {
		frameTime = 0.05f;
		BlinkAnimation earthAnim = new BlinkAnimation(frameTime, 3, EQAssets.earthAtlas.findRegion("earthOpenSE"),
			  	  EQAssets.earthAtlas.findRegion("earthSemiClosed"),
			  	  EQAssets.earthAtlas.findRegion("earthClosed"));
		
		earth = new AnimatedButton(earthAnim);
		
		
		rotatingFaces = new Group();
		
		Skin miniFacesSkin = new Skin();
		miniFacesSkin.addRegions(EQAssets.miniFacesAtlas);
		
		miniMobilFace = new Image(miniFacesSkin.getDrawable("mobil"));
		miniEnergyFace = new Image(miniFacesSkin.getDrawable("energy"));
		miniBiodivFace = new Image(miniFacesSkin.getDrawable("biodiv"));
		miniConsumFace = new Image(miniFacesSkin.getDrawable("consum"));
		miniWaterFace = new Image(miniFacesSkin.getDrawable("water"));
		
		
		miniMobilFace = new Image(miniFacesSkin.getDrawable("mobil"));
		miniEnergyFace = new Image(miniFacesSkin.getDrawable("energy"));
		miniBiodivFace = new Image(miniFacesSkin.getDrawable("biodiv"));
		miniConsumFace = new Image(miniFacesSkin.getDrawable("consum"));
		miniWaterFace = new Image(miniFacesSkin.getDrawable("water"));
	
		
		rotatingFaces.addActor(miniMobilFace);
		rotatingFaces.addActor(miniEnergyFace);
		rotatingFaces.addActor(miniBiodivFace);
		rotatingFaces.addActor(miniConsumFace);
		rotatingFaces.addActor(miniWaterFace);
		
		
		this.addActor(rotatingFaces);
		this.addActor(earth);
		
		setPositionsAndSizes(x,y,w,h);
	}
	
    private void setPositionsAndSizes(float x,float y,float w,float h) {
    	
    	this.y = y;
    	this.x = x;
    	earthWidth = w;
		if (earthWidth > EQGlobals.h*0.6f) earthWidth = EQGlobals.h*0.6f;
		earthHeight = h;
		
		float scale = w/810f;
		float miniWidth = miniMobilFace.getPrefWidth() * scale;
		float miniHeight = miniMobilFace.getPrefHeight() * scale;
		
		float sep = earthHeight *0.1f;

		//earth.setBounds(width/2-earthWidth/2, height/2-earthHeight/2, earthWidth, earthHeight);
		earth.setBounds(0, 0, earthWidth, earthHeight);
		earth.setOrigin(Align.center);
		
		
		rotatingFaces.setOrigin(earthWidth/2, earthHeight/2);
		
		miniMobilFace.setBounds(earthWidth/2 -miniWidth/2, earthHeight+sep, miniWidth, miniHeight);
		miniMobilFace.setOrigin(miniWidth/2,-sep-earthHeight/2);
		
		miniEnergyFace.setBounds(earthWidth/2 -miniWidth/2, earthHeight+sep, miniWidth, miniHeight);
		miniEnergyFace.setOrigin(miniWidth/2,-sep-earthHeight/2);
		miniEnergyFace.setRotation(-360f/5);
		
		miniBiodivFace.setBounds(earthWidth/2 -miniWidth/2, earthHeight+sep, miniWidth, miniHeight);
		miniBiodivFace.setOrigin(miniWidth/2,-sep-earthHeight/2);
		miniBiodivFace.setRotation(-2*360f/5);
		
		miniConsumFace.setBounds(earthWidth/2 -miniWidth/2, earthHeight+sep, miniWidth, miniHeight);
		miniConsumFace.setOrigin(miniWidth/2,-sep-earthHeight/2);
		miniConsumFace.setRotation(-3*360f/5);
		
		miniWaterFace.setBounds(earthWidth/2 -miniWidth/2, earthHeight+sep, miniWidth, miniHeight);
		miniWaterFace.setOrigin(miniWidth/2,-sep-earthHeight/2);
		miniWaterFace.setRotation(-4*360f/5);
		
		
	}


	public void onResize(int width, int height) {
		

		this.setPosition(EQGlobals.center.x-earthWidth/2,y*height/EQGlobals.h);
		
	}

	public Group getRotatingFaces() {
		return rotatingFaces;
	}

	public void addPulsateAction() {
		
		float s = 0.2f;
		float t = 0.5f;
		
		if (!pulsating ) {
			
			Gdx.app.log("EQEarth", "Adding pulsate Action to Earth");
			
			pulsating = true;
			earth.setTransform(true);
			earth.addAction(Actions.forever(Actions.sequence(Actions.scaleBy(s, s, t,Interpolation.sineIn),Actions.scaleBy(-s,-s,t,Interpolation.sineOut))));
		}
		
		
		//this.addAction(Actions.forever(Actions.sequence(Actions.moveBy(0, 50,0.5f,Interpolation.sineIn),Actions.moveBy(0, -50,0.5f,Interpolation.sineOut))));
		
	}

	public void addWinAction() {
		rotatingFaces.addAction(Actions.forever(Actions.rotateBy(-360f,3f)));
		
	}

	public void removePulsateAction() {
		Gdx.app.log("EQEarth", "Removing pulsate Action to Earth");
		earth.clearActions();
		earth.setScale(1, 1);
		pulsating = false;
	}

	public boolean isPulsating() {
		return pulsating ;
	}
	
	
	
}
