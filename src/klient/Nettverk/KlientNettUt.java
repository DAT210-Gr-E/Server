package klient.Nettverk;

import java.net.URL;
import java.util.ArrayList;

import klient.Klient;

public class KlientNettUt implements ISend {

	private ArrayList<Pakke> pakker = new ArrayList<Pakke>();

	// Denne tråden skal etablere kontakt med serveren for så å kunne
	// sende pakker som kommer inn i pakkebufferen. Den skal blindt sende
	// når det kommer inn noe nytt, og all kontroll styres av tråden som
	// bruker KlientNettUt.

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}



	@Override
	public void poke(int id) {
		System.out.println(id + " FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER");
		pakker.add(new Pakke(id));
	}

	@Override
	public void send(String[] tags, int id) {
		System.out.println(id + " TAGS_LISTE_TIL_SERVER: " + Klient.PrintStr(tags));
		pakker.add(new Pakke(id, false, tags));
	}

	@Override
	public void sendadmin(String[] tags, int id) {
		System.out.println(id + " ADMIN_TAGS_LISTE_TIL_SERVER: " + Klient.PrintStr(tags));
		pakker.add(new Pakke(id, true, tags));
	}

	@Override
	public void sendLogin(String passord, int id) {
		System.out.println(id + " LOGIN_PASSORD_TIL_SERVER: " + passord);
		pakker.add(new Pakke(id, passord));
	}

	@Override
	public void send(int tid, int id) {
		System.out.println(id + " SET_DEFAULT_TID: " + tid + "ms");
		pakker.add(new Pakke(id, tid));
	}

	@Override
	public void sendadmindefault(String[] tags, int id) {
		System.out.println(id + " ADMIN_SET_DEFAULT_TAGSLISTE: " + Klient.PrintStr(tags));
		pakker.add(new Pakke(id, tags));
	}

	@Override
	public void sendadmininkludert(URL[] urls, boolean[] inkludert, int id) {
		if(urls.length == inkludert.length)
		{
			int inkl = 0;
			for(int i = 0; i<inkludert.length; i++)
				if(inkludert[i])
					inkl++;
			String[] tmp1 = new String[inkl];
			String[] tmp2 = new String[inkludert.length - inkl];
			int a = 0;
			int b = 0;
			for(int i = 0; i<inkludert.length; i++)
				if(inkludert[i])
					tmp1[a++] = urls[i].getProtocol() + "://" + urls[i].getHost() + urls[i].getPath();
				else
					tmp2[b++] = urls[i].getProtocol() + "://" + urls[i].getHost() + urls[i].getPath();

				System.out.println(id + " ADMIN_INKLUDER_URL_LISTE");
				pakker.add(new Pakke(id, tmp1, true));
				System.out.println(id + " ADMIN_EKSKLUDER_URL_LISTE");
				pakker.add(new Pakke(id, tmp2, false));
		}
		else
			System.out.println(id + " FEIL! Usynkronisert svarteliste forsøkt sendt.");
	}


}
