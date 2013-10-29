package gettere;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Parsere.InstagramPicCountParser;
import Parsere.InstagramParser;

import server.Picture;
import interfaces.IGetter;

public class InstagramGetter implements IGetter {
	private String tag;
	private String tagInfoUrl;
	private String instagramUrl;
	
	public void createUrls(String t){
		this.tag		= t;
		tagInfoUrl		= "https://api.instagram.com/v1/tags/" + tag + "?client_id=94376837f7c1499cac000b277f13d7d4";
		instagramUrl	= "https://api.instagram.com/v1/tags/" + tag + "/media/recent?client_id=94376837f7c1499cac000b277f13d7d4";
	}
	
	@Override
	public List<Picture> getJSON(String tag) throws IOException {
		createUrls(tag);
		
		URL tagUrl = new URL(tagInfoUrl);
		HttpURLConnection tagConnection = (HttpURLConnection)tagUrl.openConnection();
		tagConnection.setRequestMethod("GET");
		tagConnection.connect();
		
		InputStreamReader reader = new InputStreamReader(tagConnection.getInputStream());
		InstagramPicCountParser parser = new InstagramPicCountParser();
		int media_count = parser.parse(reader);
		
		System.out.println("Antall bilder med denne taggen: " + media_count);
		
		List<Picture> pictures = new ArrayList<Picture>();
		
		URL url = new URL(instagramUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		
		InputStreamReader reader2 = new InputStreamReader(connection.getInputStream());
		InstagramParser parser2 = new InstagramParser();
		pictures = parser2.parse(reader2);
		return pictures;
	}
	
	public String getInstagramUrl() {
		return instagramUrl;
	}
}
