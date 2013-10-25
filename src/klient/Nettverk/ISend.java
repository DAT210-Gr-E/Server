package klient.Nettverk;

import java.net.URL;

public interface ISend extends Runnable{
	public void send(String[] tags, int id);
	public void send(int tid, int id);
	public void sendadmin(String[] tags, int id);
	public void sendadmindefault(String[] tags, int id);
	public void sendadmininkludert(URL[] urls, boolean[] inkludert, int id);
	public void poke(int id);
	public void sendLogin(String passord, int id);
	public void run();
}