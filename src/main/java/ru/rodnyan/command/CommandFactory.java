package ru.rodnyan.command;

import ru.rodnyan.exception.NoSuchCommandException;

public class CommandFactory {

	public static Command createCommand(String commandName, String args) {
//		return new CommandMock(args);
		switch(commandName) {
			case "categories":
				return new GetCategoriesCommand(args);
			case "help":
				return new HelpCommand(args);
			case "playlists":
				return new GetCategoryPlaylistCommand(args);
			case "featured":
				return new FeatureCommand(args);
			case "new":
				return new NewReleasesCommand(args);
			default:
				throw new NoSuchCommandException("Command " + commandName + " does not exist");
		}
	}

}
