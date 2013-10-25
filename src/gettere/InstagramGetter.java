package gettere;

import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Parsere.InstagramParserV2;

import server.Picture;
import interfaces.IGetter;

public class InstagramGetter implements IGetter {
	private String tag;
	
	public void createTagUrl(String t){
		this.tag = t;
		instagramUrl = "https://api.instagram.com/v1/tags/" + tag
				+ "/media/recent?client_id=94376837f7c1499cac000b277f13d7d4";
		
	}
	
	public String getInstagramUrl() {
		return instagramUrl;
	}

	private String instagramUrl;
	
	@Override
	public List<Picture> getJSON(String tag) throws IOException {
		createTagUrl(tag);
		URL url = new URL(instagramUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		InputStreamReader reader = new InputStreamReader(connection.getInputStream());
		InstagramParserV2 parser = new InstagramParserV2();
		
		List<Picture> pictures = parser.parse(reader);

		return pictures;
	}
	/*
	public InstagramGetter(String tag) {
		super();
		this.tag = tag;
		instagramUrl = "https://api.instagram.com/v1/tags/" + tag
				+ "/media/recent?client_id=94376837f7c1499cac000b277f13d7d4";
	}*/



}
