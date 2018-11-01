package ru.rodnyan.requests;

public enum RequestType {
	GET("GET"),
	POST("POST");

	private String method;

	RequestType(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return method;
	}
}
