package ru.rodnyan;

public class MusicApp {

	private static final String CLIENT_ID = "YOUR CLIENT ID";
	private static final String CLIENT_SECRET = "YOUR CLIENT SECRET";

	public static void main(String[] args) throws Exception {
		SpotifyApi spotifyApi = new SpotifyApi.Builder()
				.clientId(CLIENT_ID)
				.clientSecret(CLIENT_SECRET)
				.port(8765)
				.build();

		System.out.println("Please provide access to the application.");
		System.out.println("URL:" + spotifyApi.getAuthLink());
		if (spotifyApi.auth()) {
			System.out.println("SUCCESS!");
			spotifyApi.getCategories().execute();
		}

	}




}
