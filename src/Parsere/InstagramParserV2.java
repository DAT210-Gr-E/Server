package Parsere;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import server.Picture;

import com.google.gson.*;

public class InstagramParserV2 {

	public List<Picture> parse(InputStreamReader reader) throws IOException{
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(reader).getAsJsonObject();
		JsonArray jsonPictures = obj.get("data").getAsJsonArray();

		for(JsonElement j : jsonPictures) {
			Picture picture = new Picture();
			JsonObject jsonPicture = j.getAsJsonObject();

			String type = jsonPicture.getAsJsonObject().get("type").getAsString();
			picture.type = type;/*Kan fjernes, men beholdes foreløpig*/
			//if (type.equals("image")){
			JsonElement images = jsonPicture.get("images");
			JsonElement image = images.getAsJsonObject().get("standard_resolution");
			String url = image.getAsJsonObject().get("url").getAsString();
			picture.standardURL = url;

			image = images.getAsJsonObject().get("thumbnail");
			url = image.getAsJsonObject().get("url").getAsString();
			picture.thumbnailURL = url;

			JsonElement caption = jsonPicture.get("caption");
			String text = caption.getAsJsonObject().get("text").getAsString();
			picture.description = text;
			
			pictures.add(picture);
			//}

		}
		return pictures;
	}
}
