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
	private String tag, countUrlString, instagramUrlString, next_max_tag_id;
	private int media_count, limit_pics = 100;

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
		pictures = new ArrayList<Picture>();
		picCount();
		if (media_count < limit_pics) limit_pics = media_count;
		
		firstParse();
		while(pictures.size() < limit_pics && !next_max_tag_id.equals("")){
			nextParse();
		}
		return pictures;
	}

	//Skal rydde opp i disse metodene
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
		
		mainConnection = (HttpURLConnection)instagramUrl.openConnection();
		mainConnection.setRequestMethod("GET");
		mainConnection.connect();
		mainReader = new InputStreamReader(mainConnection.getInputStream());
		mainParser = new InstagramParser();

		pictures = mainParser.parse(mainReader, limit_pics);
		next_max_tag_id = mainParser.getNext_max_tag_id();
	}


	private void nextParse() throws IOException{
		instagramUrl = new URL(instagramUrlString + "&max_tag_id=" + next_max_tag_id);

		mainConnection = (HttpURLConnection)instagramUrl.openConnection();
		mainConnection.setRequestMethod("GET");
		mainConnection.connect();
		mainReader = new InputStreamReader(mainConnection.getInputStream());
		
		List<Picture> tmp = mainParser.parse(mainReader, limit_pics);
		for (int i = 0; i < tmp.size(); i++) pictures.add(tmp.get(i));
		next_max_tag_id = mainParser.getNext_max_tag_id();
	}

	public String getInstagramUrl() {
		return instagramUrlString;
	}
}
