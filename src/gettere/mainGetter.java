package gettere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.Picture;

public class mainGetter {
	static String[] tags;
	static List<Picture> tmp;
	static List<Picture> pictures = new ArrayList<Picture>();
	static boolean loop = true;
	// DatabaseAdder adder = new DatabaseAdder();	TRENGER KODE FRA MORTEN 

	public static void main(String[] args) throws IOException {
		get();
	}

	public static void get() throws IOException{
		InstagramGetter instaGetter = new InstagramGetter();

		// Foreløpig, kun for testing:
		tags = new String[5];
		tags[0] = "vinteren2013";
		tags[1] = "skambratt";
		tags[2] = "universitetet_i_oslo";
		tags[3] = "universitetetioslo";
		tags[4] = "universitetistavanger";
		// ---
		
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
}