package handlere;

import databaseKommunikasjon.DatabaseMetoder;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

public class Spoer_om_tid_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) {
		int transID = pakke.getTransaksjonsid();
		int tid = 2500;
		//Tid m� hentes fra databasen.
		
		return new Pakke(transID, TransaksjonsType.TID, tid);
	}

}
