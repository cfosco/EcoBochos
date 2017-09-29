package com.foscusgames.ecoquis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.foscusgames.ecoquishandlers.EQQuestionHandler;
import com.foscusgames.ecoquisscreens.LoadingScreen;
import com.foscusgames.interfaces.ActionResolver;

public class EQGame extends Game {
	//AssetManager assetMgr;
	EQQuestionHandler questionHandler;
	EQMatch match;
	public ActionResolver actionResolver;
	
	public EQGame(ActionResolver actionResolver) {
		this.actionResolver = actionResolver;
	}
	
	@Override
	public void create () {
		
		//assetMgr = new AssetManager();
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
