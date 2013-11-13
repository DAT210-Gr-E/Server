package handlere;

import java.util.ArrayList;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Spoer_om_tags_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) {
		int transID = pakke.getTransaksjonsid();
		ArrayList<String> tagsList = db.getTagsFromDatabase();
		String[] tags = new String[tagsList.size()];
		for (int i=0 ; i< tagsList.size() ; i++)
		{
			tags[i] = tagsList.get(i);
		}
		return new Pakke(transID, TransaksjonsType.TAGS, tags);
	}
}
