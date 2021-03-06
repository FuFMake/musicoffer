package ru.rodnyan.requests;

import org.apache.commons.io.IOUtils;
import ru.rodnyan.model.AccessToken;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRequest implements IRequest {

	protected HttpURLConnection connection;
	protected String path = "https://api.spotify.com/v1";
	private RequestType type = RequestType.GET;

	private HashMap<String, String> params = new HashMap<>();
	private HashMap<String, String> queryParams = new HashMap<>();

	protected AccessToken token;
	protected boolean tokenRequired = true;

	@Override
	public <T> T execute() throws IOException {
//		path = URLEncoder.encode(path, "UTF-8");
		if (!queryParams.isEmpty()) {
			String query = "?";
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				query += entry.getKey() + "=" + entry.getValue() + "&";
			}
			query = query.substring(0, query.length() - 1);
			path += query;
		}
//		System.out.println(path);
		connection = (HttpURLConnection) new URL(path).openConnection();
		connection.setRequestMethod(type.toString());
		configureConnection();
		connection.setUseCaches( false );
		if (type == RequestType.POST) {
			connection.setDoOutput(true);
			StringBuilder paramStr = new StringBuilder();
			for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
				paramStr.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("&");
			}
			if (paramStr.length() > 0)
				paramStr.deleteCharAt(paramStr.length() - 1);
			byte[] data = paramStr.toString().getBytes(StandardCharsets.UTF_8);
			connection.setRequestProperty( "charset", "utf-8");
			connection.setRequestProperty( "Content-Length", Integer.toString( data.length ));
			try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
				wr.write( data );
			}
		}
		String json = IOUtils.toString(connection.getInputStream(), Charset.forName("UTF-8"));
//		System.out.println("JSON HAS BEEN ACCEPTED " + json);
		connection.disconnect();
		return (T) json;
	}

	@Override
	public void addParameter(String key, String value) {
		this.params.put(key, value);
	}


	@Override
	public void addQueryParameter(String key, String value) {
		this.queryParams.put(key, value);
	}

	@Override
	public void setMethod(RequestType type) {
		this.type = type;
	}

	@Override
	public void configureConnection() {
		if (tokenRequired) {
			connection.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setToken(AccessToken token) {
		this.token = token;
	}
}
