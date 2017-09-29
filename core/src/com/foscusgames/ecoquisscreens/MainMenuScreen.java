package com.foscusgames.ecoquisscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGame;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQSounds;
import com.foscusgames.ecoquisactors.BlinkAnimation;

public class MainMenuScreen extends EQScreen {

    //Table table;
    EQGame game;
    Button normalModeButton;
	Button timeModeButton;
	Button leaderboardButton;
	Button volumeButton;
	Button aboutButton;
    
    Image mainMenuTitle;
    Image mainMenuBg;
    
	private Label versionLabel;
	private float buttonWidth;
	private float buttonHeight;
	private float buttonWidthSmall;
	private float buttonHeightSmall;
	//private Animation mobilFaceAnim,energyFaceAnim,waterFaceAnim,consumFaceAnim,biodivFaceAnim;
	private BlinkAnimation kid1Anim, kid2Anim, kid3Anim, kid4Anim;
	//private AnimatedImage mobilFace,energyFace,waterFace,consumFace,biodivFace;
	private AnimatedImage kid1,kid2,kid3,kid4;
	private Table table;
	private Label normalModeLabel;
	private Label timeModeLabel;
	private float tableH;
	private float tableY;
	private float titleWidth;
	private float titleHeight;
	private float titleCenterX;
	private int titleCenterY;
	private Group kidGroup;
	private float lastWidth = EQGlobals.w;
	private Button achievementsButton;
	private Table smallBTable;

    public MainMenuScreen(EQGame game) {
    	
    	super();
    	this.game = game;
    	
    }
    
	@Override
	public void show() {
		
		super.show();
		
		
		if (!EQSounds.mainMenuTheme.isPlaying()) {
			EQSounds.playMusic(EQSounds.mainMenuTheme);
		}
		EQSounds.mainMenuTheme.setVolume(0.7f);
		
		createMainMenuImages();
		createMainMenuButtons();
		addActions();
		
		mainMenuBg = new Image(EQAssets.mainMenuBg);
			
		LabelStyle ls =  new LabelStyle(EQAssets.mainMenuFont, new Color(0f,0.2f,0.5f,1f));
		versionLabel = new Label(EQGlobals.versionNum,ls);
		versionLabel.setBounds(0, EQGlobals.h*0.95f, EQGlobals.w, EQGlobals.h*0.05f);
		
		normalModeLabel = new Label("Desafio",ls);
		timeModeLabel = new Label("Tiempo",ls);
	
		float sideMargin = EQGlobals.w*0.05f;
		float smallMargin = EQGlobals.w*0.05f;
		
		table = new Table();
		smallBTable = new Table();
		tableH = EQGlobals.h*0.5f;
		tableY = EQGlobals.h*0.05f;
		
		//table.debug();
		
		table.setBounds(0, tableY, EQGlobals.w, tableH);
		table.add(normalModeButton).size(buttonWidth, buttonHeight).padRight(sideMargin);
		table.add(timeModeButton).size(buttonWidth, buttonHeight).padLeft(sideMargin);
		table.row();
		table.add(normalModeLabel).padRight(sideMargin);
		table.add(timeModeLabel).padLeft(sideMargin);
		table.row().padTop(EQGlobals.h*0.05f);
		table.add(smallBTable).colspan(2);
		
		smallBTable.add(aboutButton).size(buttonWidthSmall, buttonHeightSmall).space(smallMargin);
		smallBTable.add(leaderboardButton).size(buttonWidthSmall, buttonHeightSmall).space(smallMargin);
		smallBTable.add(achievementsButton).size(buttonWidthSmall, buttonHeightSmall).space(smallMargin);
		smallBTable.add(volumeButton).size(buttonWidthSmall, buttonHeightSmall).space(smallMargin);
		//smallBTable.debug();
		
		
		stage.addActor(mainMenuBg);
		stage.addActor(mainMenuTitle);
		stage.addActor(table);
		stage.addActor(versionLabel);
		

		stage.addActor(kidGroup);
		
		normalModeLabel.addAction(Actions.sequence(Actions.alpha(0f),Actions.delay(0.5f),Actions.alpha(1f,0.3f)));
		timeModeLabel.addAction(Actions.sequence(Actions.alpha(0f),Actions.delay(0.5f),Actions.alpha(1f,0.3f)));
	
		stage.addAction(Actions.sequence(Actions.alpha(0f),Actions.alpha(1f,0.3f)));

	}
	
