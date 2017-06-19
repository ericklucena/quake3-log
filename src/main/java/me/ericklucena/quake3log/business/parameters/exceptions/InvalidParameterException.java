package me.ericklucena.quake3log.business.parameters.exceptions;

public class InvalidParameterException extends Exception{

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "There is a problem with the given parameters";
	
	public InvalidParameterException() {
		super(MESSAGE);
	}

}
