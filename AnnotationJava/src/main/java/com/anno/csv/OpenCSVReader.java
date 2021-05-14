package com.anno.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;

public class OpenCSVReader {
	private final static String SAMPLE_CSV_FILE_PATH = "./users.csv";
	
	public static void readcsvDataOnebyOne(final String path) throws IOException {
		
		Reader reader = Files.newBufferedReader(Paths.get(path));
		CSVReader csv = new CSVReader(reader);		
		String[] nextRecord;
		while((nextRecord = csv.readNext()) != null) {
			System.out.println("Name "+ nextRecord[0]);
			System.out.println("Email " + nextRecord[1]);
			System.out.println("Phone "+ nextRecord[2]);
			System.out.println("Country "+ nextRecord[3]);
			System.out.println("==========================");
		}
	}
	public static void readAllCsvRecordAtOnce(final String path) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(path));
		CSVReader csv = new CSVReader(reader);	
		
		List<String[]> records = csv.readAll();
		for(String[] record : records) {
			System.out.println("Name "+ record[0]);
			System.out.println("Email " + record[1]);
			System.out.println("Phone "+ record[2]);
			System.out.println("Country "+ record[3]);
			System.out.println("==========================");
		}
	}
	public static void main(String[] args) throws IOException {
		readcsvDataOnebyOne(SAMPLE_CSV_FILE_PATH);
		readAllCsvRecordAtOnce(SAMPLE_CSV_FILE_PATH);
	}
}
