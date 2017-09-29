package com.foscusgames.ecoquishandlers;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.foscusgames.ecoquis.EQGlobals.Category;
import com.foscusgames.ecoquis.EQGlobals;
import com.foscusgames.ecoquis.EQQuestion;

public class EQQuestionHandler {
	
	ArrayList<EQQuestion> mobilEasyQuestions, energyEasyQuestions, biodivEasyQuestions, consumEasyQuestions, waterEasyQuestions, earthEasyQuestions;
	ArrayList<EQQuestion> mobilHardQuestions, energyHardQuestions, biodivHardQuestions, consumHardQuestions, waterHardQuestions, cuadernoQuestions;
	
	ArrayList<Integer> mobilEasyQIL, energyEasyQIL, biodivEasyQIL, consumEasyQIL, waterEasyQIL, earthQIL;
	ArrayList<Integer> mobilHardQIL, energyHardQIL, biodivHardQIL, consumHardQIL, waterHardQIL, cuadernoQIL;	
	
	private int mobilEasyQuesNum = 60;
	private int mobilHardQuesNum = 30;
	private int energyEasyQuesNum = 62;
	private int energyHardQuesNum = 30;
	private int biodivEasyQuesNum = 64;
	private int biodivHardQuesNum = 40;
	private int consumEasyQuesNum = 60;
	private int consumHardQuesNum = 35;
	private int waterEasyQuesNum = 60;
	private int waterHardQuesNum = 30;
	private int earthQuesNum = 52;
	
	private String encoding = EQGlobals.encoding;

	
	public void loadQuestions() throws Exception {
		// TIERRA: 52 preguntas
		// ENERGIA: 62 pregs
		// BIODIV: 64 pregs. El resto 60
		
		Gdx.app.log("EQQuestionHandler", "!!!!! LOADING QUESTIONS WITH ENCODING: "+encoding+" !!!!!");
		
		mobilEasyQuestions = new ArrayList<EQQuestion>();
		FileHandle mobilEasyFile = Gdx.files.internal("questions/"+encoding+" Encoding/mobilEasyQuestions.txt");	
		loadCategoryQuestions(mobilEasyQuesNum,mobilEasyFile,mobilEasyQuestions,Category.MOBIL,false);
		
		mobilHardQuestions = new ArrayList<EQQuestion>();
		FileHandle mobilHardFile = Gdx.files.internal("questions/"+encoding+" Encoding/mobilHardQuestions.txt");	
		loadCategoryQuestions(mobilHardQuesNum,mobilHardFile,mobilHardQuestions,Category.MOBIL,true);
		

		energyEasyQuestions = new ArrayList<EQQuestion>();
		FileHandle energyEasyFile = Gdx.files.internal("questions/"+encoding+" Encoding/energyEasyQuestions.txt");	
		loadCategoryQuestions(energyEasyQuesNum,energyEasyFile,energyEasyQuestions,Category.ENERGY,false);
		
		energyHardQuestions = new ArrayList<EQQuestion>();
		FileHandle energyHardFile = Gdx.files.internal("questions/"+encoding+" Encoding/energyHardQuestions.txt");	
		loadCategoryQuestions(energyHardQuesNum,energyHardFile,energyHardQuestions,Category.ENERGY,true);
		

		biodivEasyQuestions = new ArrayList<EQQuestion>();
		FileHandle biodivEasyFile = Gdx.files.internal("questions/"+encoding+" Encoding/biodivEasyQuestions.txt");	
		loadCategoryQuestions(biodivEasyQuesNum,biodivEasyFile,biodivEasyQuestions,Category.BIODIV,false);
		
		biodivHardQuestions = new ArrayList<EQQuestion>();
		FileHandle biodivHardFile = Gdx.files.internal("questions/"+encoding+" Encoding/biodivHardQuestions.txt");	
		loadCategoryQuestions(biodivHardQuesNum,biodivHardFile,biodivHardQuestions,Category.BIODIV,true);
		

		consumEasyQuestions = new ArrayList<EQQuestion>();
		FileHandle consumEasyFile = Gdx.files.internal("questions/"+encoding+" Encoding/consumEasyQuestions.txt");	
		loadCategoryQuestions(consumEasyQuesNum,consumEasyFile,consumEasyQuestions,Category.CONSUM,false);
		
		consumHardQuestions = new ArrayList<EQQuestion>();
		FileHandle consumHardFile = Gdx.files.internal("questions/"+encoding+" Encoding/consumHardQuestions.txt");	
		loadCategoryQuestions(consumHardQuesNum,consumHardFile,consumHardQuestions,Category.CONSUM,true);
		

		waterEasyQuestions = new ArrayList<EQQuestion>();
		FileHandle waterEasyFile = Gdx.files.internal("questions/"+encoding+" Encoding/waterEasyQuestions.txt");	
		loadCategoryQuestions(waterEasyQuesNum,waterEasyFile,waterEasyQuestions,Category.WATER,false);
		
		waterHardQuestions = new ArrayList<EQQuestion>();
		FileHandle waterHardFile = Gdx.files.internal("questions/"+encoding+" Encoding/waterHardQuestions.txt");	
		loadCategoryQuestions(waterHardQuesNum,waterHardFile,waterHardQuestions,Category.WATER,true);
		
		

		earthEasyQuestions = new ArrayList<EQQuestion>();
		FileHandle earthEasyFile = Gdx.files.internal("questions/"+encoding+" Encoding/earthQuestions.txt");	
		loadCategoryQuestions(earthQuesNum,earthEasyFile,earthEasyQuestions,Category.EARTH,false);
	
		
//		cuadernoQuestions = new ArrayList<EQQuestion>();
//		FileHandle cuadernoFile = Gdx.files.internal("questions/cuadernoQuestions.txt");	
//		loadCategoryQuestions(28,cuadernoFile,cuadernoQuestions,Category.CUADERNO,false);
		
		
		generateQuestionIndexLists();
		
	}
	
