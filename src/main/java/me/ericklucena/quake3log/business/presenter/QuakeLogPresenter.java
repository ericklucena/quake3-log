package me.ericklucena.quake3log.business.presenter;

import com.fasterxml.jackson.core.JsonProcessingException;

import me.ericklucena.quake3log.business.parameters.Quake3LogParameters;
import me.ericklucena.quake3log.business.parameters.enums.OutputType;
import me.ericklucena.quake3log.business.parameters.enums.ReportType;
import me.ericklucena.quake3log.data.models.Quake3LogResult;

public class QuakeLogPresenter {

	private Quake3LogResult result;
	private Quake3LogParameters parameters;

	public QuakeLogPresenter(Quake3LogResult result, Quake3LogParameters parameters) {
		super();
		this.result = result;
		this.parameters = parameters;
	}

	public String getPresentation() throws JsonProcessingException {
		if (parameters.getOutputType() == OutputType.JSON) {
			if (parameters.getReportType() == ReportType.RANKING) {
				return result.getRanking().toJson();
			} else {
				return result.getSummary().toJson();
			}
		} else {
			if (parameters.getReportType() == ReportType.RANKING) {
				return result.getRanking().toReport();
			} else {
				return result.getSummary().toReport();
			}
		}
	}
}
