package com.foscusgames.ecoquisscreens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGame;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquishandlers.EQQuestionHandler;

public class LoadingScreen extends EQScreen {
	
	EQGame game;
	
	Image loadingBar, loadingBg;
	
	float progress = 0;
	float finalLoadingBarWidth;
	private EQQuestionHandler qH;
	
	
	
	public LoadingScreen(EQGame eqGame) {
		
		super();
		game = eqGame;
		qH = game.getQuestionHandler();
	
		
	}

	@Override
	public void show() {
		
		super.show();
		
        // Tell the manager to load assets for the loading screen
		EQAssets.prepareLoadingScreen();
        
        // TO DO: HACER QUE ESTA CARGA SEA ASINCRONICA!!
        try {
			qH.loadQuestions();
		} catch (Exception e) {
			Gdx.app.log("LoadingScreen","Wrong Answer on File!");
			e.printStackTrace();
		}
                

        loadingBar = new Image(EQAssets.whiteOverlay);
        loadingBar.setColor(247f/255,207f/255,21f/255,1f);
        
        loadingBg = new Image(EQAssets.loadingBg);
        
        // Add all the actors to the stage
        stage.addActor(loadingBg);
        stage.addActor(loadingBar);
        
        // Queue all game assets for loading
        EQAssets.loadAssets();
        
        
		
	}
	

	@Override
	public void render(float delta) {

		
		if(EQAssets.update()) {
			Gdx.app.log("LoadingScreen", "FINISHED LOADING, moving to main menu screen");
			EQAssets.generateVariables();
			if (EQGlobals.DEBUG) {
				game.setScreen(new MainMenuScreen(game));
				
			} else {
				game.setScreen(new SplashScreen(game));
			}
		}
		
		updateProgressBar();
		

		super.render(delta);
		
		
	}
	
	public void updateProgressBar() {
		
        // Interpolate the progress percentage to make it more smooth
        progress = Interpolation.linear.apply(progress, EQAssets.getLoadProgress(), 0.5f);
        		
        loadingBar.setWidth(finalLoadingBarWidth*progress);
        
		
	}

	@Override
	public void resize(int width, int height) {
		
		super.resize(width, height);
		
		Gdx.app.log("LoadingScreen", "resizing with inputs: width="+width+" height="+height);

		// Place LoadingBG
		loadingBg.setBounds(0, 0, width, height);
		
		// Define size of loadingBar and place in screen
		finalLoadingBarWidth = width*0.9f;
		loadingBar.setBounds(width *0.05f, height*0.37f, width*0.8f, height*0.01f);


		
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
		
	}

	@Override
	public void dispose() {
		//EQAssets.dispose();
		
	}
	


}
