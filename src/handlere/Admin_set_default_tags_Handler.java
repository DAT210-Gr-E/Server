package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Admin_set_default_tags_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) {
		int transID = pakke.getTransaksjonsid();
		String[] tags = pakke.getTags();
		//tags legges sŒ inn i databasen.
		return new Pakke(transID, TransaksjonsType.KVITTERING);
	}

}
