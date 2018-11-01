package ru.rodnyan.requests;

import ru.rodnyan.model.AccessToken;
import ru.rodnyan.util.JsonUtil;

import java.io.IOException;

public class GetTokenRequest extends AbstractRequest {

	private final String TOKEN_URL = "https://accounts.spotify.com/api/token";

	public GetTokenRequest() {
		this.path = TOKEN_URL;
		setMethod(RequestType.POST);
	}


	@Override
	public AccessToken execute() throws IOException {
		return JsonUtil.fromJson((String) super.execute(), AccessToken.class);
	}

	@Override
	public void configureConnection() {
		super.configureConnection();
		addParameter("grant_type", "authorization_code");
		connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
	}
}
