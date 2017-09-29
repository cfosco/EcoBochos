package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQGlobals;

public class EQNMTimer extends EQScoreTimer {
	
	protected Image bg;
	protected Label scoreText;
	protected Label timeText;
	
	private void init(BitmapFont font) {

		scoreText = new Label("Puntaje: "+score, new LabelStyle(font, new Color(1f,1f,1f,1f)));
		timeText = new Label("Tiempo: "+elapsedTime, new LabelStyle(font, new Color(1f,1f,1f,1f)));
		timeText = new Label("Tiempo: "+elapsedTime, new LabelStyle(font, new Color(1f,1f,1f,1f)));
		
		width=EQGlobals.center.x*2;
		height=EQGlobals.h*0.06f;
		
		bg.setFillParent(true);
		scoreText.setAlignment(Align.center);
		timeText.setAlignment(Align.center);
		this.addActor(bg);
		this.addActor(scoreText);
		this.addActor(timeText);
	}
	
	public EQNMTimer(BitmapFont font,AtlasRegion background) {
		
		super();
		bg = new Image(background);
		init(font);
	}
	
	public EQNMTimer(BitmapFont font, Texture background) {
		super();
		bg = new Image(background);
		bg.setColor(1f,1f,1f,0.6f);
		//bg.setColor(0.1f,0.6f,0.9f,0.5f);
		init(font);
	}

	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		timeText.setText(String.format("Tiempo: %d", getRoundedTime()));
		scoreText.setText("Puntaje: "+score);
		
		
	}


	public void onResize(int width, int height) {

		this.setBounds(0, height-this.height, width, this.height);
		scoreText.setBounds(0, 0, width/2, this.height);
		timeText.setBounds(width/2, 0, width/2, this.height);
		
	}

	@Override
	public void addTime(int t) {
		elapsedTime+=t;
		
	}
}
