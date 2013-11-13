package server;

import java.io.*;
import java.net.Socket;

public class Worker implements Runnable {
	protected 	Socket clientSocket	= null;
				ServerInn sInn 		= null;

		public Worker(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			System.out.println("Connection created, client IP: " + clientSocket.getInetAddress());
			ObjectInputStream input 	= null;
			ObjectOutputStream output 	= null;

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
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
}