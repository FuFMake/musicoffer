package ru.rodnyan.requests;

import ru.rodnyan.model.AccessToken;
import ru.rodnyan.util.JsonUtil;

import java.io.IOException;

public class RefreshTokenRequest extends AbstractRequest {

	private final String TOKEN_URL = "https://accounts.spotify.com/api/token";

	public RefreshTokenRequest() {
		path = TOKEN_URL;
		setMethod(RequestType.POST);
	}

	@Override
	public AccessToken execute() throws IOException {
		return JsonUtil.fromJson((String) super.execute(), AccessToken.class);
	}

	@Override
	public void configureConnection() {
		super.configureConnection();
		addParameter("grant_type", "refresh_token");
		addParameter("refresh_token", token.getRefresh_token());
		connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
	}
}