	private void addActions() {
		
		//popAction = Actions.scaleTo(1f,1f,0.4f,Interpolation.swingOut);
		
		float t = 0.8f;
		
		normalModeButton.addAction(Actions.scaleTo(1f,1f,t,Interpolation.swingOut));
		timeModeButton.addAction(Actions.scaleTo(1f,1f,t,Interpolation.swingOut));
		volumeButton.addAction(Actions.scaleTo(1f,1f,t,Interpolation.swingOut));
		leaderboardButton.addAction(Actions.scaleTo(1f,1f,t,Interpolation.swingOut));
		achievementsButton.addAction(Actions.scaleTo(1f,1f,t,Interpolation.swingOut));
		aboutButton.addAction(Actions.scaleTo(1f,1f,t,Interpolation.swingOut));

		
		
	}


	private void createMainMenuButtons() {
		
		
		Skin bSkin = new Skin();
		bSkin.addRegions(EQAssets.buttonAtlas);
		
		buttonWidth = EQGlobals.w*0.3f;
		buttonHeight = buttonWidth;
		buttonWidthSmall = EQGlobals.w*0.15f;
		buttonHeightSmall = buttonWidthSmall;
		
		normalModeButton = new Button(bSkin.getDrawable("boton trofeos"),bSkin.getDrawable("boton trofeos presionado"));
		timeModeButton = new Button(bSkin.getDrawable("boton tiempo"),bSkin.getDrawable("boton tiempo presionado"));
		volumeButton  = new Button(bSkin.getDrawable("boton sonido"),bSkin.getDrawable("boton sonido presionado"),bSkin.getDrawable("boton sonido off"));
		leaderboardButton  = new Button(bSkin.getDrawable("boton leaderboard"),bSkin.getDrawable("boton leaderboard presionado"));
		achievementsButton  = new Button(bSkin.getDrawable("boton achievements"),bSkin.getDrawable("boton achievements presionado"));
		aboutButton  = new Button(bSkin.getDrawable("boton about"),bSkin.getDrawable("boton about presionado"));
	
		
		volumeButton.setChecked(!EQSounds.isSoundOn());
		
		normalModeButton.setTransform(true);
		timeModeButton.setTransform(true);
		volumeButton.setTransform(true);
		leaderboardButton.setTransform(true);
		achievementsButton.setTransform(true);
		aboutButton.setTransform(true);

		
		normalModeButton.setOrigin(buttonWidth/2,buttonHeight/2);
		timeModeButton.setOrigin(buttonWidth/2,buttonHeight/2);
		volumeButton.setOrigin(buttonWidthSmall/2,buttonHeightSmall/2);
		leaderboardButton.setOrigin(buttonWidthSmall/2,buttonHeightSmall/2);
		achievementsButton.setOrigin(buttonWidthSmall/2,buttonHeightSmall/2);
		aboutButton.setOrigin(buttonWidthSmall/2,buttonHeightSmall/2);

		
		normalModeButton.setScale(0);
		timeModeButton.setScale(0);
		volumeButton.setScale(0);
		leaderboardButton.setScale(0);
		achievementsButton.setScale(0);
		aboutButton.setScale(0);

		
		
		normalModeButton.addListener(new ClickListener() {
		
		public void clicked(InputEvent event, float x, float y) {
		
			EQSounds.playSound(EQSounds.normalModeSound);
			game.newNormalMatch();

		
		}
		
		});
		
		timeModeButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
			
				EQSounds.playSound(EQSounds.timeModeSound);

					game.newTimeMatch();

			
			}
			
			});
		
		volumeButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				//Gdx.app.log("ANDROID LAUNCHER", "Gdx Width:"+Gdx.graphics.getWidth() +" Current EQWidth = "+EQGlobals.w+" Initial Width = "+EQGlobals.initialWidth+ " quesFontScale="+EQGlobals.questionFontScale);

				EQSounds.toggleSoundOn();
				EQSounds.playSound(EQSounds.buttonClick);
			
			}
			
			});
		
		leaderboardButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				EQSounds.playSound(EQSounds.buttonClick);


				if(game.actionResolver.getSignedInGPGS()) {
					game.actionResolver.getAllLeaderboardsGPGS();
					
				} else {
					game.actionResolver.loginGPGS();
					
				}
				Gdx.app.log("MainMenuScreen", "LEADERBOARD button clicked");
				
			
			}
			
			});
		
		achievementsButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				EQSounds.playSound(EQSounds.buttonClick);


				if(game.actionResolver.getSignedInGPGS()) {
					game.actionResolver.getAchievementsGPGS();
					
				} else {
					game.actionResolver.loginGPGS();
					
				}
				Gdx.app.log("MainMenuScreen", "ACHIEVEMENTS button clicked");
				
			
			}
			
			});
		
		aboutButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				EQSounds.playSound(EQSounds.buttonClick);

				Gdx.app.log("MainMenuScreen", "ABOUT button clicked");
				game.setScreen(new AboutScreen(game));

			}
			
			});
		
		
		
		
	}

	private void createMainMenuImages() {
		
		titleWidth = EQGlobals.w*0.75f;
		titleHeight = titleWidth;
		titleCenterX = EQGlobals.w/2f;
		titleCenterY = 3*EQGlobals.h/4;
		
		float frameDur = 0.05f;
		
		//float faceH = 100;
		//float faceW = 200;

		mainMenuTitle = new Image (EQAssets.mainMenuIcon);
		mainMenuTitle.setBounds(titleCenterX*1.013f-titleWidth/2,titleCenterY-titleHeight/2, titleWidth, titleHeight);
		mainMenuTitle.addAction(Actions.forever(Actions.sequence(Actions.moveBy(0, titleHeight*0.03f,0.8f,Interpolation.sine),Actions.moveBy(0, -titleHeight*0.03f,0.8f,Interpolation.sine))));
		
//		mobilFaceAnim = new Animation(frameDur, EQAssets.facesAtlas.findRegion("mobil D"), 
//										  		EQAssets.facesAtlas.findRegion("mobil E"),
//										  		EQAssets.facesAtlas.findRegion("mobil F"));	
//		
//		energyFaceAnim = new Animation(frameDur, EQAssets.facesAtlas.findRegion("energy D"), 
//		  										EQAssets.facesAtlas.findRegion("energy E"),
//		  										EQAssets.facesAtlas.findRegion("energy F"));	
//		
//		waterFaceAnim = new Animation(frameDur, EQAssets.facesAtlas.findRegion("water D"), 
//		  										EQAssets.facesAtlas.findRegion("water E"),
//		  										EQAssets.facesAtlas.findRegion("water F"));	
//
//		consumFaceAnim = new Animation(frameDur, EQAssets.facesAtlas.findRegion("consum D"), 
//		  										EQAssets.facesAtlas.findRegion("consum E"),
//		  										EQAssets.facesAtlas.findRegion("consum F"));	
//
//		biodivFaceAnim = new Animation(frameDur, EQAssets.facesAtlas.findRegion("biodiv D"), 
//		  										EQAssets.facesAtlas.findRegion("biodiv E"),
//		  										EQAssets.facesAtlas.findRegion("biodiv F"));	
//		
//		mobilFaceAnim.setPlayMode(PlayMode.LOOP_PINGPONG);
//		energyFaceAnim.setPlayMode(PlayMode.LOOP_PINGPONG);
//		waterFaceAnim.setPlayMode(PlayMode.LOOP_PINGPONG);
//		consumFaceAnim.setPlayMode(PlayMode.LOOP_PINGPONG);
//		biodivFaceAnim.setPlayMode(PlayMode.LOOP_PINGPONG);
		
//		mobilFace = new AnimatedImage(mobilFaceAnim);
//		energyFace = new AnimatedImage(energyFaceAnim);
//		waterFace = new AnimatedImage(waterFaceAnim);
//		consumFace = new AnimatedImage(consumFaceAnim);
//		biodivFace = new AnimatedImage(biodivFaceAnim);
		
		
		//mobilFace.setBounds(titleCenterX-mobilFace.getPrefWidth()*scale*0.5f, titleCenterY-mobilFace.getPrefHeight()*scale*0.5f, mobilFace.getPrefWidth()*scale, mobilFace.getPrefHeight()*scale);
		//energyFace.setBounds(titleCenterX, titleCenterY, mobilFace.getPrefWidth()*scale, mobilFace.getPrefHeight()*scale);
		//waterFace.setBounds(titleCenterX, titleCenterY, mobilFace.getPrefWidth()*scale, mobilFace.getPrefHeight()*scale);
		//mobilFace.addAction(action);
		
		
		kid1Anim = new BlinkAnimation(frameDur, EQAssets.kid1Atlas.findRegion("personaje 1 A"),
											EQAssets.kid1Atlas.findRegion("personaje 1 B"),
											EQAssets.kid1Atlas.findRegion("personaje 1 C"));
		kid1 = new AnimatedImage(kid1Anim);
		
		kid2Anim = new BlinkAnimation(frameDur, EQAssets.kid2Atlas.findRegion("personaje 2 A"),
											EQAssets.kid2Atlas.findRegion("personaje 2 B"),
											EQAssets.kid2Atlas.findRegion("personaje 2 C"));
		kid2 = new AnimatedImage(kid2Anim);
		
		kid3Anim = new BlinkAnimation(frameDur, EQAssets.kid4Atlas.findRegion("personaje 4 A"),
											EQAssets.kid4Atlas.findRegion("personaje 4 B"),
											EQAssets.kid4Atlas.findRegion("personaje 4 C"));
		kid3 = new AnimatedImage(kid3Anim);
		
		kid4Anim = new BlinkAnimation(frameDur, EQAssets.kid3Atlas.findRegion("personaje 3 A"),
											EQAssets.kid3Atlas.findRegion("personaje 3 B"),
											EQAssets.kid3Atlas.findRegion("personaje 3 C"));
		kid4 = new AnimatedImage(kid4Anim);

		float kidScale = EQGlobals.w/2000f;
		float kidW = kid1.getWidth()*kidScale*1.6f;
		float kidH = kid1.getHeight()*kidScale*1.6f;
		float kidDeltaX = kidH*0.9f;
		float kidDeltaY = kidH*0.9f;
		float kidT = 0.3f;
		
		Interpolation kidInterpolationIn = Interpolation.swingOut;
		Interpolation kidInterpolationOut = Interpolation.swingIn;

		kid1.setTouchable(Touchable.disabled);
		kid1.setBounds(EQGlobals.w*1.15f, -kidH, kidW, kidH);
		kid1.setRotation(45);
		kid1.addAction(Actions.forever(Actions.sequence(Actions.delay(1f),
										Actions.moveBy(MathUtils.cosDeg(135)*kidH*0.9f, MathUtils.sinDeg(135)*kidH*0.9f, kidT,kidInterpolationIn),
										Actions.delay(2.5f),
										Actions.moveBy(-MathUtils.cosDeg(135)*kidH*0.9f, -MathUtils.sinDeg(135)*kidH*0.9f, kidT,kidInterpolationOut),
										Actions.delay(9f))));
		
		kid2.setTouchable(Touchable.disabled);
		kid2.setBounds(-kidH*1.05f, EQGlobals.h*0.7f, kidW, kidH);
		kid2.setRotation(-90);
		kid2.addAction(Actions.forever(Actions.sequence(Actions.delay(4f),
										Actions.moveBy(kidDeltaX, 0, kidT,kidInterpolationIn),
										Actions.delay(2.5f),
										Actions.moveBy(-kidDeltaX, 0, kidT,kidInterpolationOut),
										Actions.delay(9f)
						)));
		
		kid3.setTouchable(Touchable.disabled);
		kid3.setBounds(EQGlobals.w*1.37f, EQGlobals.h+kidH*0.43f, kidW, kidH);
		kid3.setRotation(135);
		kid3.addAction(Actions.forever(Actions.sequence(Actions.delay(7f),
										Actions.moveBy(MathUtils.cosDeg(225)*kidH, MathUtils.sinDeg(225)*kidH*0.9f, kidT,kidInterpolationIn),
										Actions.delay(2.5f),
										Actions.moveBy(-MathUtils.cosDeg(225)*kidH, -MathUtils.sinDeg(225)*kidH*0.9f, kidT,kidInterpolationOut),
										Actions.delay(9f)
						)));
		
		kid4.setTouchable(Touchable.disabled);
		kid4.setBounds(EQGlobals.w*0.05f, -kidH, kidW, kidH);
		kid4.setRotation(0);
		kid4.addAction(Actions.forever(Actions.sequence(Actions.delay(10f),
										Actions.moveBy(0, kidDeltaY, kidT,kidInterpolationIn),
										Actions.delay(2.5f),
										Actions.moveBy(0, -kidDeltaY, kidT,kidInterpolationOut),
										Actions.delay(9f)
						)));
		
		kidGroup=new Group();
		kidGroup.setTouchable(Touchable.disabled);
		kidGroup.setSize(EQGlobals.w,EQGlobals.h);
		kidGroup.addActor(kid1);
		kidGroup.addActor(kid2);
		kidGroup.addActor(kid3);
		kidGroup.addActor(kid4);
	}
	


	@Override
	public void resize(int width, int height) {
		
		super.resize(width, height);	
		
		mainMenuBg.setBounds(0, 0, width, height);	
		
		table.setBounds(0,tableY*height/EQGlobals.h,width,tableH*height/EQGlobals.h);
		
		mainMenuTitle.setPosition(width/2-titleWidth/2, titleCenterY*height/EQGlobals.h-titleHeight/2);

		//kidGroup.addAction(Actions.scaleTo(width/EQGlobals.w, height/EQGlobals.h));
		kid1.setX(kid1.getX()+(width-lastWidth));
		kid3.setX(kid3.getX()+(width-lastWidth));
		
		lastWidth = width;
		
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
