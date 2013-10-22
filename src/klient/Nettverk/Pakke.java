package klient.Nettverk;

public class Pakke {

	private String[] url;
	private String[] tags;
	
	public Pakke(String[] u, String[] t)
	{
		url = u;
		tags = t;
	}

	public String[] getUrl()
	{
		return url;
	}
	
	public String[] getTags()
	{
		return tags;
	}
	
	
}
