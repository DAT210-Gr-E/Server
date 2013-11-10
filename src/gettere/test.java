package gettere;

import java.io.IOException;
import java.util.List;

import server.Picture;


public class test {

	static mainGetter getter;
	static InstagramGetter instagramGetter;

	public static void main(String[] args) throws IOException {
		getter = new mainGetter();
		instagramGetter = new InstagramGetter();
		
		getter.start();

		//Ulike tester

		//	testGetPictureList();
	//	testMainGetter();
		//testSortByLikes();

		System.out.println("--- FERDIG ---");
	}



	public static void testGetPictureList() throws IOException{
		instagramGetter.getPictureList("ajkrdhgiwosdinvnknserbvdknjg");
		instagramGetter.getPictureList("vinteren2013");
	}

// Invalid pga mine endringer, kan diskuteres i morgen 
	/*
	public static void testMainGetter() throws IOException{
		String[] tagTest = new String[]{"vinteren2013", "skambra"};
		getter.tags = tagTest;
		getter.start();
	}*/

	public static void testSortByLikes() throws IOException {
		List<Picture> pic = instagramGetter.getPictureList("vinteren2013");
		System.out.println("Usortert liste av likes: ");
		for (int i = 0; i < pic.size(); i++) {
			System.out.println(pic.get(i).likes);
		}
		mainGetter.sortByLikes(pic);
		System.out.println("\nListe sortert pŒ likes: ");

		for (int i = 0; i < pic.size(); i++) {
			System.out.println(pic.get(i).likes);
		}

	}
}
