package ru.rodnyan.requests;

import ru.rodnyan.model.CategoriesPage;
import ru.rodnyan.model.CategoriesWrapper;
import ru.rodnyan.util.JsonUtil;

import java.io.IOException;

public class GetCategoriesRequest extends AbstractRequest {

	public GetCategoriesRequest() {
		path += "/browse/categories";
		setMethod(RequestType.GET);
	}

	@Override
	public CategoriesPage execute() throws IOException {
		return JsonUtil.fromJson(super.execute(), CategoriesWrapper.class).getCategories();

	}

	@Override
	public void configureConnection() {
		super.configureConnection();

	}
}
