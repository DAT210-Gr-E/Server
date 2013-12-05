package gettere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import databaseKommunikasjon.DatabaseMetoder;

import server.Picture;

public class mainGetter {
	public ArrayList<String> tags;
	List<Picture> tmp;
	List<Picture> pictures = new ArrayList<Picture>();
	boolean loop;
	InstagramGetter instaGetter = new InstagramGetter();
	DatabaseMetoder adder = new DatabaseMetoder(); // <-- Klasse some inneholder alle metoder (med mindre eg sortere de bedre..)
	
	private void getter() throws IOException{		
		while(loop) {
			tags = adder.getTagsFromDatabase(); 
			for (int i = 0; i < tags.size(); i++){
				tmp = instaGetter.getPictureList(tags.get(i));
				for (int j = 0; j < tmp.size(); j++) pictures.add(tmp.get(j));
			}
			sortByLikes(pictures);					//Fancy sorteringsalgoritme. Til nŒ sorterer den kun pŒ likes. IdŽer?
			for (int i = 0; i < pictures.size(); i++)
			{
			adder.addURLs(pictures.get(i));
			}
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