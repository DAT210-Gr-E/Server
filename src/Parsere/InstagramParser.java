package Parsere;

import interfaces.IParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.Picture;

import com.google.gson.*;

public class InstagramParser implements IParser{	
	public int picCounter = 0, queryCounter = 0, currentPicCounter;
	public String next_max_tag_id;

	public List<Picture> parse(InputStreamReader reader, int limit_pics) throws IOException{
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(reader).getAsJsonObject();

		JsonElement pagination = obj.get("pagination");
		if (pagination.getAsJsonObject().has("next_max_tag_id")) next_max_tag_id = pagination.getAsJsonObject().get("next_max_tag_id").getAsString();
		else next_max_tag_id = "";

		JsonArray jsonPictures = obj.get("data").getAsJsonArray();
		currentPicCounter = 0;
		
		for(JsonElement j : jsonPictures) {
			if (picCounter < limit_pics){
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

					JsonElement likes = jsonPicture.get("likes");
					int count = likes.getAsJsonObject().get("count").getAsInt();
					picture.likes = count;
					
					long unixTime = jsonPicture.getAsJsonObject().get("created_time").getAsLong();
					Date date = new Date(unixTime*1000L);
					picture.created_time = date;
					
					picture.platform = "Instagram";
					
					pictures.add(picture);

					picCounter++;
					currentPicCounter++;
				}
			}
		}
		queryCounter++;
		System.out.println("Query nr. " + queryCounter + " gjennomført. " + currentPicCounter + " bilder ble hentet.");
		return pictures;
	}
}
