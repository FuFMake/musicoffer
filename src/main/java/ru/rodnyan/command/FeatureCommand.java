package ru.rodnyan.command;

import ru.rodnyan.model.PlaylistWrapper;
import ru.rodnyan.requests.FeaturedPlayilistsRequest;

import java.io.IOException;
import java.util.Arrays;

public class FeatureCommand extends AbstractCommand {

	public FeatureCommand(String args) {
		super(args);
	}

	@Override
	public void execute() throws IOException {
		FeaturedPlayilistsRequest featured = api.getFeatured();
		featured.addQueryParameter("limit", "50");
		PlaylistWrapper wrapper = featured.execute();
		System.out.println("----" + wrapper.getMessage() + "----");
		Arrays.stream(wrapper.getPlaylists().getItems()).forEach(playlist -> {
			System.out.println(playlist.getName() + "\t" + playlist.getExternal_urls().getSpotify());
		});
	}
}
