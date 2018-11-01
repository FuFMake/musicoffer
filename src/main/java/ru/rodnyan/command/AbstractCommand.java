package ru.rodnyan.command;

import ru.rodnyan.SpotifyApi;

public abstract class AbstractCommand implements Command {

	protected String args;
	protected SpotifyApi api;

	public AbstractCommand(String args) {
		this.args = args;
	}

	@Override
	public void setApi(SpotifyApi api) {
		this.api = api;
	}
}
