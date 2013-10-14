package server;

public interface IListener {
	abstract Picture[] getLinks (String[] hashtag);
	abstract boolean checkURL(Picture[] img);
}
