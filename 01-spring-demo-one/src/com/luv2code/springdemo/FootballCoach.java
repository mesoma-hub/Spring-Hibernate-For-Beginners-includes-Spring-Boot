package com.luv2code.springdemo;

public class FootballCoach implements Coach {
	private FortuneService fortuneService;
	
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		
		return "Try out new 3-4-3 defence strategy";
	}

	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}

}
