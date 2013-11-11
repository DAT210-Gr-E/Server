package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Spoer_om_login_Handler implements IHandler {

	@Override
	public Pakke handlePakke(int transID, Pakke pakke, DatabaseMetoder db) {
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
	
		return new Pakke(transID, TransaksjonsType.LOGIN, passord, success);
	}

}
