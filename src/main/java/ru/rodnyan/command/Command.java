package ru.rodnyan.command;

import ru.rodnyan.SpotifyApi;

import java.io.IOException;

public interface Command {

	void execute() throws IOException;
	void setApi(SpotifyApi api);
}
