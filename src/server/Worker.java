package server;

import java.io.*;
import java.net.Socket;


import klient.Nettverk.Pakke;

public class Worker implements Runnable {
	protected Socket clientSocket	= null;
		
		public Worker(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Connection created, client IP: " + clientSocket.getInetAddress());
			ObjectInputStream input = null;
			ObjectOutputStream output = null;
			Pakke innPakke = null;
			Pakke utPakke = null;
			PakkeHandler handler = new PakkeHandler();
			while(true){
				try {
					if(input == null){
						input = new ObjectInputStream(clientSocket.getInputStream());
					}
					try {
						innPakke = (Pakke) input.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					if(output == null) {
						output = new ObjectOutputStream(clientSocket.getOutputStream());
					}
					utPakke = handler.createPakke(innPakke);
					
					output.writeObject(utPakke);
					
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}//end while
		}
}
