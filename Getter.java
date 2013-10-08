package getters;

import java.util.List;
import us.monoid.json.JSONObject;

public interface Getter {
	public List<Picture> getJSON(JSONObject jo);
}