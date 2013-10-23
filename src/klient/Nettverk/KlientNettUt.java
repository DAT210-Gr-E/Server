package klient.Nettverk;

public class KlientNettUt implements ISend {

	// Denne tråden skal etablere kontakt med serveren for så å kunne
	// sende String[]'s med tags som serveren skal bruke til å søke
	// opp linker. Ellers så skal den imidlertid kunne "Poke" serveren
	// sånn at serveren kan sende linker uten å måtte motta tags.
	
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
