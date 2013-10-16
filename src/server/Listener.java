package server;

import interfaces.IListener;

public class Listener implements IListener {

	@Override
	public Picture[] getLinks(String[] hashtag) {
		// TODO Auto-generated method stub
		//HEr må morten legge inn database magien sin
		System.out.println("Supertest");
		return null;
		
	}

	@Override
	public boolean checkURL(Picture[] img) {
		// TODO Auto-generated method stub
		return true;
	}

}
