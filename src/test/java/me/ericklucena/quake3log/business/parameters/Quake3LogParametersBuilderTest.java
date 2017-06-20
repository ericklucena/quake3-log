package me.ericklucena.quake3log.business.parameters;

import static org.junit.Assert.*;

import org.junit.Test;

import me.ericklucena.quake3log.business.parameters.enums.OutputType;
import me.ericklucena.quake3log.business.parameters.enums.ReportType;
import me.ericklucena.quake3log.business.parameters.exceptions.InvalidParameterException;

public class Quake3LogParametersBuilderTest {

	@Test
	public void Quake3LogParametersBuilder_ValidParameters_SuccessfulBuild() throws InvalidParameterException {
		ReportType expectedReportType = ReportType.RANKING;
		OutputType expectedOutputType = OutputType.JSON;
		String expectedFilepath = "filepath";
		String[] params = {"-r", "-j", "filepath"};
		Quake3LogParametersBuilder builder = new Quake3LogParametersBuilder(params);
		
		Quake3LogParameters actualParameters = builder.build();
		
		assertEquals(expectedReportType, actualParameters.getReportType());
		assertEquals(expectedOutputType, actualParameters.getOutputType());
		assertEquals(expectedFilepath, actualParameters.getFilepath());
	}
	
	@Test
	public void Quake3LogParametersBuilder_OnlyFilename_SuccessfulBuild() throws InvalidParameterException {
		String expectedFilepath = "filepath";
		String[] params = {"filepath"};
		Quake3LogParametersBuilder builder = new Quake3LogParametersBuilder(params);
		
		Quake3LogParameters actualParameters = builder.build();
		
		assertEquals(expectedFilepath, actualParameters.getFilepath());
	}
	
	@Test(expected=InvalidParameterException.class)
	public void Quake3LogParametersBuilder_InvalidParameters_ThrowException() throws InvalidParameterException {
		String[] params = {"-r", "-i", "filepath"};
		Quake3LogParametersBuilder builder = new Quake3LogParametersBuilder(params);
		
		builder.build();
	}
	
	@Test(expected=InvalidParameterException.class)
	public void Quake3LogParametersBuilder_EmptyParameters_ThrowException() throws InvalidParameterException {
		String[] params = {};
		Quake3LogParametersBuilder builder = new Quake3LogParametersBuilder(params);
		
		builder.build();
	}

}
