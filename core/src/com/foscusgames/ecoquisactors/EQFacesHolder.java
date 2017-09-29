package com.foscusgames.ecoquisactors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.foscusgames.ecoquis.EQAssets;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQGlobals.Category;
import com.foscusgames.ecoquis.EQMatch;
import com.foscusgames.ecoquis.EQMatch.MatchState;

public class EQFacesHolder extends WidgetGroup {

	EQFace mobilFace, energyFace, biodivFace, consumFace, waterFace;
	Table table;
	ArrayList<EQFace> faces;
	float width, height, x, y;
	private EQMatch match;
	RepeatAction[] faceEPBAction = {null,null,null,null,null};
	private int[] currentPoints = {0,0,0,0,0};
	private boolean[] actionAddedFlag = {false,false,false,false,false};
	private Image whiteBar;
	
	
	public EQFacesHolder (EQMatch match, float x, float y, float w, float h) {
		
		super();
		//setBackground(EQAssets.getDrawable(EQAssets.questionHolder));
		this.match = match;
		Skin fS = new Skin();
		fS.addRegions(EQAssets.facesAtlas);
		

		float faceW = EQGlobals.w*0.17f;
		float faceH = EQGlobals.w*0.17f;	
		
		mobilFace = new EQFace(fS.getDrawable("mobil A"),fS.getDrawable("mobil B"), fS.getDrawable("mobil C"), fS.getDrawable("mobil D"), EQAssets.iconsAtlas.findRegion("mobilIcon"),Category.MOBIL, this,faceW,faceH);
		energyFace = new EQFace(fS.getDrawable("energy A"),fS.getDrawable("energy B"), fS.getDrawable("energy C"), fS.getDrawable("energy D"), EQAssets.iconsAtlas.findRegion("energyIcon"),Category.ENERGY, this,faceW,faceH);
		biodivFace = new EQFace(fS.getDrawable("biodiv A"),fS.getDrawable("biodiv B"), fS.getDrawable("biodiv C"), fS.getDrawable("biodiv D"), EQAssets.iconsAtlas.findRegion("biodivIcon"),Category.BIODIV, this,faceW,faceH);
		consumFace = new EQFace(fS.getDrawable("consum A"),fS.getDrawable("consum B"), fS.getDrawable("consum C"), fS.getDrawable("consum D"), EQAssets.iconsAtlas.findRegion("consumIcon"),Category.CONSUM, this,faceW,faceH);
		waterFace = new EQFace(fS.getDrawable("water A"),fS.getDrawable("water B"), fS.getDrawable("water C"), fS.getDrawable("water D"),EQAssets.iconsAtlas.findRegion("waterIcon"), Category.WATER, this,faceW,faceH);
		
		table = new Table();
		table.setFillParent(true);
		
		table.add(mobilFace).size(faceW,faceH).pad(EQGlobals.w*0.01f);
		table.add(energyFace).size(faceW,faceH).pad(EQGlobals.w*0.01f);
		table.add(waterFace).size(faceW,faceH).pad(EQGlobals.w*0.01f);
		table.add(consumFace).size(faceW,faceH).pad(EQGlobals.w*0.01f);
		table.add(biodivFace).size(faceW,faceH).pad(EQGlobals.w*0.01f);
		
		
		//table.debug();
		
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		
		this.setBounds(x, y,width, height);
		
		whiteBar = new Image(EQAssets.whiteOverlay);
		whiteBar.setColor(1,1,1,0.7f);
		whiteBar.setBounds(0, this.height*0.4f, width, this.height*0.2f);
		
		this.addActor(whiteBar);
		this.addActor(table);
		
		faces = new ArrayList<EQFace>();
		
		faces.add(mobilFace);
		faces.add(energyFace);
		faces.add(biodivFace);
		faces.add(consumFace);
		faces.add(waterFace);
		

		
		//Actions.forever(Actions.sequence(Actions.scaleBy(s, s, t,Interpolation.swingOut),Actions.scaleBy(-s, -s, t,Interpolation.swingIn)));
	}


	public void onResize(int width, int height) {

		this.setBounds(0, y*height/EQGlobals.h ,width, this.height);
		whiteBar.setBounds(0, this.height*0.4f, width, this.height*0.2f);
		
	}


	public void update(int[] points) {

		float s = 0.4f;
		float t = 0.3f;
		
		
		for (int i = 0; i<EQGlobals.numberOfCategories; i++) {
			
			if (currentPoints[i]< points[i]) {
				faces.get(i).setCorrespondingImage(points[i]);
				faces.get(i).getButton().addAction(Actions.sequence(Actions.scaleBy(s, s, t/2,Interpolation.exp5In),Actions.scaleBy(-s, -s, t,Interpolation.exp5Out)));
				if(points[i]==EQGlobals.numberOfTiers) {
					//EQSounds.faceCompleted.play();
					faces.get(i).explode();
					match.onFaceCompletion();
				} else {
					//EQSounds.faceIncreased.play();
				}
			}
			
			currentPoints[i]=points[i];
		}

		
	}

	public void updateEPBActions (boolean EPBFlag) {

		for (int i = 0; i<EQGlobals.numberOfCategories; i++) {
			if (currentPoints[i]<3 && EPBFlag && !faces.get(i).getButton().hasActions()) {
				
				Gdx.app.log("FacesHolder", "Scaling corresponding faces");
				
				actionAddedFlag [i]=true;
				faceEPBAction[i] = new EQFaceEPBAction();
				faces.get(i).getButton().addAction(faceEPBAction[i]);
				
			} else if ((currentPoints[i]==3 || !EPBFlag) && actionAddedFlag[i]) {
				
				Gdx.app.log("FacesHolder", "UNSCALING faces");
				
				faces.get(i).getButton().removeAction(faceEPBAction[i]);
				actionAddedFlag [i]=false;
				faces.get(i).resetButtonScale();
				
			}
		}
	}

	public void onFaceClick(Category cat) {
		if (match.getState()!=MatchState.SPINNING)
			match.onFaceClick(cat);
		
	}


	
}
