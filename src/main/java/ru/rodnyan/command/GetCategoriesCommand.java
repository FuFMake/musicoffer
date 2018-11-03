package ru.rodnyan.command;

import ru.rodnyan.model.CategoriesPage;
import ru.rodnyan.requests.GetCategoriesRequest;

import java.io.IOException;
import java.util.Arrays;

public class GetCategoriesCommand  extends AbstractCommand {

	public GetCategoriesCommand(String args) {
		super(args);
	}

	@Override
	public void execute() throws IOException {
		GetCategoriesRequest request = api.getCategories();
		if(!args.isEmpty()) {
			request.addQueryParameter("limit", args);
		}
		CategoriesPage json = request.execute();
		Arrays.stream(json.getItems()).forEach(category -> System.out.println(category.getName()));
	}
}
