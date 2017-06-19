package me.ericklucena.quake3log.business.parameters;

import me.ericklucena.quake3log.business.parameters.enums.OutputType;
import me.ericklucena.quake3log.business.parameters.enums.ReportType;

public class Quake3LogParameters {

	private ReportType reportType;
	private OutputType outputType;
	private String filepath;

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public OutputType getOutputType() {
		return outputType;
	}

	public void setOutputType(OutputType outputType) {
		this.outputType = outputType;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s", reportType.getCommand(), outputType.getCommand(), filepath);
	}

}
