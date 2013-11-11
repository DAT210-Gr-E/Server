package handlere;

import java.util.ArrayList;

import databaseKommunikasjon.DatabaseMetoder;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

public class Spoer_om_bilder_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) 
	{
		int transID = pakke.getTransaksjonsid();
		ArrayList<String> urls = db.getURLsFromDatabase();
		String[] arrayUrl = new String[urls.size()];	
		for (int i=0 ; i< urls.size() ; i++)
			{
				arrayUrl[i] = urls.get(i);
			}
			return new Pakke(transID, TransaksjonsType.BILDER, arrayUrl);
	}

}
