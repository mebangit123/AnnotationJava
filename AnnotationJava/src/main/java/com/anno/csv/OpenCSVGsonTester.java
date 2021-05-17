package com.anno.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVGsonTester {
	private static final String SAMPLE_CSV_FILE_PATH = "./users.csv";
	private static final String SAMPLE_JSON_FILE_PATH = "./users.json";
	
	public static void main(String[] args) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			CsvToBeanBuilder<MyUser> csvToBeanBuilder = new CsvToBeanBuilder<MyUser>(reader);
			csvToBeanBuilder.withType(MyUser.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			
			CsvToBean<MyUser> csvToBean = csvToBeanBuilder.build();
			
			List<MyUser> csvUsers = csvToBean.parse();
			Gson gson = new Gson();
			String json = gson.toJson(csvUsers);
			FileWriter writer = new FileWriter(SAMPLE_JSON_FILE_PATH);
			writer.write(json);
			writer.close();
			BufferedReader br = new BufferedReader(new FileReader(SAMPLE_JSON_FILE_PATH));
			CSVUser[] usrObj = gson.fromJson(br, CSVUser[].class);
			List<CSVUser> csvUserList = Arrays.asList(usrObj);
			for(CSVUser csvuser : csvUserList) {
				System.out.println(csvuser);
				System.out.println();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
