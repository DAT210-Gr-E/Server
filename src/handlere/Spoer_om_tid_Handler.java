package handlere;

import databaseKommunikasjon.DatabaseMetoder;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

public class Spoer_om_tid_Handler implements IHandler {

	@Override
	public Pakke handlePakke(int transID, Pakke pakke, DatabaseMetoder db) {
		int tid = 2500;
		//Tid mŒ hentes fra databasen.
		
		return new Pakke(transID, TransaksjonsType.TID, tid);
	}

}
