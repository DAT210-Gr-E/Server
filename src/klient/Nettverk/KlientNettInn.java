package klient.Nettverk;

import java.net.URL;

public class KlientNettInn implements IMotta {

	private URL[] linker;
	private String[] tags;
	
	// Denne tråden skal etablere kontakt og lytte etter ting fra server og lagre det
	// i variablene ovenfor. Serveren bør sende en Pakke med både linker og tags som
	// ble brukt for å søke opp akkurat de linkene. Linkene kan være en string[] men
	// da må de omgjøres til en tilsvarende URL[] her når det mottas.
	
	@Override
	public URL[] getURLs() {
		return linker;
	}

	@Override
	public String[] getTags() {
		return tags;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
