package me.ericklucena.quake3log.business.parameters;

import me.ericklucena.quake3log.business.parameters.enums.OutputType;
import me.ericklucena.quake3log.business.parameters.enums.ReportType;
import me.ericklucena.quake3log.business.parameters.exceptions.InvalidParameterException;

public class Quake3LogParametersBuilder {

	private String[] params;

	public Quake3LogParametersBuilder(String[] params) {
		this.params = params;
	}

	public Quake3LogParameters build() throws InvalidParameterException {
		Quake3LogParameters parameters = new Quake3LogParameters();

		validateParams();
		parameters.setReportType(getReportType());
		parameters.setOutputType(getOutputType());
		parameters.setFilepath(params[params.length - 1]);

		return parameters;
	}

	private void validateParams() throws InvalidParameterException {

		for (String param : params) {
			if (param.startsWith("-") && !param.equals(ReportType.SUMMARY.getCommand())
					&& !param.equals(ReportType.RANKING.getCommand()) && !param.equals(OutputType.PLAIN.getCommand())
					&& !param.equals(OutputType.JSON.getCommand())) {
				throw new InvalidParameterException();
			}
		}
	}

	private ReportType getReportType() {
		ReportType reportType = ReportType.SUMMARY;

		for (String param : params) {
			if (param.equals(ReportType.RANKING.getCommand())) {
				reportType = ReportType.RANKING;
				break;
			}
		}
		return reportType;
	}

	private OutputType getOutputType() {
		OutputType outputType = OutputType.PLAIN;

		for (String param : params) {
			if (param.equals(OutputType.JSON.getCommand())) {
				outputType = OutputType.JSON;
			}
		}
		return outputType;
	}
}
