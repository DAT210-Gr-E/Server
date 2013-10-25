package klient.Nettverk;

import java.net.URL;

public class KlientNettUt implements ISend {

	// Denne tråden skal etablere kontakt med serveren for så å kunne
	// sende String[]'s med tags som serveren skal bruke til å søke
	// opp linker. Ellers så skal den imidlertid kunne "Poke" serveren
	// sånn at serveren kan sende linker uten å måtte motta tags.
	
	@Override
	public void send(String[] tags, int id) {
		System.out.println(id + " TAGS_LISTE_TIL_SERVER: " + PrintStr(tags));
	}
	
	@Override
	public void poke(int id) {
		System.out.println(id + " FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER");
	}
	
	@Override
	public void sendLogin(String passord, int id) {
		System.out.println(id + " LOGIN_PASSORD_TIL_SERVER: " + passord);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendadmin(String[] tags, int id) {
		System.out.println(id + " ADMIN_TAGS_LISTE_TIL_SERVER: " + PrintStr(tags));
	}

	@Override
	public void send(int tid, int id) {
		System.out.println(id + " SET_DEFAULT_TID: " + tid);
		
	}

	@Override
	public void sendadmindefault(String[] tags, int id) {
		
		System.out.println(id + " ADMIN_SET_DEFAULT_TAGSLISTE: " + PrintStr(tags));
	}

	@Override
	public void sendadmininkludert(URL[] urls, boolean[] inkludert, int id) {
		System.out.println(id + " ADMIN_INKLUDER_URL_LISTE");
		System.out.println(id + " ADMIN_EKSKLUDER_URL_LISTE");
	}
	
	private String PrintStr(String[] tags)
	{
		String tmp = "{";
		for(int i = 0; i<tags.length-1; i++)
			tmp = tmp + tags[i] + ", ";
		if(tags.length>0)
			tmp = tmp + tags[tags.length-1];
		tmp = tmp + "}";
		return tmp;
	}
}
