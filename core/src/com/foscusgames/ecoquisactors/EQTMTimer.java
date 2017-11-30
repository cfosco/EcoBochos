package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQMatch.MatchState;
import com.foscusgames.ecoquis.EQSounds;
import com.foscusgames.ecoquis.EQTimeMatch;

/**
 * Class that keeps track of time for the Time gamemode. Can be rendered on screen as a sand clock.
 * @author Camilo
 *
 */
public class EQTMTimer extends EQScoreTimer {

	protected EQTimeMatch match;
	protected float remainingTime, lastTime;
	protected Image bg;
	protected Label scoreText;
	//protected Label timeText;
	protected Label extraTimeText;
	private int extraTimeObtained;
	private boolean timeRunningOutSoundPlayed;
	private Group sandClockGroup;
	private Image sandClock;
	private Image sand;
	private float sandClockHeight;
	private float sandClockWidth;
	private float sandWidth;
	private float sandHeight;
	private Label timeIndicator;
	private Group timeIndicatorHolder;
	private Label timeAddedIndicator;

	private void init(BitmapFont font, EQTimeMatch match) {
		this.match = match;
		scoreText = new Label("", new LabelStyle(font, new Color(1f,1f,1f,1f)));
		extraTimeText = new Label("", new LabelStyle(font, new Color(1f,1f,1f,1f)));

		sandClock = new Image(EQAssets.miscAtlas.findRegion("reloj arena"));
		width=EQGlobals.w;
		height=EQGlobals.h*0.06f;		
		sandClockHeight = EQGlobals.h*0.15f;
		sandClockWidth = sandClockHeight*sandClock.getWidth()/sandClock.getHeight();
		sandWidth = sandClockWidth*0.72f;
		sandHeight = sandClockHeight*0.75f;
		float sandMargin = sandClockHeight*0.12f;
		
		sandClockGroup = new Group();
		sand = new Image(EQAssets.whiteOverlay);
		LabelStyle ls = new LabelStyle(EQAssets.numberFont, new Color(1,1,1,1));
		timeIndicator = new Label("",ls);
		timeIndicator.setAlignment(Align.center);
		timeIndicatorHolder = new Group();
		timeIndicatorHolder.addActor(timeIndicator);
		timeAddedIndicator = new Label("",ls);
		timeAddedIndicator.setAlignment(Align.center);
		resetTimeAddedIndicator();
		
		// Defining the visual sandclock
		sandClockGroup.setBounds(this.width/2f-sandClockWidth/2, -sandClockHeight+this.height, sandClockWidth, sandClockHeight);
		sandClockGroup.setOrigin(sandClockWidth/2,sandClockHeight);
		sandClockGroup.addActor(sand);
		sandClockGroup.addActor(sandClock);
		sandClockGroup.addActor(timeIndicatorHolder);
		
		sandClock.setBounds(0,0, sandClockWidth, sandClockHeight);
		//sandClock.setColor(1,1,1,0.5f);
		sand.setColor(255/255f,194/255f,53/255f,1);
		sand.setBounds(sandClockGroup.getWidth()/2f-sandWidth/2, sandMargin, sandWidth, sandHeight);
		timeIndicator.setBounds(0, sandClockHeight*0.05f, sandClockWidth, sandClockHeight);
		timeIndicatorHolder.setOrigin(sandClockWidth/2,sandClockHeight/2);
		
		bg.setFillParent(true);
		scoreText.setAlignment(Align.center);
		extraTimeText.setAlignment(Align.center);
		
		
		
		this.addActor(bg);
		this.addActor(scoreText);
		this.addActor(extraTimeText);
		this.addActor(sandClockGroup);
		
		remainingTime = EQGlobals.timeModeStartingTime;
		lastTime = remainingTime;
		
		timeRunningOutSoundPlayed=false;
	}
	
	public EQTMTimer(BitmapFont font, AtlasRegion background, EQTimeMatch match) {
		super();
		bg = new Image(background);
		bg.setColor(1f,1f,1f,0.6f);
		init(font, match);

	}
	
	public EQTMTimer(BitmapFont font, Texture tex, EQTimeMatch match) {
		super();
		bg = new Image(tex);
		bg.setColor(1f,1f,1f,0.6f);
		init(font, match);
	}

	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		if (match.getState()==MatchState.QUESTION) {
			sandClockGroup.setScale(0.5f);
		} else {
			sandClockGroup.setScale(1f);
		}
		
		
		remainingTime -= delta;
		
		sand.setHeight(sandHeight*remainingTime/EQGlobals.timeModeStartingTime);
		
		if (remainingTime<0) {
			match.onTimeUp();
			remainingTime=0;
		}
		
		if (remainingTime <10) {
			
			if(!timeRunningOutSoundPlayed) {
				timeRunningOutSoundPlayed = true;
				EQSounds.playSound(EQSounds.timeRunningOut);
			}

			timeIndicator.setText(""+MathUtils.ceil(remainingTime));
			
			if (lastTime - remainingTime >1) {
				//EQSounds.timeRunningOut.play();
				lastTime = remainingTime;
				float s = 0.3f;
				float t =0.1f;
				timeIndicatorHolder.addAction(Actions.sequence(Actions.scaleBy(s, s,t),Actions.scaleBy(-s, -s,t)));
			}
		} else {
			timeIndicator.setText("");
		}
		
		if (timeRunningOutSoundPlayed && remainingTime >=10 ) {
			EQSounds.timeRunningOut.pause();
			timeRunningOutSoundPlayed = false;
		}
		
		extraTimeText.setText("Tiempo Ganado:\n"+extraTimeObtained+" segundos");
		scoreText.setText("Puntaje:\n"+score);
		
	}
	
	public int getRoundedRemainingTime() {
		return (int)Math.ceil(remainingTime);
	}
	
	@Override
	public void addTime(int t) {
		remainingTime+=t;
		extraTimeObtained +=t;
		prepareTimeAddedIndicator(t);
	}
	
	public void prepareTimeAddedIndicator(int time) {
		
		float t =1.5f;
		sandClockGroup.addActor(timeAddedIndicator);
		timeAddedIndicator.setText("+"+time);
		timeAddedIndicator.clearActions();
		timeAddedIndicator.addAction(Actions.moveBy(0, sandClockHeight,t));
		timeAddedIndicator.addAction(Actions.sequence(Actions.alpha(0,t),Actions.run(new Runnable() {
			
			@Override
			public void run() {
				resetTimeAddedIndicator();
				
			}

			
		})));
		
		
	}
	

	private void resetTimeAddedIndicator() {
		float labelInitX = 0;
		float labelInitY = 0;
		timeAddedIndicator.setBounds(labelInitX,labelInitY,sandClockWidth,sandClockHeight);
		timeAddedIndicator.setColor(new Color(1,1,1,1));
		sandClockGroup.removeActor(timeAddedIndicator);
		
	}
	
	public void onResize(int width, int height) {

		this.setBounds(0, height-this.height, width, this.height);
		scoreText.setBounds(0, 0, width/4, this.height);
		extraTimeText.setBounds(3*width/4, 0, width/4, this.height);
		sandClockGroup.setPosition(this.getWidth()/2f-sandClockWidth/2f, -sandClockHeight+this.getHeight());
	
	
	}
	

}
