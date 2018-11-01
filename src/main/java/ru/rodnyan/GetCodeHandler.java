package ru.rodnyan;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class GetCodeHandler implements HttpHandler {

	private String code = "";
	private volatile boolean isRecieved = false;
	private final String DEFAULT_RESPONSE = "\n" +
			"Please do not tell anyone the data from the URL of the page";

	@Override
	public  synchronized void  handle(HttpExchange httpExchange) throws IOException {
		URI requestURI = httpExchange.getRequestURI();
		String query = requestURI.getQuery();
		code = query.split("=")[1];
		httpExchange.sendResponseHeaders(200, DEFAULT_RESPONSE.length());
		OutputStream os = httpExchange.getResponseBody();
		os.write(DEFAULT_RESPONSE.getBytes());
		os.close();
		isRecieved = true;
		notify();
	}

	public synchronized String getCode() throws InterruptedException {
		while (!isRecieved) {
			wait();
		}
		return code;

	}
}
