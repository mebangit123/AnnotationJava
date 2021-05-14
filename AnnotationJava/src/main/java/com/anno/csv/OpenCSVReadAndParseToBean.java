package com.anno.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVReadAndParseToBean {
	private static final String SAMPLE_CSV_FILE_PATH = "./users.csv";
	
	public static void openCSVReadAndParseToBean(final String path) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(path));
		@SuppressWarnings({ "unchecked", "rawtypes" })
		CsvToBean<CSVUser> csvToBean  = new CsvToBeanBuilder(reader)
			.withType(CSVUser.class)
			.withIgnoreLeadingWhiteSpace(true)
			.build();
		
		Iterator<CSVUser> csvUserIterator = csvToBean.iterator();
		
		while(csvUserIterator.hasNext()) {
			CSVUser csvUser = csvUserIterator.next();
			System.out.println("Name :" + csvUser.getName());
			System.out.println("Email :" + csvUser.getEmail());
			System.out.println("PhoneNo :" + csvUser.getPhoneNo());
			System.out.println("Country :" + csvUser.getCountry());
			System.out.println("================================");
		}
		//csvToBean.parse().forEach(System.out::println);
		
		// Reads all CSV contents into memory (Not suitable for large CSV files)
//		List<CSVUser> csvUsers = csvToBean.parse();
//
//		for(CSVUser csvUser: csvUsers) {
//		    System.out.println("Name : " + csvUser.getName());
//		    System.out.println("Email : " + csvUser.getEmail());
//		    System.out.println("PhoneNo : " + csvUser.getPhoneNo());
//		    System.out.println("Country : " + csvUser.getCountry());
//		    System.out.println("==========================");
//		}

	}
	
	public static void main(String[] args) throws IOException {
		openCSVReadAndParseToBean(SAMPLE_CSV_FILE_PATH)	;
	}
}
