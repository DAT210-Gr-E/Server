package klient.Nettverk;

import java.net.URL;

public interface IMotta extends Runnable {	
	
	//public Pakke getJSON(JSONObject jo);
	public URL[] getURLs();
	public URL[] getAdminURLs();
	public String[] getTags();
	public boolean getLoginSuksess();
	public String getLoginPassord();
	public int getTidsInterval();
	public boolean[] getInkluderteURLer();
	public int getID(int type);
	public int getIDr(int type);
	public void run();
}