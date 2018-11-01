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
			default:
				throw new NoSuchCommandException("Command " + commandName + " does not exist");
		}
	}

}
