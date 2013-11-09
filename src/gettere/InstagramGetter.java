package gettere;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Parsere.InstagramParser;

import server.Picture;
import interfaces.IGetter;

public class InstagramGetter implements IGetter {
	String tag, urlString, next_max_tag_id = "";
	int limit_pics = 100;
	int queries = 0;

	InstagramParser parser;
	InputStreamReader reader;
	List<Picture> tmp, pictures;
	HttpURLConnection conn;
	URL url;

	@Override
	public List<Picture> getPictureList(String tag) throws IOException {
		this.tag = tag;		
		pictures = new ArrayList<Picture>();
		parser = new InstagramParser();
		//System.out.println(tag.toUpperCase() + ":");

		do {
			get();
		} while(parser.picCounter < limit_pics && !next_max_tag_id.equals(""));
		queries += parser.queryCounter;
		//System.out.println("Antall bilder hentet tagget med '" + tag + "': " + parser.picCounter);
		//System.out.println("Antall queries: " + parser.queryCounter + ".");
		//System.out.println("Samlet antall queries: " + queries + ".\n");
		return pictures;
	}

	private void get() throws IOException{
		if (next_max_tag_id.equals("")) url = new URL(createUrl(tag));
		else url = new URL(createUrls(tag, next_max_tag_id));
		
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
		reader = new InputStreamReader(conn.getInputStream());
		
		tmp = parser.parse(reader, limit_pics);
		for (int i = 0; i < tmp.size(); i++){
			tmp.get(i).tag = tag;
			pictures.add(tmp.get(i));
		}
		next_max_tag_id = parser.next_max_tag_id;
	}
	
	public String createUrl(String tag){
		return "https://api.instagram.com/v1/tags/" + tag + "/media/recent?client_id=94376837f7c1499cac000b277f13d7d4";
	}
	
	public String createUrls(String tag, String tag_id){
		return "https://api.instagram.com/v1/tags/" + tag + "/media/recent?client_id=94376837f7c1499cac000b277f13d7d4&max_tag_id=" + next_max_tag_id;
	}
}
