package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Admin_set_default_tid_Handler implements IHandler {

	@Override
	public Pakke handlePakke(int transID, Pakke pakke, DatabaseMetoder db) {
		int tid = pakke.getTid();
		// set tid i databasen
		return new Pakke(transID, TransaksjonsType.KVITTERING);
	}

}
