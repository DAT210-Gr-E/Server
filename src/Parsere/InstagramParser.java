package Parsere;

import java.util.ArrayList;
import java.util.List;

import server.Picture;
import sun.applet.Main;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import interfaces.IParser;

public class InstagramParser implements IParser {

	@Override
	public List<Picture> parse(String tag) {
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		Resty r = new Resty();
		try {
			
			JSONResource s = r
					.json("https://api.instagram.com/v1/tags/"
							+ tag + "/media/recent?client_id=94376837f7c1499cac000b277f13d7d4");
			JSONArray objectArray = s.array();
			for (int i = 0; i < objectArray.length(); i++) {
				JSONObject jObject = objectArray.getJSONObject(i);
				
				Picture p = new Picture();
				p.setUrl(jObject.getString(""));
				
				/*
				String[] tags = new String[];
				p.setTags(tags[]);
				*/
				
				pictures.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pictures;
	}
}
