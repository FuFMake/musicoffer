package ru.rodnyan.util;

import com.google.gson.Gson;

public final class JsonUtil {
	private static final Gson gson = new Gson();

	private JsonUtil() {}

	public static <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
}
