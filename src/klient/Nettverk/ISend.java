package klient.Nettverk;

import org.json.JSONObject;

public interface ISend extends Runnable{
	public void send(String[] tags);
	public void sendadmin(String[] tags);
	public void poke();
	public void sendLogin(String passord);
	public void run();
}