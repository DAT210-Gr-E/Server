package server;

public class Picture {
	public String standardURL;
	public String thumbnailURL;
	public String[] tags;
    public String description;
    public String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStandardURL() {
		return standardURL;
	}
	public void setStandardURL(String standardURL) {
		this.standardURL = standardURL;
	}
	public String getThumbnailURL() {
		return thumbnailURL;
	}
	public void setThumbnailURL(String lowThumbnailURL) {
		this.thumbnailURL = lowThumbnailURL;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
}
