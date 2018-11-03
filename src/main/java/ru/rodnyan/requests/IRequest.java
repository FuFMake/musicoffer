package ru.rodnyan.requests;

import java.io.IOException;

public interface IRequest {

	<T> T execute() throws IOException;

	void addParameter(String key, String value);

	void setMethod(RequestType type);

	void configureConnection();

	void addQueryParameter(String key, String value);
}
