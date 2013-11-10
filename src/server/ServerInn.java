package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import klient.Nettverk.Pakke;

public class ServerInn implements Runnable {
	ObjectInputStream input = null;
	ObjectOutputStream output = null;
	boolean isStopped = false;
	
	public ServerInn(ObjectInputStream input, ObjectOutputStream output){
		this.input = input;
		this.output = output;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!isStopped){
		
		System.out.println("ServerInn runs");
		
		try {
			Pakke innPakke = (Pakke) input.readObject();
			System.out.println("Pakke received");
			new Thread(new ServerUt(output, innPakke)).start();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
