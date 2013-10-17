package Parsere;

import java.util.ArrayList;
import java.util.List;

import server.Picture;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import interfaces.IParser;

public class TwitterParser implements IParser {

	@Override
	public List<Picture> parse(String tag) {
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		Resty r = new Resty();
		try {
			
			// TwitPic API Key:	ffb574a70ecb566bae7fca3610cdb9a4, usikker på om denne trengs til noe?
			JSONResource s = r
					.json(/*url*/ tag /*url*/);
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
