package ru.rodnyan.command;

import ru.rodnyan.model.CategoriesPage;
import ru.rodnyan.model.Category;
import ru.rodnyan.model.PlaylistPage;
import ru.rodnyan.requests.GetCategoryPlaylistRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class GetCategoryPlaylistCommand extends AbstractCommand{

	public GetCategoryPlaylistCommand(String args) {
		super(args);
	}

	@Override
	public void execute() throws IOException {
		CategoriesPage page = api.getCategories().execute();
		Optional<Category> first = Arrays.stream(page.getItems()).filter(category -> category.getName().equals(args)).findFirst();
		if (!first.isPresent()) {
			throw new IllegalArgumentException("No such category with name " + args);
		}
		GetCategoryPlaylistRequest categoryPlaylists = api.getCategoryPlaylists();
		categoryPlaylists.addQueryParameter("limit", "50");
		categoryPlaylists.setPath("https://api.spotify.com/v1/browse/categories/" + first.get().getId() + "/playlists");
		PlaylistPage playlistPage = categoryPlaylists.execute();

		Arrays.stream(playlistPage.getItems()).forEach(playlist -> {
			System.out.println(playlist.getName() + "\t" + playlist.getExternal_urls().getSpotify());
		});
	}
}
