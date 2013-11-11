package server;

import static org.junit.Assert.*;
import handlere.Spoer_om_tid_Handler;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;

import org.junit.Test;

import databaseKommunikasjon.DatabaseMetoder;

public class PakkeHandlerTest {

	@Test
	public void testCreatePakke() {
		PakkeHandler tester = new PakkeHandler();
		Pakke tPakke = new Pakke(0, TransaksjonsType.TID);
		
		assertEquals(tester.createPakke(tPakke).getPakkeType(), Pakke.PakkeType.SVAR_TID);
		assertEquals(tester.createPakke(tPakke).getTransaksjonsid(), 0);
		assertTrue(tester.createPakke(tPakke).getTid() > 0 && tester.createPakke(tPakke).getTid()< 20000);
	
		//Hvis en funker funker nok alle. Handlerne er testet hver for seg.
	}

	

}
