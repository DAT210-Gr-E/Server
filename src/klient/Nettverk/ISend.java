package klient.Nettverk;

public interface ISend extends Runnable{
	public void send(String[] tags);
	public void send(int tid);
	public void sendadmin(String[] tags);
	public void sendadmindefault(String[] tags);
	public void sendadmininkludert(String[] tags, boolean[] inkludert);
	public void poke();
	public void sendLogin(String passord);
	public void run();
}