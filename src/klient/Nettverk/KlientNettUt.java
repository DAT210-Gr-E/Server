package klient.Nettverk;

public class KlientNettUt implements ISend {

	// Denne tr�den skal etablere kontakt med serveren for s� � kunne
	// sende String[]'s med tags som serveren skal bruke til � s�ke
	// opp linker. Ellers s� skal den imidlertid kunne "Poke" serveren
	// s�nn at serveren kan sende linker uten � m�tte motta tags.
	
	@Override
	public void send(String[] tags) {
		System.out.println("TAGS_LISTE_TIL_SERVER");
	}
	
	@Override
	public void poke() {
		System.out.println("FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER");
	}
	
	@Override
	public void sendLogin(String passord) {
		System.out.println("LOGIN_PASSORD_TIL_SERVER");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendadmin(String[] tags) {
		System.out.println("ADMIN_TAGS_LISTE_TIL_SERVER");
	}

	@Override
	public void send(int tid) {
		System.out.println("SET_DEFAULT_TID");
		
	}

	@Override
	public void sendadmindefault(String[] tags) {
		System.out.println("ADMIN_SET_DEFAULT_TAGSLISTE");
	}

	@Override
	public void sendadmininkludert(String[] tags, boolean[] inkludert) {
		System.out.println("ADMIN_INKLUDER_URL_LISTE");
		System.out.println("ADMIN_EKSKLUDER_URL_LISTE");
	}

}
