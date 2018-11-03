package ru.rodnyan;

import ru.rodnyan.command.CommandManager;
import ru.rodnyan.input.InputThread;

public class MusicApp {

	private static final String CLIENT_ID = "YOUR CLIENT_ID";
	private static final String CLIENT_SECRET = "YOUR CLIENT_SECRET";

	public static void main(String[] args) {
		SpotifyApi spotifyApi = new SpotifyApi.Builder()
				.clientId(CLIENT_ID)
				.clientSecret(CLIENT_SECRET)
				.port(8765)
				.build();

		System.out.println("Please provide access to the application.");
		System.out.println("URL:" + spotifyApi.getAuthLink());
		if (spotifyApi.auth()) {
			System.out.println("--------------");
			System.out.println("---SUCCESS!---");
			System.out.println("--------------");
			System.out.println("Every command must be ended with \";\" symbol");
			System.out.println("Use \"help; \" to see all available commands");
			System.out.println("Use \"stop; \" to stop program");

			CommandManager cm = new CommandManager(spotifyApi);

			new Thread(new InputThread(cm)).start();
		}

	}




}
