package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Admin_set_svarteliste_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) {
		int transID = pakke.getTransaksjonsid();
		String[] bilder = pakke.getUrls();
		boolean[] inkluderte = pakke.getInkluderte();
		// oppdater flags i databasen
		return new Pakke(transID, TransaksjonsType.KVITTERING);
	}
}
