package gettere;

import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {
		InstagramGetter instagramGetter = new InstagramGetter();
		instagramGetter.getJSON("vinteren2013");
		instagramGetter.getJSON("vinter");
		System.out.println("--- FERDIG ---");
	}
}
