package server;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

public class PakkeHandler {
	public PakkeHandler() {
		
	}
	
	public Pakke createPakke (Pakke pakke){
		Pakke utPakken = null;
		int transID = pakke.getTransaksjonsid();
		
		if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_TID){
			int tid = 5;
			//Tid hentes fra databasen.
			utPakken = new Pakke(transID, TransaksjonsType.TID, tid);
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_BILDER){
			String[] bilder = {	"http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg",
								"file:///C:/Users/Bruker/desktop/2012-12-10_03.12.03.png",
								"http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg"
								};
			//Bilder hentes fra databasen.
			
			utPakken = new Pakke(transID, TransaksjonsType.BILDER, bilder);
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_LOGIN){
			String passord = pakke.getPassord();
			boolean success;
			if(passord == "aaaa")
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
									"file:///C:/Users/Bruker/desktop/2012-12-10_03.12.03.png",
									"http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg"
									};
			boolean[] blokkert = {false, true, false};
			
				utPakken = new Pakke(transID, TransaksjonsType.ADMIN_BILDER, alleBilder, blokkert);
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_DEFAULT_TAGS){
			String[] tags = pakke.getTags();
			//tags legges så inn i databasen.
			
			
			utPakken = new Pakke(transID, TransaksjonsType.TAGS, tags);
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_DEFAULT_TID){
			int tid = pakke.getTid();
			
			utPakken = new Pakke(transID, TransaksjonsType.TID, tid);
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_SVARTELISTE){
			String[] bilder = pakke.getUrls();
			boolean[] inkluderte = pakke.getInkluderte();
			
			utPakken = new Pakke(transID, TransaksjonsType.ADMIN_BILDER, bilder, inkluderte);
		}
		else
		{
			//UGYLDIG_PAKKE
		}
		
		
		return utPakken;

}
}