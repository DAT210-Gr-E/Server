package handlere;

import static org.junit.Assert.*;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

import org.junit.Test;

import databaseKommunikasjon.DatabaseMetoder;

public class Spoer_om_tid_HandlerTest {

	@Test
	public void testHandlePakke() {
		Spoer_om_tid_Handler tester = new Spoer_om_tid_Handler();
		Pakke tPakke = new Pakke(0, TransaksjonsType.TID);
		DatabaseMetoder db = new DatabaseMetoder();
		assertEquals(tester.handlePakke(tPakke, db).getPakkeType(), Pakke.PakkeType.SVAR_TID);
		assertTrue(tester.handlePakke(tPakke, db).getTid() > 0 && tester.handlePakke(tPakke, db).getTid() <20000);
	}

}
