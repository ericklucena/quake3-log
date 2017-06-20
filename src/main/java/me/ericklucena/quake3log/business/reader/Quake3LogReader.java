package me.ericklucena.quake3log.business.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import me.ericklucena.quake3log.business.parameters.Quake3LogParameters;

public class Quake3LogReader {

	private Quake3LogParameters parameters;
	private BufferedReader reader;
	
	public Quake3LogReader(Quake3LogParameters parameters) throws FileNotFoundException {
		this.parameters = parameters;
		load();
	}
	
	private void load() throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(parameters.getFilepath()));
	}
	
	public String nextLine() throws IOException {
		return reader.readLine();
	}
	
	public void unload() throws IOException {
		reader.close();
	}
}
