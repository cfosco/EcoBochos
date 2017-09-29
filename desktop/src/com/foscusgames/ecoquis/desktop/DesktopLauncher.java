package com.foscusgames.ecoquis.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.foscusgames.ecoquis.EQGame;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.interfaces.ActionResolverDesktop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "EcoBochos";
		config.width = 520;		//640;
		config.height = 900;	// 1136;
		config.addIcon("EcoCuis_Icon_32.png", Files.FileType.Internal);
		
		EQGlobals.encoding = "UTF-8";
		
		
		new LwjglApplication(new EQGame(new ActionResolverDesktop()), config);
		

		Gdx.app.log("DesktopLauncher", "Density:"+Gdx.graphics.getDensity());
	}
}
