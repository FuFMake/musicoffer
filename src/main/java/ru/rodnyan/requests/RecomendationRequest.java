package ru.rodnyan.requests;

import java.io.IOException;

public class RecomendationRequest extends AbstractRequest {

	public RecomendationRequest() {
		setMethod(RequestType.GET);
		path = "https://api.spotify.com/v1/recommendations";
	}

	@Override
	public <T> T execute() throws IOException {
		return super.execute();
	}
}
