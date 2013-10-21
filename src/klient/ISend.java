package klient;

import org.json.JSONObject;

public interface ISend {
	public void send(String[] tags);
	public void poke();
	public void run();
}