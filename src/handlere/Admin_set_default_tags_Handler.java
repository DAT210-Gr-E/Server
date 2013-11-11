package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Admin_set_default_tags_Handler implements IHandler {

	@Override
	public Pakke handlePakke(int transID, Pakke pakke, DatabaseMetoder db) {
		String[] tags = pakke.getTags();
		//tags legges sŒ inn i databasen.
		return new Pakke(transID, TransaksjonsType.KVITTERING);
	}

}
