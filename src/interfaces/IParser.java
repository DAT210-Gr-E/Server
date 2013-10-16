package interfaces;

import java.io.InputStreamReader;
import java.util.List;

import server.Picture;

public interface IParser {
	public List<Picture> parse(InputStreamReader reader);
}



