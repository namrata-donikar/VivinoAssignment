package com.common;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class InputReader {

	public static final String KEYWORDS_FILE = "keywords.csv";
	private List<String[]> keywords;

	public InputReader() {
		keywords = new ArrayList<>();
		loadKeywords();
	}

	public void loadKeywords() {
		try {
			CSVReader fileReader = new CSVReader(new FileReader(KEYWORDS_FILE));
			String[] data = null;
			while ((data = fileReader.readNext()) != null) {
				keywords.add(data);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Error reading input data!!");
		}
	}
	
	public List<String[]> getKeywords() {
		return keywords;
	}

}
