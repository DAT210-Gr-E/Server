package gettere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			sortByLikes(pictures);					//Fancy sorteringsalgoritme. Til nŒ sorterer den kun pŒ likes. IdŽer?
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
	
	public List<Picture> reduceList(List<Picture> listOfPictures){
		if(listOfPictures.size() <= 100){
			return listOfPictures;
		}
		for (int i = 0; i < listOfPictures.size(); i++) {
			if(listOfPictures.size()>100){
				listOfPictures.remove(i);
			}
		}
		return listOfPictures;
	}

	public static Comparator<Picture> COMPARATOR = new Comparator<Picture>(){
		public int compare(Picture o1, Picture o2){
			return o1.likes - o2.likes;
		}
	};

	public static List<Picture> sortByLikes(List<Picture> listOfPictures){
		Collections.sort(listOfPictures, COMPARATOR);
		return listOfPictures;
	}
}