	public void generateQuestionIndexLists() {
		
		mobilEasyQIL = generateIntList(mobilEasyQuesNum);		
		energyEasyQIL = generateIntList(energyEasyQuesNum);	
		biodivEasyQIL = generateIntList(biodivEasyQuesNum);	
		consumEasyQIL = generateIntList(consumEasyQuesNum);	
		waterEasyQIL = generateIntList(waterEasyQuesNum);
		
		mobilHardQIL = generateIntList(mobilHardQuesNum);		
		energyHardQIL = generateIntList(energyHardQuesNum);	
		biodivHardQIL = generateIntList(biodivHardQuesNum);	
		consumHardQIL = generateIntList(consumHardQuesNum);	
		waterHardQIL = generateIntList(waterHardQuesNum);
		
		earthQIL = generateIntList(earthQuesNum);	
	}
	
	public ArrayList<Integer> generateIntList(int n) {
		
		ArrayList<Integer> list = new ArrayList<Integer> ();
		
		for(int i=0; i<n; i++) {
			list.add(i);
		}
		
		return list;
	}
	
	public void loadCategoryQuestions(int numQuestions, FileHandle file, ArrayList<EQQuestion> list, Category cat, boolean isHard) throws Exception {
				
		String fileAsString = file.readString();
		String[] lines = fileAsString.split("\n");
		
		
		
		int k=6;
		
		for (int i=0;i<numQuestions;i++) {

			fileAsString.split("\n");
			String q1 = lines[i*k+0];
			String a1 = lines[i*k+1];
			String a2 = lines[i*k+2];
			String a3 = lines[i*k+3];
			String a4 = lines[i*k+4];
						
			int answer = Integer.parseInt(lines[i*k+5]) - 1;
			if (answer < 0 || answer > 3)
				throw new Exception();
			
			
			list.add(new EQQuestion(q1,a1,a2,a3,a4,answer,cat,isHard));
			
		}
		
		
		
	}
		
	public EQQuestion getNewQuestion (int cat, boolean hard) {
		
		
	return getNewQuestion(transformIntToCat(cat),hard);
	
	}
	
	private int getAndRemoveRandomInt(ArrayList<Integer> list) {

		
		
		int index,index2;
		Random r = new Random();
		
		index = r.nextInt(list.size());
		index2 = list.remove(index);
		
		
		Gdx.app.log("EQQuestionHandler", "QIL index= "+index+"\nCorresponding question number= "+index2+"\nNumber of questions left: "+list.size());
		
		return index2;
	}
	
