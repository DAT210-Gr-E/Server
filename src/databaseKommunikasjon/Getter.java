package databaseKommunikasjon;

import java.util.List;

import org.json.JSONObject;

import server.Picture;

public interface Getter {
	public List<Picture> getJSON(JSONObject jo);
}