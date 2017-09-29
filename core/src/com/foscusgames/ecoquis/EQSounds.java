package com.foscusgames.ecoquis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.foscusgames.ecoquis.EQGlobals.Category;

public class EQSounds {

	private static boolean soundOn = true;
	public static Sound buttonClick, faceCompleted, win, correct, incorrect, timeout, timeRunningOut,
						mobilSound, biodivSound, energySound, consumSound, waterSound, earthSound, earthSpinTime, earthSpinNormal, 
						categSelected, faceIncreased, barCompleted, barIncrease, playQuestion, normalModeSound, timeModeSound;
	public static Music mainMenuTheme;
	public static Music earthTheme;
	
	public static void loadSounds(AssetManager mgr) {
		
		//mgr.load("ButtonPress.wav", Sound.class);
		//buttonClick = mgr.get("ButtonPress.wav", Sound.class);
		
		buttonClick = Gdx.audio.newSound(Gdx.files.internal("ButtonPress.wav"));
		faceCompleted = Gdx.audio.newSound(Gdx.files.internal("flecha-campana.mp3"));	//fuegos artificiales 2.wav
		win = Gdx.audio.newSound(Gdx.files.internal("bravo compacto F.wav"));
		correct = Gdx.audio.newSound(Gdx.files.internal("GanoMM F.wav"));
		incorrect = Gdx.audio.newSound(Gdx.files.internal("perdio voces F.wav"));
		timeout = Gdx.audio.newSound(Gdx.files.internal("gong F.wav"));
		timeRunningOut = Gdx.audio.newSound(Gdx.files.internal("tic-toc lento.wav"));
		earthSpinNormal = Gdx.audio.newSound(Gdx.files.internal("barquillero2.mp3"));
		earthSpinTime = Gdx.audio.newSound(Gdx.files.internal("barquillero.mp3"));
		categSelected = Gdx.audio.newSound(Gdx.files.internal("campanita delicada.mp3"));
		barCompleted = Gdx.audio.newSound(Gdx.files.internal("campanita delicada.mp3"));
		barIncrease = Gdx.audio.newSound(Gdx.files.internal("botella llenandose.wav"));
		playQuestion = Gdx.audio.newSound(Gdx.files.internal("1xilofon rapido.wav"));
		faceIncreased = Gdx.audio.newSound(Gdx.files.internal("campanita delicada.mp3"));
		normalModeSound = Gdx.audio.newSound(Gdx.files.internal("campana media F.mp3"));
		timeModeSound = Gdx.audio.newSound(Gdx.files.internal("tic-toc rapido.wav"));
		
		
		mobilSound = Gdx.audio.newSound(Gdx.files.internal("mobil.wav"));
		biodivSound = Gdx.audio.newSound(Gdx.files.internal("biodiv2.wav"));
		energySound = Gdx.audio.newSound(Gdx.files.internal("energy.wav"));
		consumSound = Gdx.audio.newSound(Gdx.files.internal("consum.wav"));
		waterSound = Gdx.audio.newSound(Gdx.files.internal("water.wav"));
		earthSound = Gdx.audio.newSound(Gdx.files.internal("gaviota.mp3"));

		mainMenuTheme = Gdx.audio.newMusic(Gdx.files.internal("Oncle_Ernest_Porte_Secrete.mp3"));
		mainMenuTheme.setLooping(true);
		mainMenuTheme.setVolume(0.7f);
		
		earthTheme = Gdx.audio.newMusic(Gdx.files.internal("earthTheme.mp3"));
		earthTheme.setLooping(true);
		earthTheme.setVolume(1.1f);
		
	}
	
	public static void playSound(Sound s) {
		if(soundOn) {
			s.play();
		}
	}
	
	public static void playMusic(Music m) {
		if(soundOn) m.play();
	}
	
	public static void toggleSoundOn() {
		soundOn = !soundOn;
		
		if (soundOn) {
			mainMenuTheme.play();
		} else {
			mainMenuTheme.stop();
		}
	}

	public static void playCategSound(Category cat) {

		if (soundOn) {
			switch(cat) {
			case BIODIV:
				biodivSound.play();
				break;
			case CONSUM:
				consumSound.play();
				break;
			case CUADERNO:
				earthSound.play();
				break;
			case EARTH:
				earthSound.play();
				break;
			case ENERGY:
				energySound.play();
				break;
			case MOBIL:
				mobilSound.play();
				break;
			case WATER:
				waterSound.play();
				break;
			default:
				break;
			
			}
		}
	}

	public static void playEarthFinalMusic() {
		mainMenuTheme.stop();
		if(soundOn) {

			earthTheme.play();
		}
	}

	public static void pauseMusic() {
		mainMenuTheme.stop();
		
	}

	public static boolean isSoundOn() {
		return soundOn;
	}

}
