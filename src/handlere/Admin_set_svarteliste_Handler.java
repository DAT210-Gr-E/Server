package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Admin_set_svarteliste_Handler implements IHandler {

	@Override
	public Pakke handlePakke(int transID, Pakke pakke, DatabaseMetoder db) {
		String[] bilder = pakke.getUrls();
		boolean[] inkluderte = pakke.getInkluderte();
		// oppdater flags i databasen
		return new Pakke(transID, TransaksjonsType.KVITTERING);
	}
}
