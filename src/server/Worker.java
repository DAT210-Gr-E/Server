package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Worker implements Runnable {
	protected Socket clientSocket	= null;
		
		public Worker(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				InputStream input 	= clientSocket.getInputStream();
				OutputStream output = clientSocket.getOutputStream();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
}


