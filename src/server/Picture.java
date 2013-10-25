package server;

public class Picture {
	public String standardURL;
	public String thumbnailURL;
	public String[] tags;
    public String description;
    public String type;
	
	public String getStandardURL() {
		return standardURL;
	}
	public String getThumbnailURL() {
		return thumbnailURL;
	}
	public String[] getTags() {
		return tags;
	}
	public String getDescription() {
		return description;
	}
	public String getType() {
		return type;
	}
}
