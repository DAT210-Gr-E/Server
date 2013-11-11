package server;

import java.util.ArrayList;

import databaseKommunikasjon.DatabaseMetoder;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

public class PakkeHandler {
	public PakkeHandler() {
		
	}
	
	public Pakke createPakke (Pakke pakke){
		Pakke utPakken = null;
		DatabaseMetoder db = new DatabaseMetoder();
		int transID = pakke.getTransaksjonsid();
		
		
		if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_TID){
			int tid = 2500;
			//Tid hentes fra databasen.
			utPakken = new Pakke(transID, TransaksjonsType.TID, tid);
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_BILDER){
			String[] bilder = {	"http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg",
								"http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg"
								};
			ArrayList<String> urls = db.getURLsFromDatabase();
			System.out.println("Bilder kom ut");
			String[] arrayUrl = new String[urls.size()];
			for (int i=0 ; i< urls.size() ; i++)
			{
				arrayUrl[i] = urls.get(i);
			}
			
			utPakken = new Pakke(transID, TransaksjonsType.BILDER, arrayUrl);
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_LOGIN){
			String passord = pakke.getPassord();
			boolean success;
			if(passord.equals("aaaa"))
			{
				success = true;
			}
			else 
			{
				success = false;
			}
		
			utPakken = new Pakke(transID, TransaksjonsType.LOGIN, passord, success);
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_TAGS){
			String[] tags = {"aaa", "bbb", "ccc" };
			//tags hentes fra databasen.
			
			utPakken = new Pakke(transID, TransaksjonsType.TAGS, tags);
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SPOER_OM_BILDER){
			String[] alleBilder = {	"http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg",
									"http://d.facdn.net/art/farad/1373404117.farad_dsc02997.jpg",
									"http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg"
									};
			boolean[] blokkert = {false, true, false};
			
				utPakken = new Pakke(transID, TransaksjonsType.ADMIN_BILDER, alleBilder, blokkert);
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_DEFAULT_TAGS){
			String[] tags = pakke.getTags();
			//tags legges sŒ inn i databasen.
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_DEFAULT_TID){
			int tid = pakke.getTid();
			// set tid i databasen
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_SVARTELISTE){
			String[] bilder = pakke.getUrls();
			boolean[] inkluderte = pakke.getInkluderte();
			// oppdater flags i databasen
		}
		else
		{
			//UGYLDIG_PAKKE
		}
		
		
		return utPakken;

}
}