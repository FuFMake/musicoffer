package ru.rodnyan.model;

public class Album {

	private ExternalURL external_urls;
	private String name;
	private Artist[] artists;

	public Artist[] getArtists() {
		return artists;
	}

	public void setArtists(Artist[] artists) {
		this.artists = artists;
	}

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
