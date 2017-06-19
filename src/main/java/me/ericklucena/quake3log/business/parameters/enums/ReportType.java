package me.ericklucena.quake3log.business.parameters.enums;

public enum ReportType {
	SUMMARY("-s"), RANKING("-r");

	private ReportType(String command) {
		this.command = command;
	}

	private String command;

	public String getCommand() {
		return command;
	}
}
