package handlere;

import databaseKommunikasjon.DatabaseMetoder;
import klient.Nettverk.Pakke;

public interface IHandler {
	public Pakke handlePakke(int transID, Pakke pakke, DatabaseMetoder db);

}
