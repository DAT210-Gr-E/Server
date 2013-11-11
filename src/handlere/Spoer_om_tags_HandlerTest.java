package handlere;

import static org.junit.Assert.*;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

import org.junit.Test;

import databaseKommunikasjon.DatabaseMetoder;

public class Spoer_om_tags_HandlerTest {

	@Test
	public void testHandlePakke() {
		Spoer_om_tags_Handler tester = new Spoer_om_tags_Handler();
		Pakke tPakke = new Pakke(0, TransaksjonsType.TAGS);
		DatabaseMetoder db = new DatabaseMetoder();
		assertEquals(tester.handlePakke(tPakke, db).getPakkeType(), Pakke.PakkeType.SVAR_TAGS);
		assertNotNull(tester.handlePakke(tPakke, db).getTags());
		
	}

}
