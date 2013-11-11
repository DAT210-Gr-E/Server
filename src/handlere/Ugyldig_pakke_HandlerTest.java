package handlere;

import static org.junit.Assert.*;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

import org.junit.Test;

import databaseKommunikasjon.DatabaseMetoder;

public class Ugyldig_pakke_HandlerTest {

	@Test
	public void testHandlePakke() {
		Ugyldig_pakke_Handler tester = new Ugyldig_pakke_Handler();
		Pakke tPakke = new Pakke(0, TransaksjonsType.UGYLDIG_PAKKE);
		DatabaseMetoder db = new DatabaseMetoder();
		assertEquals(tester.handlePakke(tPakke, db).getPakkeType(), Pakke.PakkeType.UGYLDIG_PAKKE);
	}

}
