package me.ericklucena.quake3log.business.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QuakeLogReader {

	private String filepath;
	private BufferedReader reader;
	
	public QuakeLogReader(String filepath) throws FileNotFoundException {
		this.filepath = filepath;
		load();
	}
	
	private void load() throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(filepath));
	}
	
	public String nextLine() throws IOException {
		return reader.readLine();
	}
	
	public void unload() throws IOException {
		reader.close();
	}
}
