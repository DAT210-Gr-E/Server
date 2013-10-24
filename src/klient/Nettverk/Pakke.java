package klient.Nettverk;

import java.io.Serializable;

public class Pakke implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum PakkeType{
		// PakkeType											Metoder for gitt PakkeType				Konstruktør for gitt Pakketype
		FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER,	// getPakkeType							()
		SET_DEFAULT_TID,										// getPakkeType, getTid					(int)
		TAGS_LISTE_TIL_SERVER,									// getPakkeType, getTags				(false, String[])
		URL_LISTE_OG_TAGS_ECHO_FRA_SERVER,						// getPakkeType, getTags, getURLs		(false, String[], String[])
		LOGIN_PASSORD_TIL_SERVER,								// getPakkeType, getPassord				(String)
		LOGIN_SVAR_FRA_SERVER,									// getPakkeType, getSuccess				(boolean)
		ADMIN_TAGS_LISTE_TIL_SERVER,							// getPakkeType, getTags				(true, String[])
		ADMIN_URL_LISTE_OG_TAGS_ECHO_FRA_SERVER,				// getPakkeType, getTags, getURLs		(true, String[], String[])
		ADMIN_SET_DEFAULT_TAGSLISTE,							// getPakkeType, getTags				(String[])
		ADMIN_INKLUDER_URL_LISTE,								// getPakkeType, getURLs, getInkluder	(String[], true)
		ADMIN_EKSKLUDER_URL_LISTE								// getPakkeType, getURLs, getInkluder	(String[], false)
		};
	
	private String[] tekstliste;
	private int nummer;
	private boolean booliskverdi;
	private PakkeType pakketype;
	
	public Pakke()
	{
		pakketype = PakkeType.FORESPOERSEL_OM_DEFAULT_URL_LISTE_OG_TID_FRA_SERVER;
	}
	
	public Pakke(int millisekunder)
	{
		nummer = millisekunder;
		pakketype = PakkeType.SET_DEFAULT_TID;
	}
	
	public Pakke(boolean adminmodus, String[] tags)
	{
		nummer = tags.length;
		tekstliste = tags.clone();
		if(adminmodus)
			pakketype = PakkeType.ADMIN_TAGS_LISTE_TIL_SERVER;
		else
			pakketype = PakkeType.TAGS_LISTE_TIL_SERVER;
	}
	
	public Pakke(boolean adminmodus, String[] urls, String[] tags)
	{
		String[] u = urls.clone();
		String[] t = tags.clone();
		tekstliste = new String[u.length+t.length];
		for(int i = 0; i<t.length; i++)
			tekstliste[i] = t[i];
		for(int i = 0; i<u.length; i++)
			tekstliste[i+t.length] = u[i];
		nummer = t.length;
		if(adminmodus)
			pakketype = PakkeType.ADMIN_URL_LISTE_OG_TAGS_ECHO_FRA_SERVER;
		else
			pakketype = PakkeType.URL_LISTE_OG_TAGS_ECHO_FRA_SERVER;
	}
	
	public Pakke(String passord)
	{
		tekstliste = new String[1];
		tekstliste[0] = passord;
		pakketype = PakkeType.LOGIN_PASSORD_TIL_SERVER;
	}
	
	public Pakke(boolean loginSuksess)
	{
		booliskverdi = loginSuksess;
		pakketype = PakkeType.LOGIN_SVAR_FRA_SERVER;
	}
	
	public Pakke(String[] tags)
	{
		nummer = tags.length;
		tekstliste = tags.clone();
		pakketype = PakkeType.ADMIN_SET_DEFAULT_TAGSLISTE;
	}

	public Pakke(String[] urls, boolean inkluder)
	{
		tekstliste = urls.clone();
		booliskverdi = inkluder;
		if(inkluder)
			pakketype = PakkeType.ADMIN_INKLUDER_URL_LISTE;
		else
			pakketype = PakkeType.ADMIN_EKSKLUDER_URL_LISTE;
	}
	
	public PakkeType getPakkeType()
	{
		return pakketype;
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
		else if(pakketype == PakkeType.URL_LISTE_OG_TAGS_ECHO_FRA_SERVER || pakketype == PakkeType.ADMIN_URL_LISTE_OG_TAGS_ECHO_FRA_SERVER)
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
		if(pakketype == PakkeType.URL_LISTE_OG_TAGS_ECHO_FRA_SERVER || pakketype == PakkeType.ADMIN_URL_LISTE_OG_TAGS_ECHO_FRA_SERVER)
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