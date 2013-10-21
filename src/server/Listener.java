package server;

import interfaces.IListener;

public class Listener implements IListener, Runnable {
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
	}
	
	@Override
	public Picture[] getLinks(String[] hashtag) {
		// TODO Auto-generated method stub
		//
		
		//HEr m√• morten legge inn database magien sin
		System.out.println("Supertest");
		return null;
		
	}

	@Override
	public Picture[] checkURL(Picture[] img) {
		// TODO Auto-generated method stub
		// Sjekk om det finnes et bilde på hver oppgtitte url
		// Dersom bildet returnerer error, fjern det fra listen.
		Picture [] validPics =img; //TODO
		
		for(int i=0 ; i < img.length ; i++)
		{
			validPics[i] = img[i];
		}
		
		
		
		return validPics;
	}

	

}
