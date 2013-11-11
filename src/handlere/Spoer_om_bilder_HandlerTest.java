package handlere;

import static org.junit.Assert.*;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

import org.junit.Test;

import databaseKommunikasjon.DatabaseMetoder;

public class Spoer_om_bilder_HandlerTest {

	@Test
	public void test() {
		Spoer_om_bilder_Handler tester = new Spoer_om_bilder_Handler();
		Pakke tPakke = new Pakke(0, TransaksjonsType.BILDER);
		DatabaseMetoder db = new DatabaseMetoder();
		assertEquals(tester.handlePakke(tPakke, db).getPakkeType(), Pakke.PakkeType.SVAR_BILDER);
		assertNotNull(tester.handlePakke(tPakke, db).getUrls());
		
	}

}
