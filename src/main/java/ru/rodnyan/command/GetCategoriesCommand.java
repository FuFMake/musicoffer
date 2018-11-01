package ru.rodnyan.command;

import java.io.IOException;

public class GetCategoriesCommand  extends AbstractCommand{

	public GetCategoriesCommand(String args) {
		super(args);
	}

	@Override
	public void execute() throws IOException {
		api.getCategories().execute();
	}
}
