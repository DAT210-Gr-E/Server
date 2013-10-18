package startlogikk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import databaseKommunikasjon.Getter;
import java.io.*;
import java.net.*;


public class Start {
	
	private static final int NUMTHREADS = 6; //mulighet for 5 klienter + 1 getter.
	private static final int PORTNO = 10100; //Port nummeret vi lytter til.
	public Start() 
	{
		
	//Starter opp database kommunikasjon
		new databaseKommunikasjon.StartKommunikasjonMedDatabase();
	//Laget start database kommunikasjon
		
	/**
	 * Måtte kommentere ut en del av denne koden for å programmet til å kjøre, men nå funker koblingen opp mot databasen. 
	 */
	ExecutorService executor = Executors.newFixedThreadPool(NUMTHREADS);
	
	//Start getter i egen thread. Denne b¿r v¾re en loop som pauser selv.
	//Runnable getThread = new Getter(); //getter er et interface
	//executor.execute(getThread);
	
	
	
	
	// Lytt etter TCP connections
	// Start connections i egne threads. type listener.
	//ServerSocket listenSocket = new ServerSocket(PORTNO);
	
	//Socket array of (NUMTHREADS-1)
	//Start each socket in a separate thread.
	//How about returning finished connections to the thread pool?
	
	
		//TESTING
		
	
		
	}
}
