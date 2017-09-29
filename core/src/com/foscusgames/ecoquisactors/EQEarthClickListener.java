package com.foscusgames.ecoquisactors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQMatch;
import com.foscusgames.ecoquis.EQMatch.MatchState;
import com.foscusgames.ecoquis.EQNormalMatch;
import com.foscusgames.ecoquis.EQSounds;
import com.foscusgames.ecoquis.EQTimeMatch;

public class EQEarthClickListener extends ClickListener {

	private EQMatch match;
	private EQEarth earth;
	private int currentUpFaceNum=0;
	private float rotTime;
	private Sound spinSound;

	public EQEarthClickListener(EQNormalMatch match, EQEarth earth) {
		this.match = match;
		this.earth = earth;
		rotTime = 4.5f;
		spinSound = EQSounds.earthSpinNormal;
		
		if (EQGlobals.DEBUG) {
			rotTime = 1;
		}
	}
	
	public EQEarthClickListener(EQTimeMatch match, EQEarth earth) {
		this.match = match;
		this.earth = earth;
		rotTime = 2;
		spinSound = EQSounds.earthSpinTime;
		
		if (EQGlobals.DEBUG) {
			rotTime = 1;
		}
	}


	
	public void clicked(InputEvent event, float x, float y) {

		if(!(match.getState() == MatchState.WIN)) {
		
		if (!(match.getState() == MatchState.ENDGAME) && !earth.isPulsating()) {
					
		if (!(match.getState()==MatchState.SPINNING)) {
			
			match.removeTutorialLabel();
			
			EQSounds.playSound(spinSound);
			
			int newCategory = match.getNextRandomCategory();
			int rot = -360*5 - 72*(currentUpFaceNum-newCategory);
			currentUpFaceNum = newCategory;
			
			match.setCurrentCategory(newCategory);
			match.setState(MatchState.SPINNING);

	        System.out.println("Category:"+newCategory);
			
			earth.getRotatingFaces().addAction(sequence(rotateBy(rot,rotTime,Interpolation.exp5Out), run(new Runnable() {
			    public void run () {

			    	EQSounds.playSound(EQSounds.categSelected);
			    	Array<Actor> faces =earth.getRotatingFaces().getChildren();
			    	float s=0.3f;
			    	float t=0.2f;
			    	faces.get(match.getCurrentCategory()).addAction(sequence(scaleBy(s,s, t, Interpolation.sineIn),scaleBy(-s,-s,t,Interpolation.sineOut), 
			    			Actions.run( new Runnable() {
			    				public void run() {
			    					match.onEarthSpinFinished();
			    				}
			    			})));
			    	
			    }
			})));
			
			
		}
			
		} else {
			Gdx.app.log("EQEarthClickListener","Pulsating Earth Clicked");
			match.onPulsatingEarthClick();
			
		}
		
		}
	}
	
	
	public boolean esPrimo(int numero) {
		
		// "numero" debe ser NATURAL IMPAR
		
		if (numero <= 3) return true; //esto en tu caso lo podes sacar para hacer la funcion todavia mas rapida
		
		for (int i =3; i < numero/3; i+=2) { 
			//el +=2 es para no chequear los pares, porq un impar no puede ser divisible por un numero par
			//el /3 es para no chequear los numeros que darian un resultado inferior a 3 (imposible)
			
			if (numero % i == 0) return false;
			
		}
		
		return true;	
		
	}
	
	
	

}
