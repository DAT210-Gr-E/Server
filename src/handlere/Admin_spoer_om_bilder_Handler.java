package handlere;

import java.util.ArrayList;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Admin_spoer_om_bilder_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) {
		int transID = pakke.getTransaksjonsid();
		ArrayList<String> urls = db.getURLsFromDatabase();
		String[] arrayUrl = new String[urls.size()];
		for (int i=0 ; i< urls.size() ; i++)
		{
			arrayUrl[i] = urls.get(i);
		}
		boolean[] inkludert = new boolean[arrayUrl.length];
		for(int i = 0 ; i < arrayUrl.length ; i++)
		{
			inkludert[i] = true;
		}
		return new Pakke(transID, TransaksjonsType.ADMIN_BILDER, arrayUrl, inkludert);
	}

}
