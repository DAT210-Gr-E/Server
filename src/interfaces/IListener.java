package interfaces;

import server.Picture;

public interface IListener {
	abstract Picture[] getLinks (String[] hashtag);
	abstract Picture[] checkURL(Picture[] img);
}
