package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Ugyldig_pakke_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) 
	{
		int transID = pakke.getTransaksjonsid();
		
		return new Pakke(transID, TransaksjonsType.UGYLDIG_PAKKE);
	}

}
