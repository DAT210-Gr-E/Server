package interfaces;

import java.util.List;
import org.json.JSONObject;

import server.Picture;



public interface IGetter {
	public List<Picture> getJSON(JSONObject jo);
}