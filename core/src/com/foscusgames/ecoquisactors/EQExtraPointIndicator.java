package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQNormalMatch;
import com.foscusgames.ecoquis.EQSounds;
import com.foscusgames.ecoquis.EQTimeMatch;

/**
 * Actor that indicates when an extra point is available, and shows progress to achieve that extra point.
 * @author Camilo
 *
 */
public class EQExtraPointIndicator extends Group {
	
	private Image barHolder;
	private Image bar;
	private float x, y;
	private float indicatorWidth;
	private float indicatorHeight;
	private float maxBarWidth;
	private float margin;
	private int maxPoints;
	private int currentPoints;
	private Label indicatorLabel;
	private Color initColor;
	private Label fullLabel;
	private LabelStyle ls;
	private boolean isFull=false;
	private boolean maxPointsAcquired;

	/**
	 * General initialization shared by all constructors.
	 * @param x: x position of this actor (px)
	 * @param y: y position of this actor (px)
	 * @param w: width of this actor (px)
	 * @param h: height of this actor (px)
	 */
	public void init(float x, float y, float w, float h) {
//		barHolder = new Image(EQAssets.miscAtlas.findRegion("BarHolder"));
//		bar = new Image(EQAssets.miscAtlas.findRegion("Bar"));
		
		barHolder = new Image(EQAssets.whiteOverlay);
		barHolder.setColor(1f,1f,1f,0.7f);
		bar = new Image(EQAssets.whiteOverlay);
		bar.setColor(100/255f,131/255f,228/255f,1f);

		this.x = x;
		this.y = y;
		indicatorWidth = w;
		indicatorHeight = h;
		margin = indicatorWidth*0.04f;
		
		maxBarWidth = indicatorWidth;
		//maxBarWidth = indicatorWidth-2*margin;
		
		barHolder.setBounds(0,0,indicatorWidth, indicatorHeight);
		bar.setBounds(0,0,1, indicatorHeight);
		//bar.setBounds(margin, indicatorHeight*0.1f, 1, indicatorHeight*0.8f);
		

		isFull = false;
		currentPoints = 0;
		
		ls = new LabelStyle(EQAssets.answerFont, new Color(0f,0.5f,0.8f,1f));
		indicatorLabel = new Label("Punto Bonus Adquirido", ls);
		indicatorLabel.setAlignment(Align.center);
		resetIndicatorLabel();
		
		fullLabel = new Label("",ls);
		fullLabel.setAlignment(Align.center);
		fullLabel.setBounds(0, 0, indicatorWidth, indicatorHeight);

		this.addActor(barHolder);
		this.addActor(bar);

		//this.addActor(indicatorLabel);
		
	}
	
	public void resetIndicatorLabel() {
		initColor = new Color(0f,0.5f,0.8f,1f);
		float labelInitX = 0;
		float labelInitY = -indicatorHeight/2;
		indicatorLabel.setBounds(labelInitX,labelInitY,indicatorWidth,indicatorHeight);
		indicatorLabel.setColor(initColor);
		this.removeActor(indicatorLabel);
	}
	
	public EQExtraPointIndicator(EQNormalMatch match,float x, float y, float w, float h) {
		init(x,y,w,h);
		maxPoints = EQGlobals.maxExtraPoints;
		fullLabel.setText("¡Bonus activado! ¡Elegí un personaje!");
		
		
	}
	
	public EQExtraPointIndicator(EQTimeMatch match,float x, float y, float w, float h) {
		init(x,y,w,h);
		maxPoints = EQGlobals.maxComboPoints;
		fullLabel.setText("¡Combo activado! ¡+5' por respuesta correcta!");
		
	}

	public void onResize(int width, int height) {
		
		
		this.setPosition(EQGlobals.center.x-indicatorWidth/2, y*height/EQGlobals.h);
		
	}
	
	public void addPointAndScale() {
		currentPoints++;
		if (currentPoints>=maxPoints)  {
			currentPoints=maxPoints;
		}
		
		if(!isFull) {

			prepareIndicatorLabelAction();
			prepareScaleAction();
		}
	}
	



	public int getPoints() {
		return currentPoints;
	}
	
//	public void addPointAndScale() {
//		addPoint();
//	}
	
	public void prepareIndicatorLabelAction() {
		float t =1.5f;
		this.addActor(indicatorLabel);
		indicatorLabel.addAction(Actions.moveBy(0, indicatorHeight*2,t));				
		indicatorLabel.addAction(Actions.sequence(Actions.alpha(0,t),Actions.run(new Runnable() {
			
			@Override
			public void run() {
				resetIndicatorLabel();
				
			}
			
		})));
	}
	
	public void prepareScaleAction() {
		
		float scaleX = maxBarWidth*currentPoints/(float)maxPoints;
		float scaleY =1;
		
		bar.addAction(Actions.run(new Runnable() {

			@Override
			public void run() {
				EQSounds.playSound(EQSounds.barIncrease);
				
			}
			
		}));
		
		if (currentPoints!=maxPoints) {

			bar.addAction(Actions.scaleTo(scaleX, scaleY, 0.45f, Interpolation.exp5In));
			
		} else {
			
			bar.addAction(Actions.sequence(Actions.scaleTo(scaleX, scaleY, 0.45f, Interpolation.exp5In),
							Actions.run(new Runnable() {

								@Override
								public void run() {

									EQSounds.playSound(EQSounds.barCompleted);
									isFull=true;
									addFullLabel();
									
								}
								
							})));
			
		}

		
	}
	
	public void addFullLabel() {

		this.addActor(fullLabel);
	}
	
	public boolean isFull() {
		return isFull;
	}
	
	public void reset() {
		currentPoints = 0;
		bar.setScaleX(0);
		isFull = false;
		this.removeActor(fullLabel);
	}

	public boolean isEPBAvailable() {
		return currentPoints == maxPoints;
	}

}
