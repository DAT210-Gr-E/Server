package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Ugyldig_pakke_Handler implements IHandler {

	@Override
	public Pakke handlePakke(int transID, Pakke pakke, DatabaseMetoder db) {
		
		return new Pakke(transID, TransaksjonsType.UGYLDIG_PAKKE);
	}

}
