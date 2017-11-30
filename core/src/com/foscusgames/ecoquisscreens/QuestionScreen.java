package com.foscusgames.ecoquisscreens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQGlobals.Category;
import com.foscusgames.ecoquis.EQMatch;
import com.foscusgames.ecoquis.EQMatch.MatchState;
import com.foscusgames.ecoquis.EQQuestion;
import com.foscusgames.ecoquisactors.BlinkAnimation;

/**
 * Main screen that is shown when answering a question. Contains the question holder, the 4 answers, 
 * and shows the correct or incorrect popup when the user makes a choice.
 * @author Camilo
 *
 */
public class QuestionScreen extends EQScreen {
	
	Table quesTable;
	EQQuestion q;
	EQMatch match;
	Group questionHolder, catLabelHolder;
	Image categoryFace, bg;
	private ArrayList<TextButton> answerHolders;
	private BitmapFont font;
	private Label questionLabel, catLabel;
	private Image correctPopUp;
	private Image incorrectPopUp;
	private float faceWidth;
	private float faceHeight;
	private float questionHeight;
	private float answerPad;
	private float answerHeight;
	private float holderWidth;
	private Image questionLabelBg;
	private float titleHeight;
	private float popUpTime;
	private float popUpDelay;
	private BlinkAnimation biodivAnim;
	private BlinkAnimation waterAnim;
	private BlinkAnimation consumAnim;
	private BlinkAnimation energyAnim;
	private BlinkAnimation mobilAnim;
	private BlinkAnimation earthAnim;
	
	
	
	public QuestionScreen(EQMatch eqMatch, EQQuestion q) {
		
		super();
		this.q = q;
		this.match = eqMatch;

		bg = new Image(EQAssets.mainMenuBg);
		
		popUpTime = 0.7f;
		popUpDelay = 1.6f;
		
		if (EQGlobals.DEBUG) {
			popUpDelay = 0f;
		}
		
		createFaceAnims();
	}

	@Override
	public void show() {
		
		super.show();
		
		
		correctPopUp = new Image(EQAssets.iconsAtlas.findRegion("CorrectMark"));
		incorrectPopUp = new Image(EQAssets.iconsAtlas.findRegion("IncorrectMark"));

		font = EQAssets.questionFont;
		//font.getData().setScale(1f,1f);
		
		quesTable = new Table();
		

		holderWidth = EQGlobals.w*0.8f;
		answerHeight = EQGlobals.h*0.1f;
		titleHeight= EQGlobals.h*0.08f;
		answerPad = EQGlobals.h*0.03f;
		questionHeight = EQGlobals.h*0.25f;
		
		prepareQuesTableElements(q.getCategory());
		

	
//		quesTable.add(new EQTimeScore(font, EQAssets.questionHolder));
//		quesTable.row();
		quesTable.add(catLabelHolder).size(holderWidth, titleHeight).align(Align.left);
		quesTable.row();
		quesTable.add(questionHolder).size(holderWidth, questionHeight);
		quesTable.row();
		quesTable.add(answerHolders.get(0)).size(holderWidth, answerHeight).padTop(answerPad);//size(300, 50);
		quesTable.row();
		quesTable.add(answerHolders.get(1)).size(holderWidth, answerHeight).padTop(answerPad);//size(300, 50);
		quesTable.row();
		quesTable.add(answerHolders.get(2)).size(holderWidth, answerHeight).padTop(answerPad);//size(300, 50);
		quesTable.row();
		quesTable.add(answerHolders.get(3)).size(holderWidth, answerHeight).padTop(answerPad);//size(300, 50);
		//quesTable.setDebug(true);
		

		//quesTable.setBounds(0, 0, EQGlobals.w, EQGlobals.h);
		
		stage.clear();
		
		stage.addActor(bg);
		stage.addActor(quesTable);
		stage.addActor(match.getScoreTimer());
		

		prepareActions();
		
		
	
		
	}
	


	
	@Override
	public void render(float delta) {
		super.render(delta);
		   
		   if(match.getState()==MatchState.TIMEOUT) {
			   match.backToMainState();
		   }
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		bg.setBounds(0, 0, width, height);
		
		quesTable.setBounds(0, 0, width, height);
		
		match.getScoreTimer().onResize(width, height);
		
		
	}
	
