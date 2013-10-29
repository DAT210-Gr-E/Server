package gettere;

import java.io.IOException;
import java.util.List;

import server.Picture;

public class test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InstagramGetter Igetter = new InstagramGetter();
		
		List<Picture> testPictureList = Igetter.getJSON("vinteren2013");
		
		System.out.println(Igetter.getInstagramUrl());
		System.out.println("Antall bilder hentet: " + testPictureList.size() + "\n");
		
		for (int i = 0; i<testPictureList.size(); i++){
			System.out.println("Bilde nr. " + (i+1) + ":");
			System.out.println(testPictureList.get(i).getStandardURL());
			System.out.println(testPictureList.get(i).getThumbnailURL());
			System.out.println("---");
		}
		
	}

}
