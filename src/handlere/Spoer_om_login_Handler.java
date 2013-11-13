package handlere;

import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;
import databaseKommunikasjon.DatabaseMetoder;

public class Spoer_om_login_Handler implements IHandler {

	@Override
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db) {
		int transID = pakke.getTransaksjonsid();
		String passord = pakke.getPassord();
		// Kanskje laste passord fra konfigurasjonsfil?
		boolean success = passord.equals("aaaa");
		return new Pakke(transID, TransaksjonsType.LOGIN, passord, success);
	}

}
