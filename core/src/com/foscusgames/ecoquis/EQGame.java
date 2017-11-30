package com.foscusgames.ecoquis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.foscusgames.ecoquishandlers.EQQuestionHandler;
import com.foscusgames.ecoquisscreens.LoadingScreen;
import com.foscusgames.interfaces.ActionResolver;

/**
 * Main class for the game loop, Instantiated when app is booting. 
 * 
 * @author Camilo
 *
 */
public class EQGame extends Game {
	EQQuestionHandler questionHandler;
	EQMatch match;
	public ActionResolver actionResolver;
	
	public EQGame(ActionResolver actionResolver) {
		this.actionResolver = actionResolver;
	}
	
	@Override
	public void create () {
		
		EQAssets.generateAssetManager();
		questionHandler = new EQQuestionHandler();
		
		EQGlobals.center.x = Gdx.graphics.getWidth()/2;
		EQGlobals.center.y = Gdx.graphics.getHeight()/2;
		
		EQGlobals.w = Gdx.graphics.getWidth();
		EQGlobals.h = Gdx.graphics.getHeight();
		
		setScreen( new LoadingScreen(this));
		
		
	}
	


	@Override
	public void dispose () {
		super.dispose();
		getScreen().dispose();
		EQAssets.dispose();
	}

//	public AssetManager getAssetMgr() {
//		return assetMgr;
//	}
	
	public EQQuestionHandler getQuestionHandler() {
		return questionHandler;
	}

	public void newNormalMatch() {
					
		match = new EQNormalMatch(questionHandler, this);

	}
	
	public void newTimeMatch() {
		
		match = new EQTimeMatch(questionHandler, this);

	}
}
