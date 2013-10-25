package klient.Nettverk;

import java.net.MalformedURLException;
import java.net.URL;

public class KlientNettInn implements IMotta {

	private int[] transaksjoner = {0, 0, 0, 0, 0, 0, 0};
	private URL[] linker = new URL[0];
	private String[] tags = {""};
	private URL[] alinker = new URL[0];
	private String[] atags = {""};
	private boolean[] inkluderte = {};
	private int tid = -1;
	private boolean login = false;
	private String loginpassord = "";

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
	public void run() {
		/////////////////////////////////////////////////////////// Test; hele metodeinholdet.
		URL[] l = new URL[3];
		URL[] la = new URL[5];
		boolean[] b = new boolean[5];
		try {
			l[0] = new URL("http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg");
			l[1] = new URL("file:///C:/Users/Bruker/desktop/2012-12-10_03.12.03.png");
			l[2] = new URL("http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg");
			la[0] = new URL("http://d.facdn.net/art/farad/1373404117.farad_dsc02997.jpg");
			la[1] = new URL("http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg");
			la[2] = new URL("http://r.api.no/local/v3/publications/www.ba.no/gfx/lav_logo.gif");
			la[3] = new URL("file:///C:/Users/Bruker/desktop/2012-12-10_03.12.03.png");
			la[4] = new URL("http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg");
			b[0] = true;
			b[2] = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		inkluderte = b;
		alinker = la;
		linker = l;
		tid = 2500;
		transaksjoner[0] = 1;

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		transaksjoner[4] = 2;

		try {
			Thread.sleep(30000);
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
		transaksjoner[4] = 3;
		linker = l;
		transaksjoner[2] = -1;
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
	public String[] getAdminTags() {
		return atags;
	}

	@Override
	public int getID(int type) {
		int tmp = transaksjoner[type];
		if(tmp != 0)
			System.out.println("Pakke " + tmp + " inspisert");
		transaksjoner[type] = 0;
		return tmp;
	}
}
