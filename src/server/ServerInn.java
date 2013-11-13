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
		while(!isStopped){
			try {
				Pakke innPakke = (Pakke) input.readObject();
				System.out.println("Pakke received");
				new Thread(new ServerUt(output, innPakke)).start();
			} catch (ClassNotFoundException e) {
				System.out.println("Mottok objekt som ikke var Pakke.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException.");
				e.printStackTrace();
			}
		}
	}

}
