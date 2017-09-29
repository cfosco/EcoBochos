package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class EQScoreTimer extends Group {
	

	protected float width;
	protected float height;
	protected float elapsedTime;
	protected int score;
	
	public EQScoreTimer() {
		
		elapsedTime = 0;
		score = 0;

	}


	public int getRoundedTime() {
		return   Math.round(elapsedTime);
	}
	

	@Override
	public void act(float delta) {
		
		super.act(delta);
		
		elapsedTime += delta;
		
		
	}


	public void setScore(int s) {
		score = s;
		
	}


//	public void addTime(int i) {
//		
//		elapsedTime+=i;
//		
//	}


	public void onResize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	public abstract void addTime(int i);
}
