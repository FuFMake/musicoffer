package ru.rodnyan;

public class MusicApp {

	private static final String CLIENT_ID = "a19ee7dbfda443b2a8150c9101bfd645";
	private static final String CLIENT_SECRET = "b31e02adb0ab416eb8e763b9684676c8";

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
