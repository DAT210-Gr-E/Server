package klient.Nettverk;

import java.net.URL;

public class KlientNettInn implements IMotta {

	private URL[] linker;
	private String[] tags;
	
	// Denne tr�den skal etablere kontakt og lytte etter ting fra server og lagre det
	// i variablene ovenfor. Serveren b�r sende en Pakke med b�de linker og tags som
	// ble brukt for � s�ke opp akkurat de linkene. Linkene kan v�re en string[] men
	// da m� de omgj�res til en tilsvarende URL[] her n�r det mottas.
	
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
