package getters;

import java.io.InputStreamReader;
import java.util.List;

public interface Parser {
	public List<Picture> parse(InputStreamReader reader);
}
