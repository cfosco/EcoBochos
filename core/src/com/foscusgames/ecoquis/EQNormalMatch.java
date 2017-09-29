package com.foscusgames.ecoquis;

import com.badlogic.gdx.math.MathUtils;
import com.foscusgames.ecoquis.EQGlobals.Category;
import com.foscusgames.ecoquisactors.EQNMTimer;
import com.foscusgames.ecoquishandlers.EQQuestionHandler;
import com.foscusgames.ecoquisscreens.GameScreen;

public class EQNormalMatch extends EQMatch {
	
	
	private int facePointsOnNextRightAnswer=1;

	public EQNormalMatch (EQQuestionHandler qH, EQGame g) {
		
		super(qH,g);
		
	}
	
	public void onAnswerSelection(int selectedAnswer) {
		
		super.onAnswerSelection(selectedAnswer);
		boolean answeredRight = selectedAnswer == currentQuestion.getRightAnswer();		
		
		if (answeredRight) {
			if (currentState != MatchState.ENDGAME) {
				
				addCategPoints(facePointsOnNextRightAnswer);
				
				if (checkCompletion()) {
					onAllFacesCompletion();
				}
			
			} else {
				
				currentState = MatchState.WIN;
			
			}
		}
		
		gameScreen.updateFaceHolder(points);
		gameScreen.updateEPBActions();
		
		facePointsOnNextRightAnswer=1;
	}
	
	@Override
	public void onFaceClick(Category cat) {
		
		if (gameScreen.getEPI().isFull() && (currentState!=MatchState.WIN) && points[questionHandler.transformCatToInt(cat)] != 3) {
			
			resetExtraPoints();

			facePointsOnNextRightAnswer=3;
			currentCategory = questionHandler.transformCatToInt(cat);
			currentQuestion = questionHandler.getNewQuestion(currentCategory,false);
			
			setQuesScreen();
			
		}
		
	}
	
	
	private void onAllFacesCompletion() {
		
		currentState = MatchState.ENDGAME;
		gameScreen.onAllFacesCompletion();
		
	}
	

	
	private boolean checkCompletion() {
		
		
		for (int i = 0; i < EQGlobals.numberOfCategories; i++) {
			if (points[i] < EQGlobals.numberOfTiers) {
				
				return false;
				
			}
		}
		
		return true;
		
	}
	

	
//	public boolean isExtraPointBonusAvailable() {
//		return extraPointBonusAvailable;
//	}
	
	private void resetExtraPoints() {
		gameScreen.getEPI().reset();
	}
	

//	public int getExtraPoints() {
//		return extraPoints;
//	}

	@Override
	public void newMatch() {
		game.newNormalMatch();
		
	}
	
	@Override
	public void buildGameScreen() {

		this.gameScreen = new GameScreen(this);
		
	}

	@Override
	public void buildScoreTimer() {
		scoreTimer = new EQNMTimer(EQAssets.questionFont, EQAssets.whiteOverlay);		
	}
	
	public void addBonusScore() {
		
		int s = MathUtils.ceil((-scoreTimer.getRoundedTime() + 500)/10f)*10;
		if (s<0) s=0;
		score+= s;
		
		
	}

	@Override
	public void submitFinalScore() {

		if(game.actionResolver.getSignedInGPGS()) {
			game.actionResolver.submitNormalScoreGPGS(score);
		} else {
			game.actionResolver.loginGPGS();
		}
		
	}



}
