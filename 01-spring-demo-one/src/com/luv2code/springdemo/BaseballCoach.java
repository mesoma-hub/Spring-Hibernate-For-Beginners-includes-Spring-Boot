package com.luv2code.springdemo;

public class BaseballCoach implements Coach{
	
	//define private field for dependency
	private FortuneService fortuneService;
	
	//define constructor to inject dependency
	public BaseballCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}

	@Override
	public String getDailyFortune() {
		//use my fortuneService to get fortune
		return fortuneService.getFortune();
	}
}
