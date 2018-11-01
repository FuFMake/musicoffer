package ru.rodnyan.command;

import ru.rodnyan.SpotifyApi;

import java.io.IOException;

public class CommandManager {

	private SpotifyApi spotifyApi;

	public CommandManager(SpotifyApi api) {
		this.spotifyApi = api;
	}

	public void execute(Command command) {
		command.setApi(spotifyApi);
		try {
			command.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
