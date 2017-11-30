package com.foscusgames.ecoquis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
/** 
 * Class for loading, updating and managing game assets such as Textures, Sounds and Fonts.
 * 
 * @author Camilo
 *
 */
public class EQAssets {
	
	private static AssetManager mgr;
	
	public static Texture laser;
	public static Texture loadingBg;
	//public static Texture winImg;
	public static Texture mainMenuBg, mainMenuIcon, questionHolder, correctImg, wrongImg, blackOverlay, whiteOverlay, ganasteSplash, tiempoSplash;
	
	public static TextureAtlas  facesAtlas, buttonAtlas, earthAtlas, miniFacesAtlas, iconsAtlas,  miscAtlas	;
	
	public static BitmapFont scoreFont, mainMenuFont, questionFont, numberFont, answerFont;

//	public static Sound buttonClick;

	public static Texture renaultLogo, casatierraLogo, neurarLogo;

	public static BitmapFont timerFont;

	public static TextureAtlas quesWaterAtlas;

	public static TextureAtlas quesEnergyAtlas;

	public static TextureAtlas quesBiodivAtlas;

	public static TextureAtlas quesMobilAtlas;

	public static TextureAtlas quesConsumAtlas;

	public static TextureAtlas quesEarthAtlas;

	public static TextureAtlas kid1Atlas;

	public static TextureAtlas kid2Atlas;

	public static TextureAtlas kid3Atlas;

	public static TextureAtlas kid4Atlas;




	/**
	 Instantiates an AssetManager, libgdx class for loading different kinds of assets with built in progress estimation
	 */
	public static void generateAssetManager() {
		mgr = new AssetManager();
	}
	
	
	/**
	 * When called, loads all textures, fonts and sounds. Called only once, when the program is booting.
	 */
	public static void loadAssets() {
		
		// TEXTURES
		mgr.load("FondoEcoQuis1.png", Texture.class);
		mgr.load("Icono de menu principal.png", Texture.class);
		mgr.load("BlackOverlay.png", Texture.class);
		mgr.load("WhiteOverlay.png", Texture.class);
		mgr.load("casatierra.png", Texture.class);
		mgr.load("logo Renault.png", Texture.class);
		mgr.load("Logo NeurAr Games.png", Texture.class);
		mgr.load("ganasteSplash.png", Texture.class);
		mgr.load("tiempoSplash.png", Texture.class);
		
		// TEXTURE ATLASES
		mgr.load("EQFaces.pack", TextureAtlas.class);
		mgr.load("EarthFramesShort.pack", TextureAtlas.class);
		mgr.load("EQMiniFaces.pack", TextureAtlas.class);
		mgr.load("EQIcons.pack", TextureAtlas.class);
		mgr.load("EQButtons.pack", TextureAtlas.class);
		mgr.load("EQMiscActors.pack",TextureAtlas.class);
		mgr.load("EQQuesButtonsWater.pack",TextureAtlas.class);
		mgr.load("EQQuesButtonsEnergy.pack",TextureAtlas.class);
		mgr.load("EQQuesButtonsBiodiv.pack",TextureAtlas.class);
		mgr.load("EQQuesButtonsMobil.pack",TextureAtlas.class);
		mgr.load("EQQuesButtonsConsum.pack",TextureAtlas.class);
		mgr.load("EQQuesButtonsEarth.pack",TextureAtlas.class);
		mgr.load("EQKid1.pack", TextureAtlas.class);
		mgr.load("EQKid2.pack", TextureAtlas.class);
		mgr.load("EQKid3.pack", TextureAtlas.class);
		mgr.load("EQKid4.pack", TextureAtlas.class);

		// FONTS
		mgr.load("WoodyBlack.fnt", BitmapFont.class);
		mgr.load("SwashbuckleWhite32.fnt", BitmapFont.class);
		mgr.load("SwashbuckleWhite70.fnt", BitmapFont.class);
		mgr.load("SwashbuckleWhite140.fnt", BitmapFont.class);
		//mgr.load("AlbaWhite.fnt", BitmapFont.class);
		mgr.load("ComicNeueWhite30Bold.fnt", BitmapFont.class);
		mgr.load("ComicNeueBlack26.fnt", BitmapFont.class);
		mgr.load("ComicNeueWhite20.fnt", BitmapFont.class);
		
		// SOUNDS
		EQSounds.loadSounds(mgr);
		
		
		//mgr.load("ButtonClick.wav", Sound.class);
		
	}
	
