package klient.Nettverk;

import java.io.Serializable;

public class Pakke implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum PakkeType{
		// PakkeType											   K  S		Metoder for gitt PakkeType								Konstruktør for gitt Pakketype
		FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER,	//  ->		getPakkeType, getTransaksjonsId							(int)
		SET_DEFAULT_TID,										//  <>		getPakkeType, getTransaksjonsId, getTid					(int, int)
		TAGS_LISTE_TIL_SERVER,									// 	->		getPakkeType, getTransaksjonsId, getTags				(int, false, String[])
		URL_LISTE_OG_TAGS_ECHO_FRA_SERVER,						// 	<-		getPakkeType, getTransaksjonsId, getTags, getURLs		(int, String[], String[])
		LOGIN_PASSORD_TIL_SERVER,								//  ->		getPakkeType, getTransaksjonsId, getPassord				(int, String)
		LOGIN_SVAR_FRA_SERVER,									//  <-		getPakkeType, getTransaksjonsId, getSuccess				(int, boolean)
		ADMIN_TAGS_LISTE_TIL_SERVER,							//  ->		getPakkeType, getTransaksjonsId, getTags				(int, true, String[])
		ADMIN_SET_DEFAULT_TAGSLISTE,							//  ->		getPakkeType, getTransaksjonsId, getTags				(int, String[])
		ADMIN_INKLUDER_URL_LISTE,								//  <>		getPakkeType, getTransaksjonsId, getURLs, getInkluder	(int, String[], true)
		ADMIN_EKSKLUDER_URL_LISTE								//  <>		getPakkeType, getTransaksjonsId, getURLs, getInkluder	(int, String[], false)
		};
		
		// Transaksjonstyper:
		// 0 FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER		Svares med URL_LISTE_OG_TAGS_ECHO_FRA_SERVER og SET_DEFAULT_TID
		// 1 SET_DEFAULT_TID											Svares med SET_DEFAULT_TID
		// 2 TAGS_LISTE_TIL_SERVER										Svares med URL_LISTE_OG_TAGS_ECHO_FRA_SERVER
		// 3 LOGIN_PASSORD_TIL_SERVER									Svares med LOGIN_SVAR_FRA_SERVER
		// 4 ADMIN_TAGS_LISTE_TIL_SERVER								Svares med ADMIN_INKLUDER_URL_LISTE og ADMIN_EKSKLUDER_URL_LISTE
		// 5 ADMIN_SET_DEFAULT_TAGSLISTE								Svares med URL_LISTE_OG_TAGS_ECHO_FRA_SERVER
		// 6 ADMIN_INKLUDER_URL_LISTE og ADMIN_EKSKLUDER_URL_LISTE		Svares med URL_LISTE_OG_TAGS_ECHO_FRA_SERVER, ADMIN_INKLUDER_URL_LISTE og ADMIN_EKSKLUDER_URL_LISTE
		// 
		// Alle pakker knyttet til en transaksjon må ha samme ID!
		//
		// Unntak! Server kan sende SET_DEFAULT_TID eller URL_LISTE_OG_TAGS_ECHO_FRA_SERVER med ID -1 for å override settings uten forespørsel. Klient svarer ikke Server etter dette.
		
	private String[] tekstliste;
	private int nummer;
	private boolean booliskverdi;
	private PakkeType pakketype;
	private int transaksjonsid;
	
	public Pakke(int id)
	{
		pakketype = PakkeType.FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER;
		transaksjonsid = id;
	}
	
	public Pakke(int id, int millisekunder)
	{
		nummer = millisekunder;
		pakketype = PakkeType.SET_DEFAULT_TID;
		transaksjonsid = id;
	}
	
	public Pakke(int id, boolean adminmodus, String[] tags)
	{
		nummer = tags.length;
		tekstliste = tags.clone();
		if(adminmodus)
			pakketype = PakkeType.ADMIN_TAGS_LISTE_TIL_SERVER;
		else
			pakketype = PakkeType.TAGS_LISTE_TIL_SERVER;
		transaksjonsid = id;
	}
	
	public Pakke(int id, String[] urls, String[] tags)
	{
		String[] u = urls.clone();
		String[] t = tags.clone();
		tekstliste = new String[u.length+t.length];
		for(int i = 0; i<t.length; i++)
			tekstliste[i] = t[i];
		for(int i = 0; i<u.length; i++)
			tekstliste[i+t.length] = u[i];
		nummer = t.length;
		pakketype = PakkeType.URL_LISTE_OG_TAGS_ECHO_FRA_SERVER;
		transaksjonsid = id;
	}
	
	public Pakke(int id, String passord)
	{
		tekstliste = new String[1];
		tekstliste[0] = passord;
		pakketype = PakkeType.LOGIN_PASSORD_TIL_SERVER;
		transaksjonsid = id;
	}
	
	public Pakke(int id, boolean loginSuksess)
	{
		booliskverdi = loginSuksess;
		pakketype = PakkeType.LOGIN_SVAR_FRA_SERVER;
		transaksjonsid = id;
	}
	
	public Pakke(int id, String[] tags)
	{
		nummer = tags.length;
		tekstliste = tags.clone();
		pakketype = PakkeType.ADMIN_SET_DEFAULT_TAGSLISTE;
		transaksjonsid = id;
	}

	public Pakke(int id, String[] urls, boolean inkluder)
	{
		tekstliste = urls.clone();
		booliskverdi = inkluder;
		if(inkluder)
			pakketype = PakkeType.ADMIN_INKLUDER_URL_LISTE;
		else
			pakketype = PakkeType.ADMIN_EKSKLUDER_URL_LISTE;
		transaksjonsid = id;
	}
	
	public PakkeType getPakkeType()
	{
		return pakketype;
	}
	
	public int getTransaksjonsid()
	{
		return transaksjonsid;
	}

	public int getTid()
	{
		if(pakketype == PakkeType.SET_DEFAULT_TID)
			return nummer;
		else
			return -1;
	}
	
	public String[] getUrls()
	{
		if(pakketype == PakkeType.ADMIN_INKLUDER_URL_LISTE || pakketype == PakkeType.ADMIN_EKSKLUDER_URL_LISTE)
			return tekstliste.clone();
		else if(pakketype == PakkeType.URL_LISTE_OG_TAGS_ECHO_FRA_SERVER)
		{
			String[] tmp = new String[tekstliste.length - nummer];
			for(int i = 0; i<tmp.length; i++)
				tmp[i] = tekstliste[i+nummer];
			return tmp;
		}
		else
			return null;
	}
	
	public String[] getTags()
	{
		if(pakketype == PakkeType.TAGS_LISTE_TIL_SERVER || pakketype == PakkeType.ADMIN_TAGS_LISTE_TIL_SERVER || pakketype == PakkeType.ADMIN_SET_DEFAULT_TAGSLISTE)
			return tekstliste.clone();
		if(pakketype == PakkeType.URL_LISTE_OG_TAGS_ECHO_FRA_SERVER)
		{
			String[] tmp = new String[nummer];
			for(int i = 0; i<tmp.length; i++)
				tmp[i] = tekstliste[i];
			return tmp;
		}
		else
			return null;
	}

	public String getPassord()
	{
		if(pakketype == PakkeType.LOGIN_PASSORD_TIL_SERVER)
			return tekstliste[0];
		else
			return null;
	}
	
	public boolean getSuksess()
	{
		if(pakketype == PakkeType.LOGIN_SVAR_FRA_SERVER)
			return booliskverdi;
		else
			return false;
	}
	
	public boolean getInkluder()
	{
		if(pakketype == PakkeType.ADMIN_INKLUDER_URL_LISTE || pakketype == PakkeType.ADMIN_EKSKLUDER_URL_LISTE)
			return booliskverdi;
		else
			return false;
	}
}