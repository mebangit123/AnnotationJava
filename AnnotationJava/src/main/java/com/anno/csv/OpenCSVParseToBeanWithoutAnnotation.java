package com.anno.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVParseToBeanWithoutAnnotation {
	private static final String SAMPLE_CSV_FILE_PATH = "./users.csv";
	public static void main(String[] args) throws IOException {
		openCSVParseToBeanWithoutAnnotation(SAMPLE_CSV_FILE_PATH);
	}
	
	public static void openCSVParseToBeanWithoutAnnotation(final String path) throws IOException {
		try(
			Reader reader = Files.newBufferedReader(Paths.get(path));
			) {
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(MyUser.class);
			String[] memberFieldsToBindTo = {"name","email","phoneNo","country"};
			strategy.setColumnMapping(memberFieldsToBindTo);
			
			CsvToBean<MyUser> csvToBean = new CsvToBeanBuilder(reader)
					.withMappingStrategy(strategy)
					.withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			
				
//				Iterator<MyUser> myUserIterator = csvToBean.iterator();
//				
//				while(myUserIterator.hasNext()) {
//					MyUser myUser = myUserIterator.next();
//					System.out.println("Name :" + myUser.getName());
//					System.out.println("Email :" + myUser.getEmail());
//					System.out.println("PhoneNo :" + myUser.getPhoneNo());
//					System.out.println("Country :" + myUser.getCountry());
//					System.out.println("===================================");
//				}
		
		List<MyUser> myUser = csvToBean.parse();
		for(MyUser user : myUser) {
			System.out.println("Name :" + user.getName());
			System.out.println("Email :" + user.getEmail());
			System.out.println("PhoneNo :" + user.getPhoneNo());
			System.out.println("Country :" + user.getCountry());
			System.out.println("===================================");
			}
		}
	}
}
