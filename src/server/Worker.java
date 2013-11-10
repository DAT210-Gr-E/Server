package server;

import java.io.*;
import java.net.Socket;

import klient.Nettverk.Pakke;

public class Worker implements Runnable {
	protected Socket clientSocket	= null;
	ServerInn sInn = null;
	
		
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
			//while(true){
				try {
					System.out.println("-------");
					if(input == null){
						input = new ObjectInputStream(clientSocket.getInputStream());
						System.out.println("Got intput stream");
						
					}
				
					if(output == null) {
						output = new ObjectOutputStream(clientSocket.getOutputStream());
						System.out.println("Got output stream");
					}
					
					
					new Thread(new ServerInn(input, output)).start();
					/*utPakke = handler.createPakke(innPakke);
					
					output.writeObject(utPakke);
					System.out.println("Pakke returned.");*/
					
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			//}//end while
		}
}
