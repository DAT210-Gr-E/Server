package gettere;

import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {
		InstagramGetter instagramGetter = new InstagramGetter();
		instagramGetter.getPictureList("ajkrdhgiwosdinvnknserbvdknjg");
		instagramGetter.getPictureList("vinteren2013");
		System.out.println("--- FERDIG ---");
	}
}
