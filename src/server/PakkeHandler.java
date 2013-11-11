package server;


import databaseKommunikasjon.DatabaseMetoder;
import klient.Nettverk.Pakke;
import handlere.*;

public class PakkeHandler {
	public PakkeHandler() {
		
	}
	
	public Pakke createPakke (Pakke pakke){
		DatabaseMetoder db = new DatabaseMetoder();
		int transID = pakke.getTransaksjonsid();
		IHandler handler;
		
		
		if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_TID)
		{
			handler = new Spoer_om_tid_Handler();
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_BILDER)
		{
			handler = new Spoer_om_bilder_Handler();
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_LOGIN)
		{
			handler = new Spoer_om_login_Handler();
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.SPOER_OM_TAGS)
		{
			handler = new Spoer_om_tags_Handler();
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SPOER_OM_BILDER)
		{
			handler = new Admin_spoer_om_bilder_Handler();
		}
		else if(pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_DEFAULT_TAGS)
		{
			handler = new Admin_set_default_tags_Handler();
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_DEFAULT_TID)
		{
			handler = new Admin_set_default_tid_Handler();
		}
		else if( pakke.getPakkeType() == Pakke.PakkeType.ADMIN_SET_SVARTELISTE)
		{
			handler = new Admin_set_svarteliste_Handler();
		}
		else
		{
			handler = new Ugyldig_pakke_Handler();
		}
		
		
		return handler.handlePakke(transID, pakke, db);

}
}