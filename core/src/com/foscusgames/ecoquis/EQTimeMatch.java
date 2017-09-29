package com.foscusgames.ecoquis;

import com.badlogic.gdx.Gdx;
import com.foscusgames.ecoquisactors.EQTMTimer;
import com.foscusgames.ecoquishandlers.EQQuestionHandler;
import com.foscusgames.ecoquisscreens.GameScreen;

public class EQTimeMatch extends EQMatch {
	
	public EQTimeMatch(EQQuestionHandler qH, EQGame g) {
		
		super(qH,g);
		
		
	}

	@Override
	public void onAnswerSelection(int selectedAnswer) {
		
		super.onAnswerSelection(selectedAnswer);
		
		boolean answeredRight = selectedAnswer == currentQuestion.getRightAnswer();		

		if (answeredRight) {
			addCategPoints(1);
			gameScreen.getEPI().addPointAndScale();
			addTimeBasedOnComboPoints(); //Meter combo tipo ball king
		} else {
			gameScreen.getEPI().reset();
		}
		
		if (gameScreen.isEarthPulsating()) {
			gameScreen.removeEarthPulsateAction();
		}
		
		gameScreen.updateFaceHolder(points);
		

		Gdx.app.log("EQTimeMatch","current Matchstate="+currentState);
		
	}


	private void addTimeBasedOnComboPoints() {
		if (gameScreen.getEPI().isFull()) {
			scoreTimer.addTime(5);
		} else {
			scoreTimer.addTime(1);
		}
		
	}

	@Override
	public void newMatch() {
		game.newTimeMatch();
		
	}

	@Override
	public void buildGameScreen() {

		this.gameScreen = new GameScreen(this);
		
	}

	public void onTimeUp() {
		
		currentState = MatchState.TIMEOUT;
		
	}
	
	@Override
	public void buildScoreTimer() {
		scoreTimer = new EQTMTimer(EQAssets.timerFont, EQAssets.whiteOverlay,this);		
	}

	@Override
	public void onFaceCompletion() {
		
		Gdx.app.log("EQTimeMatch","OnFaceCompletion Entered");
		scoreTimer.addTime(5); //Hacer que addTime ademas agregue una accion que llame la atención
		gameScreen.addEarthPulsateAction();
		
	}

	@Override
	public void addBonusScore() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void submitFinalScore() {

		if(game.actionResolver.getSignedInGPGS()) {
			game.actionResolver.submitTimeScoreGPGS(score);
		} else {
			game.actionResolver.loginGPGS();
		}
		
	}
}