	private void prepareQuesTableElements(Category cat) {
		
		questionHolder = new Group();
		
		Skin quesSkin = new Skin();
//		TextButtonStyle answerStyle = new TextButtonStyle(quesSkin.getDrawable("ButtonDownDefault"), 
//															quesSkin.getDrawable("ButtonDownDefault"), 
//															quesSkin.getDrawable("ButtonDownDefault"),
//															EQAssets.answerFont);
		TextButtonStyle answerStyle = new TextButtonStyle(null,null,null,EQAssets.answerFont);
		
		LabelStyle questionStyle = new LabelStyle(font, new Color(0,0,0,1));
		questionLabel = new Label(q.getQuestion(),questionStyle);


		questionLabel.setWrap(true);
		questionLabel.setBounds(holderWidth*0.1f, questionHeight*0.1f, holderWidth*0.8f, questionHeight*0.8f);
		questionLabel.setAlignment(Align.center);
		questionLabelBg = new Image();
		questionLabelBg.setBounds(0, 0, holderWidth, questionHeight);


		LabelStyle titleStyle = new LabelStyle(EQAssets.mainMenuFont, new Color(1,1,1,1));
		catLabel = new Label("",titleStyle);
		catLabelHolder = new Group();
		catLabelHolder.addActor(catLabel);
		catLabel.setBounds(0, 0, holderWidth, titleHeight);
		catLabelHolder.setTransform(true);
		
		
		float scale = EQGlobals.w/1200f;
		
		
		switch(cat) {
			case BIODIV:
				quesSkin.addRegions(EQAssets.quesBiodivAtlas);
				categoryFace = new AnimatedImage(biodivAnim);
				questionLabelBg.setDrawable(quesSkin, "recuadro biodiversidad pregunta"); 
				answerStyle.up = quesSkin.getDrawable("recuadro biodiversidad respuesta");
				answerStyle.down = quesSkin.getDrawable("recuadro biodiversidad respuesta presionado");
				answerStyle.checked = quesSkin.getDrawable("recuadro biodiversidad respuesta presionado");
				catLabel.setText("Biodiversidad");
				break;
			case CONSUM:
				quesSkin.addRegions(EQAssets.quesConsumAtlas);
				categoryFace = new AnimatedImage(consumAnim);
				questionLabelBg.setDrawable(quesSkin, "recuadro consumo pregunta"); 
				answerStyle.up = quesSkin.getDrawable("recuadro consumo respuesta");
				answerStyle.down = quesSkin.getDrawable("recuadro consumo respuesta presionado");
				answerStyle.checked = quesSkin.getDrawable("recuadro consumo respuesta presionado");
				catLabel.setText("Consumo");
				break;
			case ENERGY:
				quesSkin.addRegions(EQAssets.quesEnergyAtlas);
				categoryFace = new AnimatedImage(energyAnim);
				questionLabelBg.setDrawable(quesSkin, "recuadro energia pregunta"); 
				answerStyle.up = quesSkin.getDrawable("recuadro energia respuesta");
				answerStyle.down = quesSkin.getDrawable("recuadro energia respuesta presionado");
				answerStyle.checked = quesSkin.getDrawable("recuadro energia respuesta presionado");
				catLabel.setText("Energía");
				break;
			case MOBIL:
				quesSkin.addRegions(EQAssets.quesMobilAtlas);
				categoryFace = new AnimatedImage(mobilAnim);
				questionLabelBg.setDrawable(quesSkin, "recuadro movilidad pregunta"); 
				answerStyle.up = quesSkin.getDrawable("recuadro movilidad respuesta");
				answerStyle.down = quesSkin.getDrawable("recuadro movilidad respuesta presionado");
				answerStyle.checked = quesSkin.getDrawable("recuadro movilidad respuesta presionado");
				catLabel.setText("Movilidad");
				break;
			case WATER:
				quesSkin.addRegions(EQAssets.quesWaterAtlas);
				categoryFace = new AnimatedImage(waterAnim);
				questionLabelBg.setDrawable(quesSkin, "recuadro agua pregunta"); 
				answerStyle.up = quesSkin.getDrawable("recuadro agua respuesta");
				answerStyle.down = quesSkin.getDrawable("recuadro agua respuesta presionado");
				answerStyle.checked = quesSkin.getDrawable("recuadro agua respuesta presionado");
				catLabel.setText("Agua");
				break;
			case EARTH:
				quesSkin.addRegions(EQAssets.quesEarthAtlas);
				categoryFace = new AnimatedImage(earthAnim);
				questionLabelBg.setDrawable(quesSkin, "recuadro mundo pregunta"); 
				answerStyle.up = quesSkin.getDrawable("recuadro mundo respuesta");
				answerStyle.down = quesSkin.getDrawable("recuadro mundo respuesta presionado");
				answerStyle.checked = quesSkin.getDrawable("recuadro mundo respuesta presionado");
				scale = 0.2f;
				catLabel.setText("Tierra");
				break;
			default:
				break;
		
		}
		
		answerHolders = new ArrayList<TextButton> ();
		
		for (int i = 0; i<4; i++) {
			
			final int k = i;
			TextButton tb = new TextButton(q.getAnswer(i),answerStyle);
			tb.setTransform(true);
			tb.getLabel().setWrap(true);
			tb.getLabelCell().pad(answerHeight*0.1f, holderWidth*0.025f, answerHeight*0.1f, holderWidth*0.025f);
			tb.setOrigin(holderWidth/2, answerHeight/2);
			
			tb.addListener(new ClickListener() {
				
				public void clicked(InputEvent event, float x, float y) {
					if(!match.isAnswerClicked()) {
						match.setAnswerClicked(true);
						match.onAnswerSelection(k);
					}
				}
			});
			

			answerHolders.add(tb);
		}
		
		
		faceWidth = categoryFace.getPrefWidth()*scale;
		faceHeight = categoryFace.getPrefHeight()*scale;

		categoryFace.setBounds(holderWidth-faceWidth*0.65f,questionHeight-faceHeight/2,faceWidth,faceHeight);
		
		questionHolder.addActor(questionLabelBg);
		questionHolder.addActor(questionLabel);
		questionHolder.addActor(categoryFace);
		
		questionHolder.setOrigin(holderWidth/2, questionHeight/2);
		questionHolder.setScale(0f);
		questionHolder.addAction(Actions.scaleTo(1f,1f,0.5f,Interpolation.swingOut));
		
		
		catLabelHolder.setScale(0f);
		catLabelHolder.addAction(Actions.scaleTo(1f,1f,0.5f,Interpolation.swingOut));
		
		
				
	}


