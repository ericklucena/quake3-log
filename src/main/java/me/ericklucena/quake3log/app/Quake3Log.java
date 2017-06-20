package me.ericklucena.quake3log.app;

import me.ericklucena.quake3log.business.parameters.Quake3LogParameters;
import me.ericklucena.quake3log.business.parameters.Quake3LogParametersBuilder;
import me.ericklucena.quake3log.business.parser.QuakeLogParser;
import me.ericklucena.quake3log.business.presenter.ExceptionPresenter;
import me.ericklucena.quake3log.business.presenter.QuakeLogPresenter;
import me.ericklucena.quake3log.business.reader.QuakeLogReader;

public class Quake3Log {

	public static void main(String[] args) {

		try {
			Quake3LogParameters parameters = null;
			Quake3LogParametersBuilder builder = new Quake3LogParametersBuilder(args);
			parameters = builder.build();

			QuakeLogReader reader = new QuakeLogReader(parameters);
			QuakeLogParser parser = new QuakeLogParser(reader);

			parser.parse();
			reader.unload();

			QuakeLogPresenter presenter = new QuakeLogPresenter(parser.getResult(), parameters);

			System.out.println(presenter.getPresentation());

		} catch (Exception e) {
			ExceptionPresenter exceptionPresenter = new ExceptionPresenter(e);
			System.out.println(exceptionPresenter.getPresentation());
		}

	}
}
