package klient.Nettverk;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import klient.Nettverk.Pakke;
import klient.Nettverk.Pakke.TransaksjonsType;




public class KlientNettUt implements ISend {

	private ArrayList<Pakke> pakker = new ArrayList<Pakke>();
	private ObjectOutputStream tilServer;

	// Denne tr�den skal etablere kontakt med serveren for s� � kunne
	// sende pakker som kommer inn i pakkebufferen. Den skal blindt sende
	// n�r det kommer inn noe nytt, og all kontroll styres av tr�den som
	// bruker KlientNettUt.

	public KlientNettUt(ObjectOutputStream ts) {
		tilServer = ts;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub

		while(true)
		{
			try {
				if(pakker.size()>0)
				{
					Pakke pk = pakker.remove(0);
					tilServer.writeObject(pk);  //pakker.remove(0));
					System.out.println("Pakke sendt: " + pk.getPakkeType() +", "+ pk.getTransaksjonsid());
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NullPointerException e){

			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	@Override
	public void spoertid(int id) {
		pakker.add(new Pakke(id, TransaksjonsType.TID));
		System.out.println("Pakke til sending: Sp�r om Tid, " + id);
	}

	@Override
	public void spoerbilder(int id) {
		pakker.add(new Pakke(id, TransaksjonsType.BILDER));
		System.out.println("Pakke til sending: Sp�r om Bilder, " + id);
	}

	@Override
	public void spoerlogin(String passord, int id) {
		pakker.add(new Pakke(id, TransaksjonsType.LOGIN, passord));
		System.out.println("Pakke til sending: Sp�r om Login, " + id);
	}

	@Override
	public void spoertags(int id) {
		pakker.add(new Pakke(id, TransaksjonsType.TAGS));
		System.out.println("Pakke til sending: Sp�r om Tags, " + id);
	}

	@Override
	public void spoeradminbilder(int id) {
		pakker.add(new Pakke(id, TransaksjonsType.ADMIN_BILDER));
		System.out.println("Pakke til sending: Sp�r om Admin-Bilder, " + id);
	}

	@Override
	public void sendtags(String[] tags) {
		pakker.add(new Pakke(0, TransaksjonsType.ADMIN_SET, tags));
	}

	@Override
	public void sendtid(int tid) {
		pakker.add(new Pakke(0, TransaksjonsType.ADMIN_SET, tid));

	}

	@Override
	public void sendinkluderte(URL[] urls, boolean[] inkludert) {
		if(urls.length==inkludert.length)
		{
			String[] tmp = new String[urls.length];
			for(int i = 0; i<urls.length; i++)
				tmp[i] = urls[i].getProtocol() + "://" + urls[i].getHost() + urls[i].getPath();
			pakker.add(new Pakke(0, TransaksjonsType.ADMIN_SET, tmp, inkludert));
		}
	}
}
