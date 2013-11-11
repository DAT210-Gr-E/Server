package handlere;

import databaseKommunikasjon.DatabaseMetoder;
import klient.Nettverk.Pakke;

public interface IHandler {
	public Pakke handlePakke(Pakke pakke, DatabaseMetoder db);

}