	public EQQuestion getNewQuestion( Category cat, boolean hard ) {
		
		
		if (!hard) {
			switch(cat) {
			
				case MOBIL:
					if (mobilEasyQIL.isEmpty()) {
						mobilEasyQIL =generateIntList(mobilEasyQuesNum);
					}
					return mobilEasyQuestions.get(getAndRemoveRandomInt(mobilEasyQIL));
				case ENERGY:
					if (energyEasyQIL.isEmpty()) {
						energyEasyQIL =generateIntList(energyEasyQuesNum);
					}
					return energyEasyQuestions.get(getAndRemoveRandomInt(energyEasyQIL));
				case BIODIV:
					if (biodivEasyQIL.isEmpty()) {
						biodivEasyQIL =generateIntList(biodivEasyQuesNum);
					}
					return biodivEasyQuestions.get(getAndRemoveRandomInt(biodivEasyQIL));
				case CONSUM:
					if (consumEasyQIL.isEmpty()) {
						consumEasyQIL =generateIntList(consumEasyQuesNum);
					}
					return consumEasyQuestions.get(getAndRemoveRandomInt(consumEasyQIL));
				case WATER:
					if (waterEasyQIL.isEmpty()) {
						waterEasyQIL =generateIntList(waterEasyQuesNum);
					}
					return waterEasyQuestions.get(getAndRemoveRandomInt(waterEasyQIL));
				case EARTH:
					if (earthQIL.isEmpty()) {
						earthQIL =generateIntList(earthQuesNum);
					}
					return earthEasyQuestions.get(getAndRemoveRandomInt(earthQIL));	//20
				default:
					return null;
				
			}
		} else {
			switch(cat) {
				
				case MOBIL:
					if (mobilHardQIL.isEmpty()) {
						mobilHardQIL =generateIntList(mobilHardQuesNum);
					}
					return mobilHardQuestions.get(getAndRemoveRandomInt(mobilHardQIL));
				case ENERGY:
					if (energyHardQIL.isEmpty()) {
						energyHardQIL =generateIntList(energyHardQuesNum);
					}
					return energyHardQuestions.get(getAndRemoveRandomInt(energyHardQIL));
				case BIODIV:
					if (biodivHardQIL.isEmpty()) {
						biodivHardQIL =generateIntList(biodivHardQuesNum);
					}
					return biodivHardQuestions.get(getAndRemoveRandomInt(biodivHardQIL));
				case CONSUM:
					if (consumHardQIL.isEmpty()) {
						consumHardQIL =generateIntList(consumHardQuesNum);
					}
					return consumHardQuestions.get(getAndRemoveRandomInt(consumHardQIL));
				case WATER:
					if (waterHardQIL.isEmpty()) {
						waterHardQIL =generateIntList(waterHardQuesNum);
					}
					return waterHardQuestions.get(getAndRemoveRandomInt(waterHardQIL));
				case EARTH:
					if (earthQIL.isEmpty()) {
						earthQIL =generateIntList(earthQuesNum);
					}
					return earthEasyQuestions.get(getAndRemoveRandomInt(earthQIL));
				default:
					return null;
				
			}
		}
		
		
		
	}

	public int transformCatToInt(Category cat) {
		switch(cat) {
		
		case MOBIL:
			return 0;
		case ENERGY:
			return 1;
		case BIODIV:
			return 2;
		case CONSUM:
			return 3;
		case WATER:
			return 4;
		case EARTH:
			return 5;
		default:
			return 0;
		
		}
	}

	public Category transformIntToCat(int cat) {
		

		Category c;
		
		switch (cat) {
		case 0:
			c=Category.MOBIL;
			break;
		case 1:
			c=Category.ENERGY;
			break;
		case 2:
			c=Category.BIODIV;
			break;
		case 3:
			c=Category.CONSUM;
			break;
		case 4:
			c=Category.WATER;
			break;
		default:
			c=Category.EARTH;
			break;
	}
			
		return c;	
			
	}

}
