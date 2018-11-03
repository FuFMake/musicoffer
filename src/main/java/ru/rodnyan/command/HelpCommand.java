package ru.rodnyan.command;

public class HelpCommand extends AbstractCommand {

	public HelpCommand(String args) {
		super(args);
	}

	@Override
	public void execute() {
		StringBuilder resultBuild = new StringBuilder();
		resultBuild.append("values in () are optional \n");
		resultBuild.append("Available commands: \n");
		resultBuild.append("categories (int value - maximum categories) -- to see categories of music \n");
		resultBuild.append("playlists *category_name* -- to see playlists of this category \n");
		resultBuild.append("featured -- to see featured playlists \n");
		resultBuild.append("new -- new album releases");

		System.out.println(resultBuild.toString());
	}
}
