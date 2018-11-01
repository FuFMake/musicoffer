package ru.rodnyan;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import ru.rodnyan.exception.SpotifyApiBuildException;
import ru.rodnyan.model.AccessToken;
import ru.rodnyan.requests.GetCategoriesRequest;
import ru.rodnyan.requests.GetTokenRequest;
import ru.rodnyan.requests.RefreshTokenRequest;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SpotifyApi {

	private final String AUTHORIZE_URL = "https://accounts.spotify.com/authorize";
	private String REDIRECT_URI = "http://localhost";
	private final String RESPONSE_TYPE = "code";

	private String CLIENT_ID;
	private String CLIENT_SECRET;

	private AccessToken token;

	private HttpServer serverForCode;
	private GetCodeHandler getCodeHandler;

	private final int port;
	private String authLink;

	private String code;

	private String SCOPE = "";

	private SpotifyApi(int port, String clientId, String clientSecret) {
		this.port = port;
		REDIRECT_URI += ":" + port;
		this.CLIENT_ID = clientId;
		this.CLIENT_SECRET = clientSecret;
	}

	public String getAuthLink()  {
		if (authLink == null) {
			buildAuthLink();
		}
		return authLink;
	}

	private void buildAuthLink() {
		authLink =  AUTHORIZE_URL + "?"
				+ "client_id=" + CLIENT_ID
				+ "&redirect_uri=" + REDIRECT_URI
				+ "&response_type=" + RESPONSE_TYPE
				+ "&scope" + SCOPE;
	}

	public boolean auth()  {
		try {
			bootstrapServer();
			code = getCodeHandler.getCode();
			token = getToken(code);
			System.out.println("your refresh token is " + token.getRefresh_token());
			System.out.println("your access token is " + token.getAccess_token());
		} catch ( IOException | InterruptedException e) {
			e.printStackTrace();
			return false;
		} finally {
			shutdownServer();
		}
		return true;
	}

	private void shutdownServer() {
		serverForCode.stop(5);
	}

	private void refreshToken() {
		RefreshTokenRequest refreshRequest = new RefreshTokenRequest();
		refreshRequest.setToken(token);
		try {
			token = refreshRequest.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private AccessToken getToken(String code) throws IOException {
		GetTokenRequest tokenRequest = new GetTokenRequest();
		tokenRequest.addParameter("client_id", CLIENT_ID);
		tokenRequest.addParameter("client_secret", CLIENT_SECRET);
		tokenRequest.addParameter("redirect_uri", REDIRECT_URI);
		tokenRequest.addParameter("code", code);
		return tokenRequest.execute();
	}

	private void bootstrapServer() throws IOException {
		if (serverForCode == null) {
			getCodeHandler = new GetCodeHandler();
			serverForCode = HttpServer.create();
			serverForCode.bind(new InetSocketAddress(port), 0);
			HttpContext context = serverForCode.createContext("/", getCodeHandler);
			serverForCode.setExecutor(null);
		}
		serverForCode.start();
	}



	private void setScope(String scope) {
		this.SCOPE = scope;
	}





	//API METHODS
	public GetCategoriesRequest getCategories() {
		GetCategoriesRequest request = new GetCategoriesRequest();
		request.setToken(token);
		return request;
//		HttpURLConnection urlConnection = (HttpURLConnection) new URL("https://api.spotify.com/v1/browse/categories").openConnection();
//		urlConnection.setRequestMethod("GET");
//		urlConnection.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());
//		System.out.println(IOUtils.toString(urlConnection.getInputStream(), Charsets.UTF_8));

	}

	public static class Builder {

		private final int DEFAULT_PORT = 8765;

		private int port = DEFAULT_PORT;
		private String CLIENT_ID;

		private String CLIENT_SECRET;

		private String scope = "";

		public Builder clientId(String client) {
			this.CLIENT_ID = client;
			return this;
		}

		public Builder clientSecret(String clientS) {
			this.CLIENT_SECRET = clientS;
			return  this;
		}

		public Builder port(int port) {
			this.port = port;
			return this;
		}

		public Builder scope(String scope) {
			scope += scope + " ";
			return  this;
		}

		public SpotifyApi build() {
			if (this.CLIENT_SECRET == null || this.CLIENT_ID == null) {
				throw new SpotifyApiBuildException("client_id or client_secret cannot be null");
			}
			SpotifyApi spotifyApi = new SpotifyApi(port, CLIENT_ID, CLIENT_SECRET);
			spotifyApi.setScope(scope);
			return spotifyApi;
		}
	}
}
