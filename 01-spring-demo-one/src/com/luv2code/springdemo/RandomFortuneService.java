package com.luv2code.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {

	private String[] fortunes  = {"Good Luck", "Medium Luck", "Bad Luck"};
	@Override
	public String getFortune() {
		int rnd = new Random().nextInt(fortunes.length);
		return fortunes[rnd];
	}

}
