package com.foscusgames.ecoquis;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.foscusgames.ecoquis.EQGlobals.Category;

public class EQQuestion {
	
	Category category;
	int answerNum;
	String question;
	ArrayList<String> answers;
	
	boolean isHard;
	
	public EQQuestion(String question, ArrayList<String> answers, int ans, Category cat, boolean isHard) {
		
		this.isHard = isHard;
		this.question = question;
		this.answers = answers;
		this.category = cat;
		this.answerNum = ans;

		
	}
	
	public EQQuestion(String q1, String a1, String a2, String a3, String a4, int ans, Category cat, boolean isHard) {
		this.question = q1;
		this.answers = new ArrayList<String>();
		this.answers.add(a1);
		this.answers.add(a2);
		this.answers.add(a3);
		this.answers.add(a4);
		this.answerNum = ans;
		this.category = cat;
		this.isHard = isHard;
		
	}

	public Category getCategory() {
		return category;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public ArrayList<String> getAnswers() {
		return answers;
	}
	
	public void printQuestion() {
		Gdx.app.log("EQQuestion", "Q="+question+"\na1="+answers.get(0)+"\na2="+answers.get(1)+"\na3="+answers.get(2)+"\na4="+answers.get(3)+"\nAnswer="+(answerNum+1)+"\nCat="+category);

	}	
	

	public String getAnswer(int i) {
		return answers.get(i);
	}

	public int getRightAnswer() {
		return answerNum;
	}

	public boolean isHard() {
		return isHard;
	}

}
