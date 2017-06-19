package me.ericklucena.quake3log.business.parameters.enums;

public enum OutputType {
	PLAIN("-p"), JSON("-j");

	private OutputType(String command) {
		this.command = command;
	}

	private String command;

	public String getCommand() {
		return command;
	}
}
