package com.anno.csv.addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AddressbookIOService {
		public static String CONTACT_FILENAME = "contact-file.txt";
		public static void writeDatas(List<Person> person) {
			StringBuffer empBuffer = new StringBuffer();
			person.forEach(employee -> {
				String employeeDataString = employee.toString().concat("\n");
				empBuffer.append(employeeDataString);
			});
			try {
				Files.write(Paths.get(CONTACT_FILENAME), empBuffer.toString().getBytes());		
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void printData() {
			try {
				Files.lines(new File(CONTACT_FILENAME).toPath())
					.forEach(System.out::println);
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
}
