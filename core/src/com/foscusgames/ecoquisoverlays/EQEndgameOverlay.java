package com.foscusgames.ecoquisoverlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQMatch;
import com.foscusgames.ecoquis.EQNormalMatch;
import com.foscusgames.ecoquis.EQSounds;
import com.foscusgames.ecoquis.EQTimeMatch;
import com.foscusgames.ecoquisactors.ParticleExplosion;

/**
 * Simple semi transparent Black Overlay class that is used as a dimmed background when transitioning 
 * from the spinning state to the ENDGAME state.
 * @author Camilo
 *
 */
public class EQEndgameOverlay extends EQBlackOverlay {


	private Image splashImage, correctIcon, incorrectIcon;
	private Label scoreLabel;
	private ImageButton backToMainMenuButton;
	private ImageButton replayButton;
	private Table table;
	private EQMatch match;
	private Label incorrectAnswersLabel;
	private Label correctAnswersLabel;
	private Label scoreTextLabel;
	private ParticleExplosion pE;
	
	
	public void init() {
		
		//EQAssets.numberFont.getData().scale(1f);

		LabelStyle ls =  new LabelStyle(EQAssets.mainMenuFont, new Color(1f,1f,1f,1f));
		LabelStyle numberLs =  new LabelStyle(EQAssets.numberFont, new Color(1f,1f,1f,1f));
		LabelStyle scoreLs =  new LabelStyle(EQAssets.scoreFont, new Color(1f,1f,1f,1f));

		scoreLabel = new Label(""+match.getScore(), scoreLs);
		scoreLabel.setAlignment(Align.top);
		scoreTextLabel = new Label("Puntaje", ls);
		scoreTextLabel.setAlignment(Align.center);
		
		correctIcon = new Image(EQAssets.iconsAtlas.findRegion("CorrectMark"));
		incorrectIcon = new Image(EQAssets.iconsAtlas.findRegion("IncorrectMark"));
		
		float iconWidth = EQGlobals.w*0.15f;


		table = new Table();
		

		
		correctAnswersLabel = new Label(""+match.getRightAnswers(), numberLs);
		correctAnswersLabel .setAlignment(Align.center);
		incorrectAnswersLabel  = new Label(""+match.getWrongAnswers(), numberLs);
		incorrectAnswersLabel .setAlignment(Align.center);
		

		setEndgameButtons();
		

		this.addActor(pE);
		pE.setPosition(EQGlobals.w/2, EQGlobals.h*0.75f);
		pE.explode();

		
		
		//table.debug();
		
		table.setFillParent(true);
		table.align(Align.bottom);
		this.addActor(table);
		
		float splashImageWidth = EQGlobals.w*0.92f;
		float splashImageHeight = splashImageWidth*splashImage.getHeight()/splashImage.getWidth();
		float margin = EQGlobals.w*0.06f;
		
		table.add(splashImage).size(splashImageWidth,splashImageHeight).padBottom(EQGlobals.h*0.06f).colspan(2);
		table.row();
		table.add(scoreTextLabel).size(EQGlobals.w*0.7f,EQGlobals.h*0.045f).colspan(2);
		table.row();
		table.add(scoreLabel).size(EQGlobals.w*0.7f,EQGlobals.h*0.16f).colspan(2).padBottom(EQGlobals.h*0.075f);
		table.row();
		table.add(correctIcon).size(iconWidth, iconWidth*correctIcon.getPrefHeight()/correctIcon.getPrefWidth()).align(Align.right).padRight(margin);
		table.add(incorrectIcon).size(iconWidth, iconWidth*incorrectIcon.getPrefHeight()/incorrectIcon.getPrefWidth()).align(Align.left).padLeft(margin);
		table.row();
		table.add(correctAnswersLabel).size(iconWidth,EQGlobals.h*0.065f).align(Align.right).padRight(margin);
		table.add(incorrectAnswersLabel).size(iconWidth,EQGlobals.h*0.065f).align(Align.left).padLeft(margin);
		table.row();
		table.add(backToMainMenuButton).size(EQGlobals.w*0.4f,EQGlobals.h*0.1f).pad(margin*1.5f).padRight(margin).padTop(margin*2);
		table.add(replayButton).size(EQGlobals.w*0.4f,EQGlobals.h*0.1f).pad(margin*1.5f).padLeft(margin).padTop(margin*2);
		
		
	}
	
	public EQEndgameOverlay(EQNormalMatch match) {
		
		this.match = match;
		splashImage = new Image(EQAssets.ganasteSplash);
		pE = new ParticleExplosion(EQAssets.miscAtlas.findRegion("Star"), 0.15f);
		init();
		
	}

	public EQEndgameOverlay(EQTimeMatch match) {
		
		this.match = match;
		splashImage = new Image(EQAssets.tiempoSplash);
		pE = new ParticleExplosion(EQAssets.iconsAtlas.findRegion("sandclockIcon"), 0.27f);
		init();
		
	}

	private void setEndgameButtons() {

		Skin skin = new Skin();
		skin.addRegions(EQAssets.buttonAtlas);
//		ImageButtonStyle bs = new ImageButtonStyle();
//		bs.up = skin.getDrawable("Home");
//		bs.down =  skin.getDrawable("Home");
//		bs.checked =  skin.getDrawable("Home");
		backToMainMenuButton = new ImageButton(skin.getDrawable("boton home"),skin.getDrawable("boton home presionado"));
		replayButton = new ImageButton(skin.getDrawable("boton replay"),skin.getDrawable("boton replay presionado"));
		
		backToMainMenuButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				Gdx.app.log("EQOverlayHandler", "back to main menu clicked");
				EQSounds.playSound(EQSounds.buttonClick);
				if (EQSounds.earthTheme.isPlaying()) EQSounds.earthTheme.stop();
				match.backToMainMenu();
				
				
			}
			
		});
		
		replayButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				Gdx.app.log("EQOverlayHandler", "New game clicked");
				EQSounds.playSound(EQSounds.buttonClick);
				if (EQSounds.earthTheme.isPlaying()) EQSounds.earthTheme.stop();
				match.newMatch();
				
				
			}
			
		});
		
	}



	public void updateEndgameOverlayText() {
		scoreLabel.setText(""+match.getScore());
		//timeLabel.setText("Tiempo: "+match.getTime());
		correctAnswersLabel.setText(""+match.getRightAnswers());
		incorrectAnswersLabel.setText(""+match.getWrongAnswers());
		
	}
	
		
}
