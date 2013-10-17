package startlogikk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start {
	public Start() 
	{
	
	//Start getter i egen thread
	Thread getThread = new Thread(new Getter());
	getThread.start();
	
	
	
	
	// Lytt etter TCP connections
	// Start connections i egne tr�der. type listener.
	
		//TESTING
		
	//Starter opp database kommunikasjon
		
	}
}
