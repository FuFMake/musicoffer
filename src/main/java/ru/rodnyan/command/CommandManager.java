package ru.rodnyan.command;

import ru.rodnyan.SpotifyApi;

import java.io.IOException;

public class CommandManager {

	private SpotifyApi spotifyApi;

	private Command previousCommand;

	public CommandManager(SpotifyApi api) {
		this.spotifyApi = api;
	}

	public void execute(Command command) {
		command.setApi(spotifyApi);
		try {
			command.execute();
			previousCommand = command;
		} catch (IOException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
