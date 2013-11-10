package gettere;

import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {
		mainGetter getter = new mainGetter();
		//instagramGetter.getPictureList("ajkrdhgiwosdinvnknserbvdknjg");
		//instagramGetter.getPictureList("vinteren2013");
		
		String[] tagTest = new String[]{"vinteren2013", "skambra"};
		
		getter.tags = tagTest;
		getter.start();
		
		System.out.println("--- FERDIG ---");
	}
}
