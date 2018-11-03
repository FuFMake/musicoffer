package ru.rodnyan.requests;

import ru.rodnyan.model.AlbumWrapper;
import ru.rodnyan.util.JsonUtil;

import java.io.IOException;

public class NewReleasesRequest extends AbstractRequest{

	public NewReleasesRequest() {
		path += "/browse/new-releases";
	}

	@Override
	public AlbumWrapper execute() throws IOException {
		return JsonUtil.fromJson(super.execute(), AlbumWrapper.class);
	}
}
