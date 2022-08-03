package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {

		//read spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// get bean from spring container
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		Coach aCoach = context.getBean("gunCoach", Coach.class);
		//call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(aCoach.getDailyWorkout());
		
		System.out.println(theCoach.getDailyFortune());
		System.out.println(aCoach.getDailyFortune());
		//close the context
		context.close();
	}

}
