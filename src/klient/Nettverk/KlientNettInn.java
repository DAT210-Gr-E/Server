package klient.Nettverk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;

import klient.Nettverk.Pakke.PakkeType;

public class KlientNettInn implements IMotta {

	private int[] transaksjoner = {0, 0, 0, 0, 0};
	private URL[] linker = new URL[0];
	private String[] tags = {""};
	private URL[] alinker = new URL[0];
	private boolean[] inkluderte = {};
	private int tid = -1;
	private boolean login = false;
	private String loginpassord = "";
	private ObjectInputStream fraServer;

	// Denne tr�den skal etablere kontakt og lytte etter pakker fra server og lagre
	// innholdet i variablene ovenfor.

	public KlientNettInn(ObjectInputStream fs) {
		fraServer = fs;
	}

	@Override
	public void run() {
		while(true)
		{
			try {
				Pakke pakke = (Pakke)fraServer.readObject();
				int id = pakke.getTransaksjonsid();
				System.out.println("Mottatt: " + pakke.getPakkeType() + ", " + id);
				if (pakke.getPakkeType() == PakkeType.SVAR_TID)
				{
					tid = pakke.getTid();
					transaksjoner[0] = id;
				}
				if (pakke.getPakkeType() == PakkeType.SVAR_BILDER)
				{
					linker = StringsToURL(pakke.getUrls());
					transaksjoner[1] = id;
				}
				if (pakke.getPakkeType() == PakkeType.SVAR_LOGIN)
				{
					loginpassord = pakke.getPassord();
					login = pakke.getSuksess();
					transaksjoner[2] = id;
				}
				if (pakke.getPakkeType() == PakkeType.SVAR_TAGS)
				{
					tags = pakke.getTags();
					transaksjoner[3] = id;
				}
				if (pakke.getPakkeType() == PakkeType.ADMIN_SVAR_BILDER)
				{
					alinker = StringsToURL(pakke.getUrls());
					inkluderte = pakke.getInkluderte();
					transaksjoner[4] = id;
				}
			} catch (IOException e) {

			} catch (ClassNotFoundException e) {

			} catch (NullPointerException e){

			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		/*
		/////////////////////////////////////////////////////////// Test; hele metodeinholdet.
		URL[] l = new URL[3];
		try {
			l[0] = new URL("http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg");
			l[1] = new URL("file:///C:/Users/Bruker/desktop/2012-12-10_03.12.03.png");
			l[2] = new URL("http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String[] t = {"aaa", "bbb", "ccc"};

		tags = t;
		linker = l;
		tid = 2500;
		transaksjoner[0] = 1;

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		loginpassord = "aaa";
		login = true;
		transaksjoner[3] = 2;

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		l = new URL[5];
		boolean[] b = new boolean[5];
		try {
			l[0] = new URL("http://d.facdn.net/art/farad/1373404117.farad_dsc02997.jpg");
			l[1] = new URL("http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg");
			l[2] = new URL("http://r.api.no/local/v3/publications/www.ba.no/gfx/lav_logo.gif");
			l[3] = new URL("http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg");
			b[0] = true;
			b[2] = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		inkluderte = b;
		alinker = l;
		transaksjoner[4] = 3;

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		l = new URL[5];
		try {
			l[0] = new URL("http://images5.fanpop.com/image/photos/28000000/randomised-random-28065165-1024-819.jpg");
			l[1] = new URL("http://www.miataturbo.net/attachments/insert-bs-here-4/90179-random-pictures-thread-sfw-gets-horse-random-random-31108109-500-502-jpg?dateline=1380373426");
			l[2] = new URL("http://images2.wikia.nocookie.net/__cb20090724010737/wikiality/images/c/c7/BlankSnowyOrlyOwl.jpg");
			l[3] = new URL("http://www.pulp-paperworld.com/images/stories/BASF/Amflora1_EN.jpg");
			l[4] = new URL("http://bloggfiler.no/charlotte1987.blogg.no/images/430486-6-1261950736602.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		inkluderte = new boolean[5];
		alinker = l;
		transaksjoner[4] = 4;

		linker = l;
		transaksjoner[2] = -1;*/
	}

	private URL[] StringsToURL(String[] urls) {
		URL[] l = new URL[urls.length];
		for(int i=0; i<urls.length; i++)
			try {
				l[i] = new URL(urls[i]);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return l;
	}

	@Override
	public URL[] getURLs() {
		return linker;
	}

	@Override
	public String[] getTags() {
		return tags;
	}

	@Override
	public boolean getLoginSuksess() {
		boolean tmp = login;
		login = false;
		return tmp;
	}

	@Override
	public String getLoginPassord() {
		return loginpassord;
	}

	@Override
	public int getTidsInterval() {
		return tid;
	}

	@Override
	public boolean[] getInkluderteURLer() {
		return inkluderte;
	}

	@Override
	public URL[] getAdminURLs() {
		return alinker;
	}

	@Override
	public int getIDr(int type) {
		int tmp = transaksjoner[type];
		transaksjoner[type] = 0;
		return tmp;
	}
	
	@Override
	public int getID(int type) {
		return transaksjoner[type];
	}
}
