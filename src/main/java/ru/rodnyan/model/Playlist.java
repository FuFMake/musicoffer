package ru.rodnyan.model;

public class Playlist {

//	private boolean collaborative;
	private ExternalURL external_urls;
//	private String href;
//	private String id;
//	private Icon[] images;
	private String name;

	public ExternalURL getExternal_urls() {
		return external_urls;
	}

	public void setExternal_urls(ExternalURL external_urls) {
		this.external_urls = external_urls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
