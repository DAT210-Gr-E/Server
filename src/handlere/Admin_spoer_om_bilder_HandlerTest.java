package handlere;

import static org.junit.Assert.*;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

import org.junit.Test;

import databaseKommunikasjon.DatabaseMetoder;

public class Admin_spoer_om_bilder_HandlerTest {

	@Test
	public void testHandlePakke() {
		Admin_spoer_om_bilder_Handler tester = new Admin_spoer_om_bilder_Handler();
		Pakke tPakke = new Pakke(0, TransaksjonsType.ADMIN_BILDER);
		DatabaseMetoder db = new DatabaseMetoder();
		assertEquals(tester.handlePakke(tPakke, db).getPakkeType(), Pakke.PakkeType.ADMIN_SVAR_BILDER);
		assertNotNull(tester.handlePakke(tPakke, db).getUrls());
		assertNotNull(tester.handlePakke(tPakke, db).getInkluderte());
		assertTrue(tester.handlePakke(tPakke, db).getUrls().length == tester.handlePakke(tPakke,db).getInkluderte().length);
		
	}

}
