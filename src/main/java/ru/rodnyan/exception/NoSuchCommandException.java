package ru.rodnyan.exception;

public class NoSuchCommandException extends RuntimeException {

	public NoSuchCommandException(String message) {
		super(message);
	}
}
