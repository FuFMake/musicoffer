package ru.rodnyan.input;

import ru.rodnyan.command.Command;
import ru.rodnyan.command.CommandFactory;
import ru.rodnyan.command.CommandManager;

import java.util.Scanner;

public class InputThread implements Runnable {

	private CommandManager commandManager;

	public InputThread(CommandManager commandManager) {
		this.commandManager = commandManager;
	}

	@Override
	public void run() {
		Scanner in = new Scanner(System.in);
		StringBuilder commandBuilder = new StringBuilder();
		String curStr;
		while(in.hasNext()) {
			curStr = in.next();
			if (curStr.equals("stop;")) {
				System.exit(0);
			}
			commandBuilder.append(curStr).append(" ");
			if (curStr.endsWith(";")) {
				commandBuilder.deleteCharAt(commandBuilder.length() - 1).deleteCharAt(commandBuilder.length() - 1);
				handleCommand(commandBuilder.toString());
				commandBuilder = new StringBuilder();
			}
		}
	}

	private void handleCommand(String s) {
		s = s.trim();
		String splitedCommand[] = s.split(" ", 2);
		String args = "";
		if (splitedCommand.length > 1)
			args = splitedCommand[1];
		Command command = CommandFactory.createCommand(splitedCommand[0], args);
		commandManager.execute(command);
	}
}
