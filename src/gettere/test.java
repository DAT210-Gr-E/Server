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
		// TODO Auto-generated method stub
		InstagramGetter Igetter = new InstagramGetter();
		
		//////// SP�R GLENN: PROGRAMMET KR�SJET HVIS VI S�KER P� TAGS SOM INNEHOLDER �, � ELLER �
		List<Picture> testPictureList = Igetter.getJSON("rolandtd15kv");
		
		for (int i = 0; i<testPictureList.size(); i++){
			System.out.println(testPictureList.get(i).getStandardURL());
			System.out.println(testPictureList.get(i).getThumbnailURL());
			System.out.println(testPictureList.get(i).getType());
			System.out.println("---");
		}
		
		System.out.println(Igetter.getInstagramUrl());
		
	}

}
