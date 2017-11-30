package com.foscusgames.ecoquis;

import com.badlogic.gdx.math.Vector2;


/**
 * Stores global variables used throughout the project.
 * @author Camilo
 *
 */
public class EQGlobals {
	
	public static final int easyQuestionRightAnswerScore = 10;
	public static final int hardQuestionRightAnswerScore = 20;
	public static final int wrongAnswerScore = -5;
	public static int numberOfCategories = 5;
	public static int numberOfTiers = 3;
	public static final int maxExtraPoints = 2;
	public static final int maxComboPoints = 4;
	public static int w = 600;
	public static int h = 800;
	public static Vector2 center = new Vector2(300,400);
	public enum Category {
		MOBIL, ENERGY, BIODIV, CONSUM, WATER, EARTH, CUADERNO;
	}

	public static int timeModeStartingTime = 120;
	public static final boolean DEBUG =false;
	public static String encoding = "UTF-8";	//"ANSI";
	public static String versionNum = "";
	public static float globalFontScale=0;
	
}
