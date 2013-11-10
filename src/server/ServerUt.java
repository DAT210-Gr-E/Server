package server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import klient.Nettverk.Pakke;

public class ServerUt implements Runnable {
	ObjectOutputStream output = null;
	Pakke innPakken = null;
	Pakke utPakken = null;
	
	public ServerUt(ObjectOutputStream output, Pakke innPakken){
		this.output = output;
		this.innPakken = innPakken;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("ServerUt runs");
		PakkeHandler handler = new PakkeHandler();
		
		utPakken = handler.createPakke(innPakken);
		System.out.println(utPakken.getTransaksjonsid());
		
		try {
			output.writeObject(utPakken);
			System.out.println("Pakke sendt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
