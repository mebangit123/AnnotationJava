package com.anno.csv.addressbook;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.anno.csv.CSVUser;
import com.anno.csv.MyUser;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookCsvJson {
	public static Scanner sc = new Scanner(System.in);
	public static final String OBJECT_LIST_SAMPLE = "./personContact-list-sample.csv";
	public static List<Person> contact = new ArrayList<Person>();
	
	//main method..
	public static void main(String[] args) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		mainMenu();
		openCSVReaderObjectList(OBJECT_LIST_SAMPLE);
	}
	
	//Main Menu
	public static void mainMenu() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		int menu = 1;
	    while (menu != 0) {
	    	System.out.println(" 0. Exit. ");
	        System.out.println(" 1. Add Contact. ");
	        System.out.println(" 2. Display Data. ");
	        System.out.println(" 3. Write Data into a text file. ");
	        System.out.println(" 4. Write Data into a csv file");
	    	menu = sc.nextInt();
	    	switch (menu) {
		    	case 0:
		    		System.out.println("Goodbye!");
		    	 break;
		        case 1:
		            addContact();
		            break;
		        case 2:
		    	   printData();
		            break;
		        case 3:
		    	   writeData();
		    	   break;
		        case 4:
		        	openCSVWriterObjectList(OBJECT_LIST_SAMPLE);
		        	break;
		        default:
		    	   System.out.println("Invali input..");
	         } 

     	 }
    }
	public static void addContact() {
		int menu = 0;
		while (menu != 2) {
            System.out.println("Enter First Name: ");
            String f_name = sc.next();
            System.out.println("Enter Last Name: ");
            String l_name = sc.next();
            System.out.println("Enter email: ");
            String email = sc.next();
            System.out.println("Enter Address: ");
            String address = sc.next();
            System.out.println("Enter City: ");
            String city = sc.next();
            System.out.println("Enter State: ");
            String state = sc.next();
            System.out.println("Enter Zip: ");
            int zip = sc.nextInt();
            System.out.println("Enter Phone_NO: ");
            int phone_no = sc.nextInt();
            contact.add(new Person(f_name, l_name, address, city, state, zip, phone_no, email));
            System.out.println("Would you like to add someone else? 1: Yes, 2: No");
            menu = sc.nextInt();
        }
	}
	public static void writeData() {
		new AddressbookIOService();
		AddressbookIOService.writeDatas(contact);
	}
	public static void printData() {
		new AddressbookIOService().printData(); 
	}
	public static void openCSVWriterObjectList(final String path) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try(
			Writer writer = Files.newBufferedWriter(Paths.get(path));
			) {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer)
						.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
						.build();
				beanToCsv.write(contact);
				writer.close();
		}
	}
	public static void openCSVReaderObjectList(final String path) throws IOException {
		try(
				Reader reader = Files.newBufferedReader(Paths.get(path));
				) {
				ColumnPositionMappingStrategy<Person> strategy = new ColumnPositionMappingStrategy<Person>();
				strategy.setType(Person.class);
				String[] memberFieldsToBindTo = {"FirstName","LastName","Address","City","State","PhoneNO","ZipCode","Email"};
				strategy.setColumnMapping(memberFieldsToBindTo);
				
				CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(reader)
						.withMappingStrategy(strategy)
						.withSkipLines(1)
						.withIgnoreLeadingWhiteSpace(true)
						.build();
				
					csvToBean.parse().forEach(System.out::println);
		}
	}
}