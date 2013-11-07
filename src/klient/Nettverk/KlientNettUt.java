package klient.Nettverk;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

import klient.Nettverk.Pakke.TransaksjonsType;


public class KlientNettUt implements ISend {

	private ArrayList<Pakke> pakker = new ArrayList<Pakke>();
	private ObjectOutputStream tilServer;

	// Denne tråden skal etablere kontakt med serveren for så å kunne
	// sende pakker som kommer inn i pakkebufferen. Den skal blindt sende
	// når det kommer inn noe nytt, og all kontroll styres av tråden som
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
					tilServer.writeObject(pakker.remove(0));
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
	}

	@Override
	public void spoerbilder(int id) {
		pakker.add(new Pakke(id, TransaksjonsType.BILDER));
	}

	@Override
	public void spoerlogin(String passord, int id) {
		pakker.add(new Pakke(id, TransaksjonsType.LOGIN, passord));
	}

	@Override
	public void spoertags(int id) {
		pakker.add(new Pakke(id, TransaksjonsType.TAGS));
	}

	@Override
	public void spoeradminbilder(int id) {
		pakker.add(new Pakke(id, TransaksjonsType.ADMIN_BILDER));
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
