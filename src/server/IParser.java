package server;

import java.io.InputStreamReader;
import java.util.List;

public interface IParser {
	public List<Picture> parse(InputStreamReader reader);
}


