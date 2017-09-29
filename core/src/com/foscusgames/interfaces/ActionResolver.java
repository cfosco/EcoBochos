package com.foscusgames.interfaces;

public interface ActionResolver {
	
	  public boolean getSignedInGPGS();
	  public void loginGPGS();
	  public void submitNormalScoreGPGS(int score);
	  public void submitTimeScoreGPGS(int score);
	  public void unlockAchievementGPGS(String achievementId);
	  public void getNormalLeaderboardGPGS();
	  public void getTimeLeaderboardGPGS();
	  public void getAchievementsGPGS();
	public void getAllLeaderboardsGPGS();

	  
	  

}
