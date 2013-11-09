package interfaces;

import java.io.IOException;
import java.util.List;

import server.Picture;

public interface IGetter {
	public List<Picture> getPictureList(String tag) throws IOException;
}