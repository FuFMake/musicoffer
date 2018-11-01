package ru.rodnyan.requests;

import java.io.IOException;

public class GetCategoriesRequest extends AbstractRequest {

	public GetCategoriesRequest() {
		path += "/browse/categories";
		setMethod(RequestType.GET);
	}

	@Override
	public <T> T execute() throws IOException {
		return super.execute();
	}

	@Override
	public void configureConnection() {
		super.configureConnection();
		connection.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());
	}
}
