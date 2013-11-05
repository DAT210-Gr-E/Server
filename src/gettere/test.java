package gettere;

import java.io.IOException;
import java.util.List;

import server.Picture;

public class test {

	public static void main(String[] args) throws IOException {
		InstagramGetter instagramGetter = new InstagramGetter();
		
		// AE: %C3%A6
		// OE: %C3%B8
		// AA: %C3%A5
		
		List<Picture> testPictureList = instagramGetter.getJSON("fjedl%C3%A5");
		
		System.out.println(instagramGetter.getInstagramUrl());
		System.out.println("Antall bilder hentet: " + testPictureList.size() + "\n");
	}
}
