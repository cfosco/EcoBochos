package com.foscusgames.ecoquis;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.foscusgames.ecoquis.EQGlobals.Category;
import com.foscusgames.ecoquisactors.EQScoreTimer;
import com.foscusgames.ecoquishandlers.EQQuestionHandler;
import com.foscusgames.ecoquisscreens.GameScreen;
import com.foscusgames.ecoquisscreens.MainMenuScreen;
import com.foscusgames.ecoquisscreens.QuestionScreen;

/**
 * This class represents a Match, one particular game played by a player. Handles the main game loop of the match.
 * @author Camilo
 *
 */

public abstract class EQMatch {
	
	public enum MatchState {
		MAIN,SPINNING,QUESTION,ENDGAME,WIN,TIMEOUT
	}	

	protected MatchState currentState;
	protected int currentCategory = 0;
	protected Random r;
	protected GameScreen gameScreen;
	protected EQQuestionHandler questionHandler;
	protected EQQuestion currentQuestion;
	protected int score = 0;
	protected int numQuestionsAnswered=0;
	protected int numRightAnswers=0;
	protected int numWrongAnswers=0;
	protected EQGame game;
	protected QuestionScreen quesScreen;
	protected boolean answerClicked = false;
	protected EQScoreTimer scoreTimer;
	protected float hardProb;
	
	protected int[] points = {0,0,0,0,0};

	
	
	public EQMatch (EQQuestionHandler qH, EQGame game) {
		

		qH.generateQuestionIndexLists();
		
		this.quesScreen = new QuestionScreen(this,null);

		buildScoreTimer();
		buildGameScreen();
		
		game.setScreen(gameScreen);
		
		this.currentState = MatchState.MAIN;
		this.questionHandler = qH;
		this.r = new Random();
		this.game = game;
		
		this.hardProb = 0.2f;
		
	
	}
	
	public void onEarthSpinFinished() {

		boolean hard;
		
		if (r.nextFloat()>hardProb) {
			hard = false;
		} else {
			hard = true;
		}

		currentQuestion = questionHandler.getNewQuestion(currentCategory,hard);
		
		if(currentState != MatchState.TIMEOUT) {
			gameScreen.setCategoryOverlay(questionHandler.transformIntToCat(currentCategory),hard);
		}
	}
	
	
	public void setQuesScreen() {
		
		quesScreen.updateQuestion(currentQuestion);
		game.setScreen(quesScreen);
		
		//DEBUG
		Gdx.app.log("EQMatch", "Answer= "+(currentQuestion.getRightAnswer()+1));
		
		if(currentState != MatchState.ENDGAME) {
			Gdx.app.log("EQMatch", "Setting QUESTION state");
			this.currentState = MatchState.QUESTION;
		}
				
	}
	
	public void onAnswerSelection(int selectedAnswer) {
		
		boolean answeredRight = selectedAnswer == currentQuestion.getRightAnswer();		
		numQuestionsAnswered ++;		
		quesScreen.highlightRightAnswer(currentQuestion.getRightAnswer());	
		
		if (answeredRight) {
			if(currentQuestion.isHard()) addScore(EQGlobals.hardQuestionRightAnswerScore);
			else addScore(EQGlobals.easyQuestionRightAnswerScore);
			EQSounds.playSound(EQSounds.correct);
			numRightAnswers++;		
		} else {
			quesScreen.highlightWrongAnswer(selectedAnswer);
			addScore(EQGlobals.wrongAnswerScore);
			EQSounds.playSound(EQSounds.incorrect);
			numWrongAnswers ++;
		}

		quesScreen.showAnswerAnimation(answeredRight);
				
	};
	
	public void backToMainState() {
		
		answerClicked = false;
		
		switch(currentState) {
		case ENDGAME:
			game.setScreen(gameScreen);
			break;
		case QUESTION:
			game.setScreen(gameScreen);
			currentState = MatchState.MAIN;
			break;
		case MAIN:
			game.setScreen(gameScreen);
			break;
		case WIN:
			addBonusScore();
			gameScreen.onMatchFinished();
			EQSounds.playSound(EQSounds.win);
			game.setScreen(gameScreen);
			break;
		case TIMEOUT:
			//EQSounds.playSound(EQSounds.timeout);
			game.setScreen(gameScreen);
			//gameScreen.setEndgameOverlay();
			break;
		default:
			break;
			
		}
		

	}
	
	public abstract void addBonusScore();
	public abstract void newMatch();
	public abstract void buildGameScreen();
	public abstract void buildScoreTimer();
	public abstract void submitFinalScore();
	
	public boolean isAnswerClicked() {
		return answerClicked ;
	}

	public void setAnswerClicked(boolean b) {
		answerClicked = b;	
	}

	public void setState(MatchState s) {
		currentState=s;	
	}

	public int getRightAnswers() {
		return numRightAnswers;
	}

	public int getWrongAnswers() {
		return numWrongAnswers;
	}

	public int getQuestionsAnswered() {
		return numQuestionsAnswered;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getCategPoints(int cat) {
		return points[cat];
	}
	
	public void addScore(int s) {
		
		score +=s;
		scoreTimer.setScore(score);
		
	}

	public MatchState getState() {
		return currentState;
	}
	
	public int getCurrentCategory() {
		return currentCategory;
	}
	
	public int getNextRandomCategory() {

		return r.nextInt(EQGlobals.numberOfCategories);
	}
	
	public void setCurrentCategory(int newCategory) {
		currentCategory = newCategory;	
	}
	
	public void backToMainMenu() {
		game.setScreen(new MainMenuScreen(game));
	}
	
	public int getTime() {
		return scoreTimer.getRoundedTime();
	}

	public EQScoreTimer getScoreTimer() {
		return scoreTimer;
	}

	public void onFaceClick(Category cat) {
		
	}


	public void onPulsatingEarthClick() {

		currentQuestion = questionHandler.getNewQuestion(Category.EARTH,false);
		gameScreen.setCategoryOverlay(Category.EARTH,false);
		
	}
	
	public void addCategPoints(int p) {


		if (!(points[currentCategory]>=EQGlobals.numberOfTiers)) {
			
			points[currentCategory]+=p;
			if(points[currentCategory]>=EQGlobals.numberOfTiers)
				points[currentCategory]=EQGlobals.numberOfTiers;
			
		} else {
			
			gameScreen.getEPI().addPointAndScale();
			
		}
		
	}

	public void onFaceCompletion() {
		// TODO Auto-generated method stub
		
	}

	public void updateScoreAchievements() {
		
		if (score >=100) {
			game.actionResolver.unlockAchievementGPGS("CgkIxbOavPYTEAIQBA");
		}
		
		if (score >=200) {
			game.actionResolver.unlockAchievementGPGS("CgkIxbOavPYTEAIQBQ");
			
		}
		
		if (score >=300) {
			game.actionResolver.unlockAchievementGPGS("CgkIxbOavPYTEAIQBg");
			
		}
		
		if (score >=400) {
			game.actionResolver.unlockAchievementGPGS("CgkIxbOavPYTEAIQBw");
			
		}
		
		if (score >=500) {
			game.actionResolver.unlockAchievementGPGS("CgkIxbOavPYTEAIQCA");
			
		}
		
	}

	public void removeTutorialLabel() {
		gameScreen.removeTutorialLabel();
		
	}


}
