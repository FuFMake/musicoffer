package ru.rodnyan.requests;

import ru.rodnyan.model.PlaylistWrapper;
import ru.rodnyan.util.JsonUtil;

import java.io.IOException;

public class FeaturedPlayilistsRequest extends AbstractRequest {

	public FeaturedPlayilistsRequest() {
		path += "/browse/featured-playlists";
		setMethod(RequestType.GET);
	}

	@Override
	public PlaylistWrapper execute() throws IOException {
		return JsonUtil.fromJson(super.execute(), PlaylistWrapper.class);
	}
}
