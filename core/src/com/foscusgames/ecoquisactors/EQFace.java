package com.foscusgames.ecoquisactors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.foscusgames.ecoquis.EQGlobals.Category;
import com.foscusgames.ecoquis.EQSounds;

/**
 * LibGDX Group of Widgets representing the Faces, or characters of the game.
 * @author Camilo
 *
 */
public class EQFace extends WidgetGroup {
	
	private Array<Drawable> images;
	ImageButton button;
	Category cat;
	EQFacesHolder faceHolder;
	private ParticleExplosion particleExplosion;
	private boolean exploded =false;
	
	
	public EQFace (Drawable empty, Drawable oneThird, Drawable twoThirds, Drawable full, AtlasRegion icon, Category c, EQFacesHolder fH, float w, float h) {
		
		button = new ImageButton(empty);
		
		button.setTransform(true);
		button.setOrigin(w/2,h/2);
		button.setFillParent(true);
		
		images = new Array<Drawable>();
		images.add(empty);
		images.add(oneThird);
		images.add(twoThirds);
		images.add(full);
		
		this.cat = c;
		this.faceHolder = fH;
		this.particleExplosion = new ParticleExplosion(icon, 0.3f);
		this.particleExplosion.setPosition(w/2,h/2);
		
		
		button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				faceHolder.onFaceClick(cat);
				EQSounds.playCategSound(cat);;
			}
		});
		

		this.addActor(button);
		this.addActor(particleExplosion);
		this.setSize(w, h);
		
		
	}
	
//	
//	public void increaseCompletion() {
//		
//		completion +=1;
//		
//		if (completion > 3) completion = 3;
//		
//		getStyle().imageUp = images.get(completion);
//	
//	}

	public void setCorrespondingImage(int i) {

		button.getStyle().imageUp = images.get(i);
		
	}


	public void explode() {

		
		if(!exploded ) {
			exploded = true;
			particleExplosion.explode();
			
			this.addAction(Actions.run(new Runnable() {

				@Override
				public void run() {
					EQSounds.playSound(EQSounds.faceCompleted);
					
				}
				
			}));
			
		}
		
	}

	public Button getButton() {
		return button;
	}

	public void resetButtonScale() {
		button.setScale(1f);
		
	}


	

}
