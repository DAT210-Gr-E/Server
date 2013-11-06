package startlogikk;

import server.Listener;




public class Start {
	
	private static final int PORTNO = 9091; //Port nummeret vi lytter til.
	
	public Start() 
	{

	//Starter opp database kommunikasjon
	//new databaseKommunikasjon.StartKommunikasjonMedDatabase();

	
	Listener listener = new Listener(PORTNO);
	new Thread(listener).start();
	
	
	}
}
