package gettere;

import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Parsere.InstagramParser;

import server.Picture;
import interfaces.IGetter;

public class InstagramGetter implements IGetter {

	private String tag = "nofilter";
	private String instagramUrl = "https://api.instagram.com/v1/tags/" + tag + "/media/recent?client_id=CLIENTID";

	@Override
	public List<Picture> getJSON(String tag) throws IOException {
		createTagUrl(tag);
		URL url = new URL(instagramUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		InputStreamReader reader = new InputStreamReader(connection.getInputStream());

		InstagramParser parser = new InstagramParser();
		// List<Picture> pictures = parser.parse(reader); //må gjøre om på InstagramParser-klassen først

		return null; //pictures;
	}
	public void createTagUrl(String tag){
		this.tag = tag;
		instagramUrl = "https://api.instagram.com/v1/tags/" + tag + "/media/recent?client_id=CLIENTID";
		
	}

}
