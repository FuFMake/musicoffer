package ru.rodnyan.model;

public class AlbumWrapper {
	private String message = "No message";

	private AlbumPage albums;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AlbumPage getAlbums() {
		return albums;
	}

	public void setAlbums(AlbumPage albums) {
		this.albums = albums;
	}
}
