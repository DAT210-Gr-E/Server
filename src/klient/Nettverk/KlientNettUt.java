package klient.Nettverk;

public class KlientNettUt implements ISend {

	// Denne tr�den skal etablere kontakt med serveren for s� � kunne
	// sende String[]'s med tags som serveren skal bruke til � s�ke
	// opp linker. Ellers s� skal den imidlertid kunne "Poke" serveren
	// s�nn at serveren kan sende linker uten � m�tte motta tags.
	
	@Override
	public void send(String[] tags) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void poke() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void sendLogin(String passord) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendadmin(String[] tags) {
	}

}
