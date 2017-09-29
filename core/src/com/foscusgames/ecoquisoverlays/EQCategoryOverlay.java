package com.foscusgames.ecoquisoverlays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQGlobals.Category;
import com.foscusgames.ecoquisactors.BlinkAnimation;
import com.foscusgames.ecoquisscreens.AnimatedImage;
import com.foscusgames.ecoquis.EQMatch;
import com.foscusgames.ecoquis.EQSounds;

public class EQCategoryOverlay extends EQBlackOverlay {
	

	private Image catImage;
	private Label catLabel, textLabel;
	private TextButton playButton;
	private Table table;
	private EQMatch match;
	private Image star;
	private WidgetGroup catGroup;
	private float scale;
	private TextButtonStyle playButtonStyle;
	private BlinkAnimation biodivAnim,waterAnim,consumAnim,energyAnim,mobilAnim;
	private BlinkAnimation earthAnim;
	
	public EQCategoryOverlay (EQMatch m, Category cat, boolean hard) {
		
		super();
		
		float w = EQGlobals.w*0.85f;
		float h = EQGlobals.h*0.45f;
		scale = EQGlobals.w/600f;
		float margin = EQGlobals.w*0.03f;
		
		match =m;
		table = new Table();
		
		LabelStyle catStyle = new LabelStyle(EQAssets.mainMenuFont, new Color(1,1,1,1));
		catLabel = new Label(getCatText(cat), catStyle);
		catLabel.setAlignment(Align.center);

		createFaceAnims();
		prepareButtons();
		prepareCatImage(cat);
		
		star = new Image(EQAssets.miscAtlas.findRegion("Star"));
		catGroup = new WidgetGroup();
	

		
		LabelStyle textStyle = new LabelStyle(EQAssets.questionFont, new Color(1,1,1,1));
		
		if (hard) {
			textLabel = new Label("Pregunta Difícil. ¡Sumás doble si acertás!", textStyle);
			catGroup.addActor(star);
			catGroup.addActor(catImage);
	
		}else {
			textLabel = new Label("¡Si acertás sumás 10 puntos, sino restás 5!", textStyle);
			catGroup.addActor(catImage);
		}
		textLabel.setAlignment(Align.center);
		textLabel.setWrap(true);
		
		catImage.setWidth(catImage.getPrefWidth()*scale);
		catImage.setHeight(catImage.getPrefHeight()*scale);
		star.setWidth(star.getPrefWidth()*scale*1.1f);
		star.setHeight(star.getPrefHeight()*scale*1.1f);
		star.setPosition(w/2-star.getWidth()/2, h/2-star.getHeight()/2);
		catImage.setPosition(w/2-catImage.getWidth()/2, h/2-catImage.getHeight()/2);

		catGroup.setFillParent(true);
		catGroup.addAction(Actions.forever(Actions.sequence(Actions.moveBy(0, h*0.05f,0.5f,Interpolation.sine),Actions.moveBy(0, -h*0.05f,0.5f,Interpolation.sine))));
		
		
		table.add(catGroup).size(w, h).pad(margin);
		table.row();
		table.add(catLabel).width(w).pad(margin);
		table.row();
		table.add(textLabel).width(w).padBottom(margin);
		table.row();
		table.add(playButton).size(w*0.8f,h*0.2f).padTop(2*margin);
		
		table.setFillParent(true);
		//table.debug();
		this.addActor(table);
		
		
				
	}
	

	private void createFaceAnims() {
		
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


	private String getCatText(Category cat) {
		
		switch(cat) {
		case BIODIV:
			return "Biodiversidad";
		case CONSUM:
			return "Consumo";
		case CUADERNO:
			return "Cuaderno";
		case EARTH:
			return "Tierra";
		case ENERGY:
			return "Energía";
		case MOBIL:
			return "Movilidad";
		case WATER:
			return "Agua";
		default:
			return "";
		
		}
		
	}


	private void prepareCatImage(Category cat) {
		

		Skin skin = new Skin();
		
		switch(cat) {
		case BIODIV:
			skin.addRegions(EQAssets.quesBiodivAtlas);
			catImage = new AnimatedImage(biodivAnim);
			playButtonStyle.up = skin.getDrawable("recuadro biodiversidad respuesta");
			playButtonStyle.down = skin.getDrawable("recuadro biodiversidad respuesta presionado");
			playButtonStyle.checked = skin.getDrawable("recuadro biodiversidad respuesta presionado");
			break;
		case CONSUM:
			skin.addRegions(EQAssets.quesConsumAtlas);
			catImage = new AnimatedImage(consumAnim);
			playButtonStyle.up = skin.getDrawable("recuadro consumo respuesta");
			playButtonStyle.down = skin.getDrawable("recuadro consumo respuesta presionado");
			playButtonStyle.checked = skin.getDrawable("recuadro consumo respuesta presionado");
			break;
		case ENERGY:
			skin.addRegions(EQAssets.quesEnergyAtlas);
			catImage = new AnimatedImage(energyAnim);
			playButtonStyle.up = skin.getDrawable("recuadro energia respuesta");
			playButtonStyle.down = skin.getDrawable("recuadro energia respuesta presionado");
			playButtonStyle.checked = skin.getDrawable("recuadro energia respuesta presionado");
			break;
		case MOBIL:
			skin.addRegions(EQAssets.quesMobilAtlas);
			catImage = new AnimatedImage(mobilAnim);
			playButtonStyle.up = skin.getDrawable("recuadro movilidad respuesta");
			playButtonStyle.down = skin.getDrawable("recuadro movilidad respuesta presionado");
			playButtonStyle.checked = skin.getDrawable("recuadro movilidad respuesta presionado");
			break;
		case WATER:
			skin.addRegions(EQAssets.quesWaterAtlas);
			catImage = new AnimatedImage(waterAnim);
			playButtonStyle.up = skin.getDrawable("recuadro agua respuesta");
			playButtonStyle.down = skin.getDrawable("recuadro agua respuesta presionado");
			playButtonStyle.checked = skin.getDrawable("recuadro agua respuesta presionado");
			break;
		case EARTH:
			skin.addRegions(EQAssets.quesEarthAtlas);
			catImage = new AnimatedImage(earthAnim);
			playButtonStyle.up = skin.getDrawable("recuadro mundo respuesta");
			playButtonStyle.down = skin.getDrawable("recuadro mundo respuesta presionado");
			playButtonStyle.checked = skin.getDrawable("recuadro mundo respuesta presionado");
			scale = scale/1.5f;
			break;
		default:
			break;
	
		}
		
	}

	public void prepareButtons() {
		

		playButtonStyle = new TextButtonStyle();
		playButtonStyle.font = EQAssets.mainMenuFont;
		playButtonStyle.fontColor = new Color(0f,0f,0f,1f);
		
		playButton = new TextButton("Jugar",playButtonStyle);

		
		playButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {

				EQSounds.playSound(EQSounds.playQuestion);
				removeOverlay();
				match.setQuesScreen();
				
				
			}
			
		});
		
	}




}
