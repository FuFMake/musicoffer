package ru.rodnyan.requests;

import ru.rodnyan.model.PlaylistPage;
import ru.rodnyan.model.PlaylistWrapper;
import ru.rodnyan.util.JsonUtil;

import java.io.IOException;

public class GetCategoryPlaylistRequest extends AbstractRequest {

	public GetCategoryPlaylistRequest() {
		setMethod(RequestType.GET);
	}

	@Override
	public PlaylistPage execute() throws IOException {
		String json = super.execute();
//		System.out.println(json);
		return JsonUtil.fromJson(json, PlaylistWrapper.class).getPlaylists();
	}

	@Override
	public void configureConnection() {
		super.configureConnection();
	}


}
