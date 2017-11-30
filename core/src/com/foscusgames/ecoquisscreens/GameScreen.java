package com.foscusgames.ecoquisscreens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQGlobals.Category;
import com.foscusgames.ecoquis.EQMatch;
import com.foscusgames.ecoquis.EQMatch.MatchState;
import com.foscusgames.ecoquis.EQNormalMatch;
import com.foscusgames.ecoquis.EQSounds;
import com.foscusgames.ecoquis.EQTimeMatch;
import com.foscusgames.ecoquisactors.EQEarth;
import com.foscusgames.ecoquisactors.EQExtraPointIndicator;
import com.foscusgames.ecoquisactors.EQFacesHolder;
import com.foscusgames.ecoquisoverlays.EQCategoryOverlay;
import com.foscusgames.ecoquisoverlays.EQEndgameOverlay;

/**
 * Screen seen during the main loop of a match. Contains the earth, ready to spin, the faces and the score.
 * @author Camilo
 *
 */
public class GameScreen extends EQScreen {

	private EQMatch match;
	private EQEarth earth;
	private Image gameScreenBg;
	private Label tutorialLabel;
	private EQFacesHolder facesHolder;
	private EQEndgameOverlay endgameOverlay;
	private EQExtraPointIndicator extraPointIndicator;
	private EQCategoryOverlay categoryOverlay;
	private boolean endgameOverlayOn = false;
	private ImageButton backToMenuButton;

	public void init(EQMatch eqMatch) {
		this.match = eqMatch;
		
		if (!EQSounds.mainMenuTheme.isPlaying()) {
			EQSounds.playMusic(EQSounds.mainMenuTheme);
		}
		EQSounds.mainMenuTheme.setVolume(0.35f);
		
		
		gameScreenBg = new Image(EQAssets.mainMenuBg);
		
		Skin skin = new Skin();
		skin.addRegions(EQAssets.buttonAtlas);
		backToMenuButton = new ImageButton(skin.getDrawable("boton home"),skin.getDrawable("boton home presionado"));
		backToMenuButton.setBounds(EQGlobals.h*0.03f, EQGlobals.h*0.03f, EQGlobals.w*0.11f, EQGlobals.w*0.11f);
		backToMenuButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				EQSounds.playSound(EQSounds.buttonClick);
				if (EQSounds.earthTheme.isPlaying()) EQSounds.earthTheme.stop();
				match.backToMainMenu();
			}
			
		});
		
		LabelStyle ls = new LabelStyle(EQAssets.timerFont, Color.WHITE);
		tutorialLabel = new Label("¡Tocá la tierra para jugar!", ls);
		tutorialLabel.setAlignment(Align.center);
		
		stage.addActor(gameScreenBg);
		stage.addActor(match.getScoreTimer());
		stage.addActor(extraPointIndicator);
		stage.addActor(facesHolder);
		stage.addActor(earth);
		stage.addActor(tutorialLabel);
		stage.addActor(backToMenuButton);
		
		//DEBUG
		//stage.addActor(endgameOverlay);
	}
	
	public GameScreen (EQNormalMatch eqMatch)  {
		
		super();

		endgameOverlay = new EQEndgameOverlay(eqMatch);
		facesHolder = new EQFacesHolder(eqMatch,0,EQGlobals.h*0.75f,EQGlobals.w,EQGlobals.h*0.2f);
		extraPointIndicator = new EQExtraPointIndicator(eqMatch,EQGlobals.w/2, EQGlobals.h*0.72f, EQGlobals.w,EQGlobals.h*0.04f);//EQGlobals.w*0.92f,EQGlobals.h*0.07f);
		earth = new EQEarth(eqMatch, this, EQGlobals.w/2, EQGlobals.h*0.23f,EQGlobals.w*0.51f,EQGlobals.w*0.51f);
		init(eqMatch);
		

	}
	
	public GameScreen (EQTimeMatch eqMatch)  {
		
		super();

		endgameOverlay = new EQEndgameOverlay(eqMatch);
		facesHolder = new EQFacesHolder(eqMatch,0,EQGlobals.h*0.68f,EQGlobals.w,EQGlobals.h*0.2f);
		extraPointIndicator = new EQExtraPointIndicator(eqMatch,EQGlobals.w/2, EQGlobals.h*0.65f, EQGlobals.w,EQGlobals.h*0.04f);//EQGlobals.w*0.92f,EQGlobals.h*0.07f);
		earth = new EQEarth(eqMatch, this, EQGlobals.w/2, EQGlobals.h*0.2f,EQGlobals.w*0.5f,EQGlobals.w*0.5f);
		init(eqMatch);

		
	}
	
	@Override
	public void show() {
		super.show();

		stage.addActor(match.getScoreTimer());
		
	}


	@Override
	public void resize(int width, int height) {

		
		super.resize(width, height);
		
		facesHolder.onResize(width,height);
		
		extraPointIndicator.onResize(width, height);
		
		gameScreenBg.setBounds(0, 0, width, height);
		
		earth.onResize(width,height);
		
		match.getScoreTimer().onResize(width,height);
		
		backToMenuButton.setPosition(height*0.03f, height*0.03f);
		
		tutorialLabel.setBounds( 0,0,width, height*0.1f );


	}
	
	public void setCategoryOverlay(Category cat, boolean hard) {
		
		
		
		if (match.getState()==MatchState.ENDGAME) {
			
			EQSounds.playEarthFinalMusic();
		} else {

			EQSounds.playCategSound(cat);
		}
		categoryOverlay = new EQCategoryOverlay(match, cat, hard);
		stage.addActor(categoryOverlay);
		
	}


	public void updateFaceHolder(int[] points) {
		
		facesHolder.update(points);

	}
	
	public void updateEPBActions() {
		
		facesHolder.updateEPBActions(getEPI().isEPBAvailable());

	}


	public void onAllFacesCompletion() {

		earth.addPulsateAction();
		
	}
	
	public void addEarthPulsateAction() {
		
		earth.addPulsateAction();

	}

	public void onMatchFinished() {

		earth.addWinAction();
		match.getScoreTimer().addAction(Actions.removeActor());
		match.submitFinalScore();
		match.updateScoreAchievements();
		setEndgameOverlay();
		
	}
	
	public void setEndgameOverlay() {
		endgameOverlay.updateEndgameOverlayText();
		stage.addActor(endgameOverlay);
		endgameOverlayOn = true;
	}

	public EQExtraPointIndicator getEPI() {
		return extraPointIndicator;
	}
	
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
	   super.render(delta);
	   
	   if(match.getState()==MatchState.TIMEOUT && !endgameOverlayOn) {

			EQSounds.playSound(EQSounds.timeout);
		   onMatchFinished();
		   //setEndgameOverlay();
	   }
	}

	public void removeEarthPulsateAction() {
		earth.removePulsateAction();
		
	}

	public boolean isEarthPulsating() {
		return earth.isPulsating();
	}

	
	public void removeTutorialLabel() {
		tutorialLabel.remove();
	}


}
