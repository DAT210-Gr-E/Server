package klient.Nettverk;

import java.io.Serializable;

public class Pakke implements Serializable {

	private static final long serialVersionUID = 2L;

	public enum PakkeType{
		// PakkeType											   K  S		Metoder for gitt PakkeType											Konstruktør for gitt Pakketype
		SPOER_OM_TID,											//  ->		getPakkeType, getTransaksjonsId										(int, TransaksjonsType.TID)
		SVAR_TID,												//  <-		getPakkeType, getTransaksjonsId, getTid								(int, TransaksjonsType.TID, int)
		SPOER_OM_BILDER,										// 	->		getPakkeType, getTransaksjonsId,									(int, TransaksjonsType.BILDER)
		SVAR_BILDER,											// 	<-		getPakkeType, getTransaksjonsId, getURLs							(int, TransaksjonsType.BILDER, String[])
		SPOER_OM_LOGIN,											//  ->		getPakkeType, getTransaksjonsId, getPassord							(int, TransaksjonsType.LOGIN, String)
		SVAR_LOGIN,												//  <-		getPakkeType, getTransaksjonsId, getPassord, getSuccess				(int, TransaksjonsType.LOGIN, String, boolean)
		SPOER_OM_TAGS,											//  ->		getPakkeType, getTransaksjonsId,									(int, TransaksjonsType.TAGS)
		SVAR_TAGS,												//  <-		getPakkeType, getTransaksjonsId, getTags							(int, TransaksjonsType.TAGS, String[])
		ADMIN_SPOER_OM_BILDER,									//  ->		getPakkeType, getTransaksjonsId, 									(int, TransaksjonsType.ADMIN_BILDER)
		ADMIN_SVAR_BILDER,										//  <-		getPakkeType, getTransaksjonsId, getURLs, getInkluderte				(int, TransaksjonsType.ADMIN_BILDER, String[], Boolean[])
		ADMIN_SET_DEFAULT_TAGS,									//  ->		getPakkeType, getTransaksjonsId, getTags							(int, TransaksjonsType.ADMIN_SET, String[])
		ADMIN_SET_DEFAULT_TID,									//  ->		getPakkeType, getTransaksjonsId, getTid								(int, TransaksjonsType.ADMIN_SET, int)
		ADMIN_SET_SVARTELISTE,									//  ->		getPakkeType, getTransaksjonsId, getURLs, getInkluderte				(int, TransaksjonsType.ADMIN_SET, String[], Boolean[] )
		KVITTERING,												//  ->		getPakkeType														(int, TransaksjonsType.KVITTERING )
		UGYLDIG_PAKKE											//  <>		getPakkeType														alt annet
	};

	public enum TransaksjonsType{
		TID,
		BILDER,
		LOGIN,
		TAGS,
		ADMIN_BILDER,
		ADMIN_SET,
		KVITTERING,
		UGYLDIG_PAKKE,
	};

	private String[] tekstliste;
	private int nummer;
	private boolean booliskverdi[];
	private PakkeType pakketype = PakkeType.UGYLDIG_PAKKE;
	private int transaksjonsid;
	
	public Pakke(int id, TransaksjonsType type)
	{
		transaksjonsid = id;
		if(type == TransaksjonsType.TID)
			pakketype = PakkeType.SPOER_OM_TID;
		if(type == TransaksjonsType.BILDER)
			pakketype = PakkeType.SPOER_OM_BILDER;
		if(type == TransaksjonsType.TAGS)
			pakketype = PakkeType.SPOER_OM_TAGS;
		if(type == TransaksjonsType.ADMIN_BILDER)
			pakketype = PakkeType.ADMIN_SPOER_OM_BILDER;
		if(type == TransaksjonsType.KVITTERING)
			pakketype = PakkeType.KVITTERING;
		if(type == TransaksjonsType.UGYLDIG_PAKKE)
			pakketype = PakkeType.UGYLDIG_PAKKE;

	}

	public Pakke(int id, TransaksjonsType type, int millisekunder)
	{
		nummer = millisekunder;
		transaksjonsid = id;
		if(type == TransaksjonsType.TID)
			pakketype = PakkeType.SVAR_TID;
		if(type == TransaksjonsType.ADMIN_SET)
			pakketype = PakkeType.ADMIN_SET_DEFAULT_TID;
	}

	public Pakke(int id, TransaksjonsType type, String[] liste)
	{
		tekstliste = liste.clone();
		transaksjonsid = id;

		if(type == TransaksjonsType.BILDER)
			pakketype = PakkeType.SVAR_BILDER;
		if(type == TransaksjonsType.TAGS)
			pakketype = PakkeType.SVAR_TAGS;
		if(type == TransaksjonsType.ADMIN_SET)
			pakketype = PakkeType.ADMIN_SET_DEFAULT_TAGS;
	}


	public Pakke(int id, TransaksjonsType type, String passord)
	{
		tekstliste = new String[1];
		tekstliste[0] = passord;
		transaksjonsid = id;

		if(type == TransaksjonsType.LOGIN)
			pakketype = PakkeType.SPOER_OM_LOGIN;
	}

	public Pakke(int id, TransaksjonsType type, String passord, boolean loginSuksess)
	{
		tekstliste = new String[1];
		tekstliste[0] = passord;
		booliskverdi = new boolean[1];
		booliskverdi[0] = loginSuksess;
		transaksjonsid = id;
		
		if(type == TransaksjonsType.LOGIN)
			pakketype = PakkeType.SVAR_LOGIN;
	}

	public Pakke(int id, TransaksjonsType type, String[] urls, boolean[] inkluder)
	{
		tekstliste = urls.clone();
		booliskverdi = inkluder;
		transaksjonsid = id;
		
		if(type == TransaksjonsType.ADMIN_BILDER)
			pakketype = PakkeType.ADMIN_SVAR_BILDER;
		if(type == TransaksjonsType.ADMIN_SET)
			pakketype = PakkeType.ADMIN_SET_SVARTELISTE;
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
		if(pakketype == PakkeType.SVAR_TID || pakketype == PakkeType.ADMIN_SET_DEFAULT_TID)
			return nummer;
		else
			return -1;
	}

	public String[] getUrls()
	{
		if(pakketype == PakkeType.SVAR_BILDER || pakketype == PakkeType.ADMIN_SVAR_BILDER || pakketype == PakkeType.ADMIN_SET_SVARTELISTE)
			return tekstliste;
		else
			return null;
	}

	public String[] getTags()
	{
		if(pakketype == PakkeType.SVAR_TAGS || pakketype == PakkeType.ADMIN_SET_DEFAULT_TAGS)
			return tekstliste;
		else
			return null;
	}

	public String getPassord()
	{
		if(pakketype == PakkeType.SPOER_OM_LOGIN || pakketype == PakkeType.SVAR_LOGIN)
			return tekstliste[0];
		else
			return null;
	}

	public boolean getSuksess()
	{
		if(pakketype == PakkeType.SVAR_LOGIN)
			return booliskverdi[0];
		else
			return false;
	}

	public boolean[] getInkluderte()
	{
		if(pakketype == PakkeType.ADMIN_SVAR_BILDER || pakketype == PakkeType.ADMIN_SET_SVARTELISTE)
			return booliskverdi;
		else
			return null;
	}
}