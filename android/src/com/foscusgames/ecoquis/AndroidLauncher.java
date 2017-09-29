package com.foscusgames.ecoquis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.foscusgames.interfaces.ActionResolver;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;

import android.content.Intent;
import android.os.Bundle;

public class AndroidLauncher extends AndroidApplication implements GameHelperListener, ActionResolver {
	
	private GameHelper gameHelper;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new EQGame(this), config);
		
		if (gameHelper == null) {
			gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
			gameHelper.enableDebugLog(true);
		}
		
		gameHelper.setup(this);
	}
	
	@Override
	public void onStart() {
		Gdx.app.log("ANDROID LAUNCHER", "ONSTART CALLED");
		super.onStart();
		gameHelper.onStart(this);
	}
	
	@Override
	public void onStop() {
		Gdx.app.log("ANDROID LAUNCHER", "ONSTOP CALLED");
		Gdx.app.log("AndroidLauncher", "Density:"+Gdx.graphics.getDensity());
		super.onStart();
		gameHelper.onStop();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean getSignedInGPGS() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void loginGPGS() {
		
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
			
		} catch (final Exception ex) {
			
		}
		
	}

	@Override
	public void submitNormalScoreGPGS(int score) {
		Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkIxbOavPYTEAIQAA", score);
		
	}
	
	public void submitTimeScoreGPGS(int score) {
		Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkIxbOavPYTEAIQAQ", score);

	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
		
	}

	@Override
	public void getNormalLeaderboardGPGS() {
		
			if (gameHelper.isSignedIn()) {
				    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkIxbOavPYTEAIQAA"), 100);
			}
			else if (!gameHelper.isConnecting()) {
				    loginGPGS();
			}
		
	}
	
	@Override
	public void getTimeLeaderboardGPGS() {
		
		if (gameHelper.isSignedIn()) {
			    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkIxbOavPYTEAIQAQ"), 100);
		}
		else if (!gameHelper.isConnecting()) {
			    loginGPGS();
		}
	
}
	
	@Override
	public void getAllLeaderboardsGPGS() {
		
		if (gameHelper.isSignedIn()) {
			    startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(gameHelper.getApiClient()), 1);
		}
		else if (!gameHelper.isConnecting()) {
			    loginGPGS();
		}
	
	}

	@Override
	public void getAchievementsGPGS() {	
		
			if (gameHelper.isSignedIn()) {
				    startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);
			}
			else if (!gameHelper.isConnecting()) {
				    loginGPGS();
			}
		
	}

	@Override
	public void onSignInFailed() {
		Gdx.app.log("AndroidLauncher", "GPGS SIGN IN FAILED");
		
	}

	@Override
	public void onSignInSucceeded() {
		Gdx.app.log("AndroidLauncher", "GPGS SIGN IN SUCCEEDED");
		
	}
}
