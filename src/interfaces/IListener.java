package interfaces;

import server.Picture;

public interface IListener {
	abstract Picture[] getLinks (String[] hashtag);
	abstract boolean checkURL(Picture[] img);
}
