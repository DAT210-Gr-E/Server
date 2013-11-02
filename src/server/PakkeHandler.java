package server;

import klient.Nettverk.Pakke;

public class PakkeHandler {
	public PakkeHandler() {
		
	}
	
	public Pakke createPakke (Pakke pakke){
		Pakke utPakken = null;
		int transID = pakke.getTransaksjonsid();
		
		if(pakke.getPakkeType() == Pakke.PakkeType.FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER){
			//Hent fra database. Morten hva får jeg her?
			//Enten kan jeg ta imot en pakke eller string[]urlliste og string[]tags
			//Kun for test:
			String[] u = {"http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg",
							"file:///C:/Users/Bruker/desktop/2012-12-10_03.12.03.png",
							"http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg"};
			String[] t = {"aaa", "bbb", "ccc"};
		
			utPakken = new Pakke(transID, u, t);
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.SET_DEFAULT_TID){
			int tid = pakke.getTid();
			//tid må legges inn i databasen.
			
			
			utPakken = new Pakke(transID, tid);
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.TAGS_LISTE_TIL_SERVER){
			String[] tags = pakke.getTags();
			//tags til database...
			//Hent fra database. Morten hva får jeg her?
			//Enten kan jeg ta imot en pakke eller string[]urlliste og string[]tags
			//Kun for test:
			String[] u = {"http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg",
							"file:///C:/Users/Bruker/desktop/2012-12-10_03.12.03.png",
							"http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg"};
			String[] t = {"aaa", "bbb", "ccc"};
		
			utPakken = new Pakke(transID, u, t);
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.URL_LISTE_OG_TAGS_ECHO_FRA_SERVER){
			utPakken = null; //evt error?
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.LOGIN_PASSORD_TIL_SERVER){
			String passord = pakke.getPassord();
			//Sjekk med databasen om pasordet er rikgtig.
			//foreløpig: 
			if (passord == "hemmelig") {
				utPakken = new Pakke(transID, true);
			} 
			else {
				utPakken = new Pakke(transID, false);
			}
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.LOGIN_SVAR_FRA_SERVER){
			utPakken = null; //evt error?
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_TAGS_LISTE_TIL_SERVER){
			//antar denne er for å legge til nye tags.
			String[] tags = pakke.getTags();
			//tags må legges til databasen
			utPakken = new Pakke(transID, tags, true);
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_DEFAULT_TAGSLISTE){
			//er dette da for å overskrive hele lista?
			utPakken = null; //evt error?
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_INKLUDER_URL_LISTE){
			utPakken = null; //evt error?
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_EKSKLUDER_URL_LISTE){
			utPakken = null; //evt error?
		}
		
		
		return utPakken;

}
}