package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
	private String[] fortunes = {"GOOD","GOAT","LOL SUCC", "CRY", "Fuck Off"};
	
	private Random myRandom = new Random();
	@Override
	public String getFortune() {
		// TODO Auto-generated method stub
		int index = myRandom.nextInt(fortunes.length);
		
		return fortunes[index];
	}

}
