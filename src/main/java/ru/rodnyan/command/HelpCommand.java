package ru.rodnyan.command;

public class HelpCommand extends AbstractCommand {

	public HelpCommand(String args) {
		super(args);
	}

	@Override
	public void execute() {
		StringBuilder resultBuild = new StringBuilder();
		resultBuild.append("Available commands: \n");
		resultBuild.append("categories -- to see categories of music");

		System.out.println(resultBuild.toString());
	}
}
