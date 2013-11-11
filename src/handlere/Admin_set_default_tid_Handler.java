package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Admin_set_default_tid_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) {
		int transID = pakke.getTransaksjonsid();
		int tid = pakke.getTid();
		// set tid i databasen
		return new Pakke(transID, TransaksjonsType.KVITTERING);
	}

}
