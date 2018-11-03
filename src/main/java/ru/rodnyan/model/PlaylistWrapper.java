package ru.rodnyan.model;

public class PlaylistWrapper {

	private PlaylistPage playlists;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PlaylistPage getPlaylists() {
		return playlists;
	}

	public void setPlaylists(PlaylistPage playlists) {
		this.playlists = playlists;
	}
}
