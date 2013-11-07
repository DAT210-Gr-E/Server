package klient.Nettverk;

import java.net.URL;

public interface ISend extends Runnable{
	public void spoertid(int id);
	public void spoerbilder(int id);
	public void spoerlogin(String passord, int id);
	public void spoertags(int id);
	public void spoeradminbilder(int id);
	public void sendtags(String[] tags);
	public void sendtid(int tid);
	public void sendinkluderte(URL[] urls, boolean[] inkludert);
	public void run();
}