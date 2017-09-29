package com.foscusgames.interfaces;

import com.badlogic.gdx.Gdx;

public class ActionResolverDesktop implements ActionResolver {

	boolean signedInStateGPGS = false;
	
	@Override
	public boolean getSignedInGPGS() {
		return signedInStateGPGS;
	}

	@Override
	public void loginGPGS() {
		Gdx.app.log("ActionResolverDesktop", "loginGPGS");
		signedInStateGPGS = true;
		
	}

	@Override
	public void submitNormalScoreGPGS(int score) {

		Gdx.app.log("ActionResolverDesktop", "submitNormalScoreGPGS");
		
	}

	@Override
	public void submitTimeScoreGPGS(int score) {
		Gdx.app.log("ActionResolverDesktop", "submitTimeScoreGPGS");
		
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		Gdx.app.log("ActionResolverDesktop", "unlockAchievementGPGS. ACHIEVEMENT UNLOCKED: "+achievementId);
		
	}

	@Override
	public void getNormalLeaderboardGPGS() {
		Gdx.app.log("ActionResolverDesktop", "getNormalLeaderboardGPGS");
	}
	
	@Override
	public void getTimeLeaderboardGPGS() {
		Gdx.app.log("ActionResolverDesktop", "getTimeLeaderboardGPGS");
	}
	
	@Override
	public void getAllLeaderboardsGPGS() {
		Gdx.app.log("ActionResolverDesktop", "getTimeLeaderboardGPGS");
	}


	@Override
	public void getAchievementsGPGS() {
		Gdx.app.log("ActionResolverDesktop", "getAchievementsGPGS");
		
	}

}
