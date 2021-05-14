package com.anno.csv;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;

public class OpenCSVWriter {
	private static final String STRING_ARRAY_SAMPLE = "./string-array-sample.csv";
	
	public static void main(String[] args) throws IOException {
		openCsvWriter(STRING_ARRAY_SAMPLE);
	}
	
	public static void openCsvWriter(final String path) throws IOException {
		try(
				Writer writer = Files.newBufferedWriter(Paths.get(path));
				CSVWriter csvWriter = new CSVWriter(writer,
					CSVWriter.DEFAULT_SEPARATOR,
					CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);
				) {
					String[] headerRecord = {"Name","Email","PhoneNo","Country"};
					csvWriter.writeNext(headerRecord);
					
					csvWriter.writeNext(new String[] {"Sundar Pichai","sundar.pichai@gmail.com","+1-111111111","India"});
					csvWriter.writeNext(new String[] {"Satya Nadella","satya.nadella@outlook.com","+1-111111112","India"});
			}
	}
}
