package startlogikk;

import server.Listener;




public class Start {
	
	private static final int PORTNO = 9091; //Port nummeret vi lytter til.
	
	public Start() 
	{

	//Starter opp database kommunikasjon
	
	//Start getter i egen trŒd.....
	
	
	Listener listener = new Listener(PORTNO);
	new Thread(listener).start();
	
	
	}
}