	/**
	 *  Puts all the loaded assets into easy to call static variables.
	 */
	public static void generateVariables() {
		
		// TEXTURES
		mainMenuBg = mgr.get("FondoEcoQuis1.png", Texture.class);
		mainMenuIcon = mgr.get("Icono de menu principal.png", Texture.class);
		blackOverlay = mgr.get("BlackOverlay.png", Texture.class);
		whiteOverlay = mgr.get("WhiteOverlay.png", Texture.class);
		neurarLogo = mgr.get("Logo NeurAr Games.png", Texture.class);
		renaultLogo = mgr.get("logo Renault.png", Texture.class);
		casatierraLogo = mgr.get("casatierra.png", Texture.class);
		ganasteSplash = mgr.get("ganasteSplash.png", Texture.class);
		tiempoSplash = mgr.get("tiempoSplash.png", Texture.class);
		
		// TEXTURE ATLASES
		facesAtlas = mgr.get("EQFaces.pack", TextureAtlas.class);
		earthAtlas = mgr.get("EarthFramesShort.pack", TextureAtlas.class);
		miniFacesAtlas = mgr.get("EQMiniFaces.pack", TextureAtlas.class);
		iconsAtlas = mgr.get("EQIcons.pack", TextureAtlas.class);
		buttonAtlas = mgr.get("EQButtons.pack", TextureAtlas.class);
		miscAtlas = mgr.get("EQMiscActors.pack",TextureAtlas.class);
		//quesButtonAtlas = mgr.get("QuestionButtons.pack",TextureAtlas.class);
		//kidAtlas = mgr.get("EQKids.pack", TextureAtlas.class);
		quesWaterAtlas = mgr.get("EQQuesButtonsWater.pack",TextureAtlas.class);
		quesEnergyAtlas = mgr.get("EQQuesButtonsEnergy.pack",TextureAtlas.class);
		quesBiodivAtlas = mgr.get("EQQuesButtonsBiodiv.pack",TextureAtlas.class);
		quesMobilAtlas = mgr.get("EQQuesButtonsMobil.pack",TextureAtlas.class);
		quesConsumAtlas = mgr.get("EQQuesButtonsConsum.pack",TextureAtlas.class);
		quesEarthAtlas = mgr.get("EQQuesButtonsEarth.pack",TextureAtlas.class);
		kid1Atlas = mgr.get("EQKid1.pack", TextureAtlas.class);
		kid2Atlas = mgr.get("EQKid2.pack", TextureAtlas.class);
		kid3Atlas = mgr.get("EQKid3.pack", TextureAtlas.class);
		kid4Atlas = mgr.get("EQKid4.pack", TextureAtlas.class);
		
		// FONTS
		mainMenuFont = mgr.get("SwashbuckleWhite32.fnt", BitmapFont.class);
		questionFont = mgr.get("ComicNeueWhite30Bold.fnt", BitmapFont.class);
		answerFont = mgr.get("ComicNeueBlack26.fnt", BitmapFont.class);
		numberFont = mgr.get("SwashbuckleWhite70.fnt", BitmapFont.class);
		scoreFont = mgr.get("SwashbuckleWhite140.fnt", BitmapFont.class);
		timerFont = mgr.get("ComicNeueWhite20.fnt", BitmapFont.class);

		
		scaleFonts();
		
	}
	
	
	
	private static void scaleFonts() {

			float x1 = 520;
			float x2 = 1080;
			float y1 = -0.07f;
			float y2 = 0.7f;
			
			EQGlobals.globalFontScale = ((y2-y1)/(x2-x1))*(Gdx.graphics.getWidth()-x1)+y1;

			mainMenuFont.getData().scale(EQGlobals.globalFontScale);
			questionFont.getData().scale(EQGlobals.globalFontScale);
			answerFont.getData().scale(EQGlobals.globalFontScale);
			numberFont.getData().scale(EQGlobals.globalFontScale);
			scoreFont.getData().scale(EQGlobals.globalFontScale);
			timerFont.getData().scale(EQGlobals.globalFontScale);
			
			
		
	}

	/**
	 * This function prepares the assets that are needed during the loading screen, before everything else is properly loaded.
	 */
	public static void prepareLoadingScreen() {
		
        mgr.load("WhiteOverlay.png", Texture.class);
		mgr.load("Pantalla de carga.png", Texture.class);
        
        // Wait until they are finished loading
        mgr.finishLoading();
        
        whiteOverlay = mgr.get("WhiteOverlay.png", Texture.class);
        loadingBg = mgr.get("Pantalla de carga.png", Texture.class);
		
	}

	public static float getLoadProgress() {
		return mgr.getProgress();
	}

	public static void dispose() {
		mgr.dispose();
		
	}

	public static boolean update() {
		return mgr.update();
	}
	
	public static AssetManager getAssetMgr() {
		return mgr;
	}

	public static Drawable getDrawable(Texture t) {
		return new TextureRegionDrawable(new TextureRegion(t));
	}




	
	
}
