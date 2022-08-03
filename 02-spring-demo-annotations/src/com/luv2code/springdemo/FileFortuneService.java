package com.luv2code.springdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class FileFortuneService implements FortuneService {
	private String fileName = "src/fortune.txt";
	private List<String> fortunes;
	
	private Random myRandom = new Random();
	
	public FileFortuneService() {
		File theFile = new File(fileName);
		System.out.println("Reading from file started");
		System.out.println("File exists:" + theFile.exists());
		
		fortunes = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(theFile));
			String tempLine;
			while((tempLine = reader.readLine()) != null) {
				fortunes.add(tempLine);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getFortune() {
		int index = myRandom.nextInt(fortunes.size());
		return fortunes.get(index);
	}
}


