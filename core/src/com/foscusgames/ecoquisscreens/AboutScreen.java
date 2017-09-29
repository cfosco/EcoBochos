package com.foscusgames.ecoquisscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGame;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQSounds;

public class AboutScreen extends EQScreen {

	private Label titleLabel,cuadernoTextLabel, howToPlayTextLabel;
	private ScrollPane scrollPane;
	private Button backToMenuButton;
	private TextButton cuadernoNButton, cuadernoDButton;
	private EQGame game;
	private Image bg,titleHolder;
	private Table table;
	
    public AboutScreen(EQGame game) {
    	
    	super();
    	this.game = game;
    	
    }

	@Override
	public void show() {

		LabelStyle titleLS =  new LabelStyle(EQAssets.mainMenuFont, new Color(0f,0.2f,0.5f,1f));
		LabelStyle textLS =  new LabelStyle(EQAssets.answerFont, new Color(0f,0.2f,0.5f,1f));
		float bw = EQGlobals.w*0.11f;
		float bh = bw;
		float margin = EQGlobals.w*0.03f;
		float scrollPaneW = EQGlobals.w-2*margin;

		titleHolder = new Image(EQAssets.whiteOverlay);
		titleHolder.setColor(1f,1f,1f,0.7f);
		titleLabel = new Label("Sobre el juego",titleLS);
		titleLabel.setAlignment(Align.center);
		

		cuadernoTextLabel = new Label(getCuadernoText(),textLS);
		cuadernoTextLabel.setWrap(true);
		
		howToPlayTextLabel = new Label(getHTPText(),textLS);
		howToPlayTextLabel.setWrap(true);
		
		
		createButtons();
		
		table = new Table();
		//table.setFillParent(true);
		table.add(cuadernoTextLabel).size(scrollPaneW, EQGlobals.h*0.21f);
		table.row();
		table.add(cuadernoNButton).pad(margin).size(scrollPaneW*0.8f, EQGlobals.h*0.09f);
		table.row();
		table.add(cuadernoDButton).pad(margin).size(scrollPaneW*0.8f, EQGlobals.h*0.09f);
		table.row();
		table.add(howToPlayTextLabel).size(scrollPaneW, EQGlobals.h*1.3f);
		//table.debug();
		
		scrollPane = new ScrollPane(table);
		scrollPane.setBounds(margin, 0, scrollPaneW, EQGlobals.h*0.9f);
		
		Skin skin = new Skin();
		skin.addRegions(EQAssets.buttonAtlas);
		backToMenuButton = new ImageButton(skin.getDrawable("boton home"),skin.getDrawable("boton home presionado"));
		backToMenuButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				EQSounds.playSound(EQSounds.buttonClick);
				game.setScreen(new MainMenuScreen(game));
			}
			
		});
		backToMenuButton.setBounds(EQGlobals.h*0.02f,EQGlobals.h*0.95f-bh/2, bw, bh);
		
		bg = new Image(EQAssets.mainMenuBg);
		
		stage.addActor(bg);
		stage.addActor(titleHolder);
		stage.addActor(titleLabel);
		stage.addActor(scrollPane);
		stage.addActor(backToMenuButton);
		
		
	}
	
	private String getHTPText() {
		return "Este juego tiene dos modalidades:\n\n"
				+"DESAFIO: tenés que conseguir los 5 personajes temáticos: Agua, Biodiversidad, Energía, Consumo y Movilidad. Para ello, deberás responder 3 preguntas correctas o sumar preguntas correctas de otra categoría para obtener el bonus que te permita elegir un personaje. Una vez logrados, y para terminar la partida, accederás al personaje Tierra con preguntas más generales sobre Ecología. Tu puntaje se calculará en función de la cantidad de preguntas correctas e incorrectas y del tiempo que te llevó la partida. ¡OJO! Hay preguntas difíciles que te darán más puntaje.\n\n"
				+"TIEMPO: esta modalidad, para jugadores más experimentados, te ofrece dos minutos para contestar la mayor cantidad de preguntas que puedas. Si contestaste 4 seguidas bien, se activará un combo que te suma tiempo. Si completás un personaje temático (3 preguntas correctas), podés responder una pregunta de la categoría Tierra con más puntaje.\n\n"
				+"Un Leaderboard recoge tu puntaje y el de tus amigos para que puedan compartir sus conocimientos ambientales. Con cada puntaje mayor, irás obteniendo distintos logros para convertirte en un EcoBocho.\n\n¡A ver quién llega más lejos!";
		
	}

	private String getCuadernoText() {
		return "Si querés ser más EcoBocho, encontrarás todas las respuestas consultando los Cuadernos de Educación Ambiental para Todos disponibles en la web. ¡Clickeá los botones para acceder!";

	}


	
	@Override
	public void resize(int width, int height) {
		
		super.resize(width, height);	
		
		bg.setBounds(0, 0, width, height);	
		titleHolder.setBounds(0, height*0.9f, width, height*0.1f);
		titleLabel.setBounds(0, height*0.9f, width, height*0.1f);
	}
	
	public void createButtons() {

		Skin skin = new Skin();
		skin.addRegions(EQAssets.quesBiodivAtlas);
		
		TextButtonStyle playButtonStyle = new TextButtonStyle();
		playButtonStyle.font = EQAssets.mainMenuFont;
		playButtonStyle.fontColor = new Color(0f,0f,0f,1f);
		playButtonStyle.up = skin.getDrawable("recuadro biodiversidad respuesta");
		playButtonStyle.down = skin.getDrawable("recuadro biodiversidad respuesta presionado");
		playButtonStyle.checked = skin.getDrawable("recuadro biodiversidad respuesta presionado");
		
		cuadernoNButton = new TextButton("Cuaderno Alumnos",playButtonStyle);
		cuadernoDButton = new TextButton("Cuaderno Docentes",playButtonStyle);

		
		cuadernoNButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				EQSounds.playSound(EQSounds.buttonClick);
				Gdx.net.openURI("https://asp-es.secure-zone.net/v2/index.jsp?id=172/14743/33465&lng=es");
			}
		});
		
		cuadernoDButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				EQSounds.playSound(EQSounds.buttonClick);
				Gdx.net.openURI("http://asp-es.secure-zone.net/v2/index.jsp?id=172/14863/33571&lng=es");

			}
		});
		
		
	}
	
}
