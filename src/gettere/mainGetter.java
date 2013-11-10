package gettere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.Picture;

public class mainGetter {
	public String[] tags;
	List<Picture> tmp;
	List<Picture> pictures = new ArrayList<Picture>();
	boolean loop;
	InstagramGetter instaGetter = new InstagramGetter();
	// DatabaseAdder adder = new DatabaseAdder();	TRENGER KODE FRA MORTEN 
	
	private void getter() throws IOException{		
		while(loop) {
			// tags = adder.getTagsFromDatabase(); 	TRENGER KODE FRA MORTEN HER
			for (int i = 0; i < tags.length; i++){
				tmp = instaGetter.getPictureList(tags[i]);
				for (int j = 0; j < tmp.size(); j++) pictures.add(tmp.get(j));
			}
			// sort(pictures)						Fancy algoritme for sortering av bilder trengs her. Noen som melder seg frivillig?
			// adder.addToDatabase(pictures);		TRENGER KODE FRA MORTEN HER
		}
	}
	
	public void start() throws IOException{
		loop = true;
		getter();
	}
	
	public void stopp(){
		loop = false;
	}
}