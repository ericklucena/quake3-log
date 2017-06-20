package me.ericklucena.quake3log.app;

import me.ericklucena.quake3log.business.parameters.Quake3LogParameters;
import me.ericklucena.quake3log.business.parameters.Quake3LogParametersBuilder;
import me.ericklucena.quake3log.business.parser.Quake3LogParser;
import me.ericklucena.quake3log.business.presenter.ExceptionPresenter;
import me.ericklucena.quake3log.business.presenter.QuakeLogPresenter;
import me.ericklucena.quake3log.business.reader.Quake3LogReader;

public class Quake3Log {

	public static void main(String[] args) {

		try {
			Quake3LogParameters parameters = null;
			Quake3LogParametersBuilder builder = new Quake3LogParametersBuilder(args);
			parameters = builder.build();

			Quake3LogReader reader = new Quake3LogReader(parameters);
			Quake3LogParser parser = new Quake3LogParser(reader);

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
