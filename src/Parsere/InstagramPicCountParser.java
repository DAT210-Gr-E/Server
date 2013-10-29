package Parsere;

import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.*;

public class InstagramPicCountParser {	
	public int parse(InputStreamReader reader) throws IOException{
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(reader).getAsJsonObject();
		
		JsonObject tagInfo = obj.get("data").getAsJsonObject();
		int media_count = tagInfo.getAsJsonObject().get("media_count").getAsInt();
		
		return media_count;
	}
}
