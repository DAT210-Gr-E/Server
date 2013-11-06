package server;

import interfaces.IListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;




public class Listener implements IListener, Runnable {
	
	protected int			serverPort 		= 9091;
	protected ServerSocket	serverSocket 	= null;
	protected boolean		isStopped		= false;
	protected Thread		runningThread	= null;
	
	
	public Listener(int port){
		this.serverPort = port;
	}
	
	@Override
	public void run() {
		synchronized(this){
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		
		while(!isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if(isStopped()) {
					System.out.println("Server stopped.");
					return;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
			new Thread(new Worker(clientSocket)).start();
		}
		System.out.println("Server stopped.");		
	}
	
	private synchronized boolean isStopped() {
		return this.isStopped;
	}
	
	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error closing server.", e);
		}
	}
	
	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e){
			throw new RuntimeException("Error closing server", e);
		}
	}
	
//følgende bør flyttes til pakkeHandler
	@Override
	public Picture[] getLinks(String[] hashtag) {
		
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

