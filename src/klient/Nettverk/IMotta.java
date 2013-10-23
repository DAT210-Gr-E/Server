package klient.Nettverk;

import java.net.URL;

import org.json.JSONObject;

public interface IMotta extends Runnable {	
	
	//public Pakke getJSON(JSONObject jo);
	public URL[] getURLs();
	public URL[] getAdminURLs();
	public String[] getTags();
	public String[] getAdminTags();
	public boolean getLoginSuksess();
	public String getLoginPassord();
	public int getTidsInterval();
	public boolean[] getInkluderteURLer();
	public void run();
}