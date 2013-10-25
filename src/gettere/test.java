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
		List<Picture> testPictureList = Igetter.getJSON("nofilter");
		
		for (int i = 0; i<testPictureList.size(); i++){
			System.out.println(testPictureList.get(i).getUrl());
		}
		
	}

}
