package interfaces;

import java.io.IOException;
import java.util.List;
import org.json.JSONObject;

import server.Picture;



public interface IGetter {
	public List<Picture> getJSON(String tag) throws IOException;
}