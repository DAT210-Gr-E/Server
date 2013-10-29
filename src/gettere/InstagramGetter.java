package gettere;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Parsere.InstagramPicCountParser;
import Parsere.InstagramParser;

import server.Picture;
import interfaces.IGetter;

public class InstagramGetter implements IGetter {
	private String tag, countUrlString, instagramUrlString;
	private int media_count;

	private InstagramPicCountParser countParser;
	private InstagramParser mainParser;
	private InputStreamReader countReader, mainReader;
	private List<Picture> pictures;
	private HttpURLConnection countConnection, mainConnection;
	URL countUrl, instagramUrl;

	public void createUrls(String t){
		this.tag		= t;
		countUrlString		= "https://api.instagram.com/v1/tags/" + tag + "?client_id=94376837f7c1499cac000b277f13d7d4";
		instagramUrlString	= "https://api.instagram.com/v1/tags/" + tag + "/media/recent?client_id=94376837f7c1499cac000b277f13d7d4";
	}

	@Override
	public List<Picture> getJSON(String tag) throws IOException {
		createUrls(tag);
		picCount();
		pictures = new ArrayList<Picture>();

		firstParse();

		while(pictures.size() < media_count){
			nextParse();
		}
		return pictures;
	}

	private void picCount() throws IOException{
		countUrl = new URL(countUrlString);

		countConnection = (HttpURLConnection)countUrl.openConnection();
		countConnection.setRequestMethod("GET");
		countConnection.connect();

		countReader = new InputStreamReader(countConnection.getInputStream());
		countParser = new InstagramPicCountParser();

		media_count = countParser.parse(countReader);
		System.out.println("Antall bilder tagget med '" + tag + "': " + media_count);
	}

	private void firstParse() throws IOException{
		instagramUrl = new URL(instagramUrlString);
		System.out.println("current url: " + instagramUrl);
		
		mainConnection = (HttpURLConnection)instagramUrl.openConnection();
		mainConnection.setRequestMethod("GET");
		mainConnection.connect();

		mainReader = new InputStreamReader(mainConnection.getInputStream());
		mainParser = new InstagramParser();

		pictures = mainParser.parse(mainReader);
	}


	private void nextParse() throws IOException{
		String next = mainParser.getNext_max_tag_id();
		instagramUrl = new URL(instagramUrlString + "&" + next);
		System.out.println("next url: " + instagramUrl);

		mainConnection = (HttpURLConnection)instagramUrl.openConnection();
		mainConnection.setRequestMethod("GET");
		mainConnection.connect();

		mainReader = new InputStreamReader(mainConnection.getInputStream());

		List<Picture> tmp = mainParser.parse(mainReader);
		
		for (int i = 0; i < tmp.size(); i++) pictures.add(tmp.get(i));

	}

	public String getInstagramUrl() {
		return instagramUrlString;
	}
}
