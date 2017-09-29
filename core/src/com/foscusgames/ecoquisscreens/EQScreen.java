package com.foscusgames.ecoquisscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.foscusgames.ecoquis.EQGlobals;

public class EQScreen implements Screen {
	
	protected Stage stage;
	protected OrthographicCamera cam;
	
	public EQScreen() {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {


		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);    
	    
	    stage.act();
        stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
        // Generate camera for the new screen size
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);	
		
	
		// Generate viewport for the new screen size
		stage.setViewport(new ScreenViewport(cam)); 
		stage.getViewport().update(width,height);
		
		EQGlobals.center.x = width/2;
		EQGlobals.center.y = height/2;
		
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

}
