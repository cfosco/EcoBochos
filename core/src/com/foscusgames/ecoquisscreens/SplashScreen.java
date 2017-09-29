package com.foscusgames.ecoquisscreens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGame;

public class SplashScreen extends EQScreen {

	Image renaultLogo;
	Image casatierraLogo;
	Image neurarLogo;
	Image bg;
	private Group smallLogoGroup;
	private EQGame game;
	
	public SplashScreen(EQGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		
		renaultLogo = new Image(EQAssets.renaultLogo);
		casatierraLogo = new Image(EQAssets.casatierraLogo);
		neurarLogo = new Image (EQAssets.neurarLogo);
		bg = new Image(EQAssets.whiteOverlay);
		
		smallLogoGroup = new Group();
		smallLogoGroup.addActor(casatierraLogo);
		smallLogoGroup.addActor(neurarLogo);
		renaultLogo.setColor(1f,1f,1f,0f);
//		casatierraLogo.setColor(1f,1f,1f,0f);
//		foscusLogo.setColor(1f,1f,1f,0f);
		smallLogoGroup.setColor(1f,1f,1f,0f);
		
		float fadeTime = 1f;
		float stayTime = 0.5f;
		
		renaultLogo.addAction(Actions.sequence(Actions.alpha(1, fadeTime),
												Actions.delay(2*stayTime),
												Actions.alpha(0,fadeTime)));
		
		
		
		smallLogoGroup.addAction(Actions.sequence(Actions.delay(stayTime),
								Actions.alpha(1,fadeTime),
								Actions.delay(stayTime),
								Actions.alpha(0,fadeTime),
								Actions.run(new Runnable() {

									@Override
									public void run() {
										game.setScreen(new MainMenuScreen(game));
									}
										
									
								})));
		
//		casatierraLogo.addAction(Actions.alpha(1, 1f));
//		foscusLogo.addAction(Actions.alpha(1, 1f));
		
		stage.addActor(bg);
		stage.addActor(renaultLogo);
		stage.addActor(smallLogoGroup);
		
		
	}
	
	@Override
	public void resize(int width, int height) {
        super.resize(width, height);
        
        float grlW = width*0.8f;
        float grlH = grlW*renaultLogo.getHeight()/renaultLogo.getWidth();
        float grlY =  0.6f*height-grlH/2;
        
        float casaW = width*0.3f;
        float casaH = casaW*casatierraLogo.getHeight()/casatierraLogo.getWidth();
        float casaY = height/4-casaH/2f;
        
        float foscoW = width*0.26f;
        float foscoH = foscoW*neurarLogo.getHeight()/neurarLogo.getWidth();
        float foscoY = height/4-foscoH/2f;
        
        float margin = width *0.025f;
        
        renaultLogo.setBounds(width/2-grlW/2, grlY, grlW, grlH);
        casatierraLogo.setBounds(width/3f-casaW/2f-margin, casaY, casaW, casaH);
		neurarLogo.setBounds(2*width/3f-foscoW/2f+margin, foscoY, foscoW, foscoH);
		bg.setBounds(0, 0, width, height);
	}
	
}
