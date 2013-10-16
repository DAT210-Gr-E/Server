package databaseKommunikasjon;

import java.io.InputStreamReader;
import java.util.List;

import server.Picture;

public interface Parser {
	public List<Picture> parse(InputStreamReader reader);
}
