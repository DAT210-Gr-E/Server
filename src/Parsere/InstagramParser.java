package Parsere;

import interfaces.IParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import server.Picture;

import com.google.gson.*;

public class InstagramParser implements IParser{	
	int counter = 0;
	
	public List<Picture> parse(InputStreamReader reader) throws IOException{
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(reader).getAsJsonObject();
		
		JsonElement pagination = obj.get("pagination");
		String nextUrl = pagination.getAsJsonObject().get("next_url").getAsString();
		System.out.println(nextUrl);
		
		JsonArray jsonPictures = obj.get("data").getAsJsonArray();

		for(JsonElement j : jsonPictures) {
			Picture picture = new Picture();
			JsonObject jsonPicture = j.getAsJsonObject();

			String type = jsonPicture.getAsJsonObject().get("type").getAsString();
			if (type.equals("image")){
				JsonElement images = jsonPicture.get("images");
				JsonElement image = images.getAsJsonObject().get("standard_resolution");
				String url = image.getAsJsonObject().get("url").getAsString();
				picture.standardURL = url;

				image = images.getAsJsonObject().get("thumbnail");
				url = image.getAsJsonObject().get("url").getAsString();
				picture.thumbnailURL = url;
				pictures.add(picture);
			}
			counter++;
		}
		System.out.println(counter);
		return pictures;
	}
}
