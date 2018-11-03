package ru.rodnyan.command;

import ru.rodnyan.model.AlbumWrapper;
import ru.rodnyan.requests.NewReleasesRequest;

import java.io.IOException;
import java.util.Arrays;

public class NewReleasesCommand  extends AbstractCommand{

	public NewReleasesCommand(String args) {
		super(args);
	}

	@Override
	public void execute() throws IOException {
		NewReleasesRequest featured = api.getNewReleases();
		featured.addQueryParameter("limit", "50");
		AlbumWrapper wrapper = featured.execute();
		System.out.println("----" + wrapper.getMessage() + "----");
		Arrays.stream(wrapper.getAlbums().getItems()).forEach(album -> {
			System.out.println(album.getName() + "\t" + Arrays.toString(album.getArtists()) + "\t"
					+ album.getExternal_urls().getSpotify());
		});

	}
}
