package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Admin_set_default_tags_Handler implements IHandler {
	
	DatabaseMetoder dbMetoder = new DatabaseMetoder();
	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) {
		int transID = pakke.getTransaksjonsid();
		String[] tags = pakke.getTags();
		dbMetoder.addTags(tags);
		return new Pakke(transID, TransaksjonsType.KVITTERING);
	}

}
