package handlere;

import static org.junit.Assert.*;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

import org.junit.Test;

import databaseKommunikasjon.DatabaseMetoder;

public class Spoer_om_login_HandlerTest {

	@Test
	public void testHandlePakke() {
		Spoer_om_login_Handler tester = new Spoer_om_login_Handler();
		Pakke tPakke = new Pakke(0, TransaksjonsType.LOGIN, "aaaa");
		DatabaseMetoder db = new DatabaseMetoder();
		assertEquals(tester.handlePakke(tPakke, db).getPakkeType(), Pakke.PakkeType.SVAR_LOGIN);
		assertTrue(tester.handlePakke(tPakke, db).getSuksess());
		
	}

}
