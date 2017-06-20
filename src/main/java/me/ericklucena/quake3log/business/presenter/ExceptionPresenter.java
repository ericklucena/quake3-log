package me.ericklucena.quake3log.business.presenter;

import java.io.FileNotFoundException;
import java.io.IOException;

import me.ericklucena.quake3log.business.parameters.exceptions.InvalidParameterException;

public class ExceptionPresenter {

	private Exception exception;
	private static final String UNKNOWN_EXCEPTION = "A unexpected exception occurred.";
	private static final String FILE_NOT_FOUND_EXCEPTION = "The file received as parameter do not exist. Please check the filepath and try again.";
	private static final String IO_EXCEPTION = "There was a problem while reading the file. Please check if the file still exists and try again.";

	public ExceptionPresenter(Exception exception) {
		super();
		this.exception = exception;
	}

	public String getPresentation() {
		if (exception instanceof InvalidParameterException) {
			return exception.getMessage();
		} else if (exception instanceof FileNotFoundException) {
			return FILE_NOT_FOUND_EXCEPTION;
		} else if (exception instanceof IOException) {
			return IO_EXCEPTION;
		} else {
			return UNKNOWN_EXCEPTION;
		}
	}

	public Exception getException() {
		return exception;
	}
}