	private void prepareActions() {
		//quesTable.setLayoutEnabled(false);
		int i =0;
		for (TextButton t : answerHolders) {
						
			t.setScale(0f);
			t.addAction(Actions.sequence(Actions.delay(0.1f*i),Actions.scaleTo(1f,1f,0.4f,Interpolation.swingOut)));
			i++;
		}
		
	}

	public void showAnswerAnimation(boolean answeredRight) {

		
		float popUpWidth = EQGlobals.w*0.4f;
		if (popUpWidth > 600) popUpWidth = 600;
		float popUpHeight = popUpWidth;
		
		Image popUp;
		if (answeredRight) popUp = correctPopUp;
		else popUp = incorrectPopUp;
				
		popUp.setBounds(EQGlobals.center.x-popUpWidth/2, EQGlobals.center.y*1.5f-popUpHeight/2, popUpWidth, popUpHeight );
		popUp.setOrigin(popUpWidth/2, popUpHeight/2);
		popUp.setScale(0);
		
		popUp.addAction(Actions.sequence(Actions.scaleBy(1,1,popUpTime,Interpolation.swingOut),Actions.delay(popUpDelay), Actions.run(new Runnable() {
			public void run() {
				stage.addAction( Actions.run(new Runnable() {	//Actions.sequence(Actions.moveBy(100, 0, 0.1f),
					public void run() {
						
						match.backToMainState();
					}
				}));
			}
		})));
		
		
		stage.addActor(popUp);
		
	}
	

	public void highlightRightAnswer(int selectedAnswer) {

		float s =0.3f;
		float t =0.3f;
		
		answerHolders.get(selectedAnswer).addAction(Actions.sequence(Actions.scaleBy(s, s, t,Interpolation.exp5In),Actions.scaleBy(-s,-s,t,Interpolation.exp5Out)));
		answerHolders.get(selectedAnswer).setColor(new Color(0f, 0.8f, 0f, 1f));
		
	}
	
	public void highlightWrongAnswer(int selectedAnswer) {
		
		answerHolders.get(selectedAnswer).setColor(new Color(0.8f, 0f, 0f, 1f));
		
	}
	
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void updateQuestion(EQQuestion currentQuestion) {


		Gdx.input.setInputProcessor(stage);
		this.q=currentQuestion;
		
		if (q.isHard()) {
			setHardIndicator();
		} else {
			removeHardIndicator();
		}
		
	}

	private void removeHardIndicator() {

		bg.setColor(1f,1f,1f,1f);
		
	}

	private void setHardIndicator() {
		
		bg.setColor(1f,0f,0.5f,1f);
		
	}
	
	public void createFaceAnims() {
		
		float frameDur = 0.05f;
		
		biodivAnim = new BlinkAnimation(frameDur, EQAssets.facesAtlas.findRegion("biodiv D"),
				  EQAssets.facesAtlas.findRegion("biodiv E"),
				  EQAssets.facesAtlas.findRegion("biodiv F"));
		
		waterAnim = new BlinkAnimation(frameDur, EQAssets.facesAtlas.findRegion("water D"),
			  	  EQAssets.facesAtlas.findRegion("water E"),
			  	  EQAssets.facesAtlas.findRegion("water F"));
		
		consumAnim = new BlinkAnimation(frameDur, EQAssets.facesAtlas.findRegion("consum D"),
			  	  EQAssets.facesAtlas.findRegion("consum E"),
			  	  EQAssets.facesAtlas.findRegion("consum F"));
		
		energyAnim = new BlinkAnimation(frameDur, EQAssets.facesAtlas.findRegion("energy D"),
			  	  EQAssets.facesAtlas.findRegion("energy E"),
			  	  EQAssets.facesAtlas.findRegion("energy F"));
		
		mobilAnim = new BlinkAnimation(frameDur, EQAssets.facesAtlas.findRegion("mobil D"),
			  	  EQAssets.facesAtlas.findRegion("mobil E"),
			  	  EQAssets.facesAtlas.findRegion("mobil F"));
		
		earthAnim = new BlinkAnimation(frameDur, EQAssets.earthAtlas.findRegion("earthOpenSE"),
			  	  EQAssets.earthAtlas.findRegion("earthSemiClosed"),
			  	  EQAssets.earthAtlas.findRegion("earthClosed"));
		
	}
	

}
