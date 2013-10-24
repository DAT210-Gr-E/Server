package klient.Nettverk;

import java.net.MalformedURLException;
import java.net.URL;

public class KlientNettInn implements IMotta {

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
		try {
			l[0] = new URL("http://www.wallng.com/images/2013/08/image-explosion-colors-background-beautiful-263613.jpg");
			l[1] = new URL("http://www.nasa.gov/images/content/693952main_pia15817-full_full.jpg");
			l[2] = new URL("file:///C:/Users/Bruker/desktop/2012-12-10_03.12.03.png");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		inkluderte = new boolean[3];
		alinker = l;
		atags[0] = "null";
		linker = l;
		tags[0] = "0";
		
		try {
			Thread.sleep(60000);
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
		atags[0] = "1";
		linker = l;
		tags[0] = "1";
	}

	@Override
	public int getTidsInterval() {
		int tmp = tid;
		tid = -1;
		return tmp;
	}

	@Override
	public boolean[] getInkluderteURLer() {
		// TODO Auto-generated method stub
		return inkluderte;
	}

	@Override
	public URL[] getAdminURLs() {
		// TODO Auto-generated method stub
		return alinker;
	}

	@Override
	public String[] getAdminTags() {
		// TODO Auto-generated method stub
		return atags;
	}
}
