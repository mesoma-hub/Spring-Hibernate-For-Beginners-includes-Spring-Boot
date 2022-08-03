package com.luv2code.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {

		try {
			//create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			//reader JSON file and map/convert to java POJO
			Student theStudent = mapper.readValue(new File("data/sample-full.json"),Student.class);
			
			//print first name and last name;
			System.out.println("First name: " +theStudent.getFirstName());
			System.out.println("Last name: " +theStudent.getLastName());
			
			//print out address: street and city
			Address theAddress = theStudent.getAddress();
			
			System.out.println("Street: " + theAddress.getStreet());
			System.out.println("City: " + theAddress.getCity());
			
			//print out languages:
			
			for(String theLang : theStudent.getLanguages()) {
				System.out.println(theLang);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
