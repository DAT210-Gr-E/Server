package klient.Nettverk;

import java.net.URL;

import org.json.JSONObject;

public interface IMotta extends Runnable {	
	
	//public Pakke getJSON(JSONObject jo);
	public URL[] getURLs();
	public String[] getTags();
	public boolean erLoginKorrekt();
	public void run();
}