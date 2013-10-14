package server;

import java.util.List;
import org.json.JSONObject;



public interface IGetter {
	public List<Picture> getJSON(JSONObject jo);
}