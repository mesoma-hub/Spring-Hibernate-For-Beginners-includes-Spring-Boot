package com.luv2code.hibernate.practice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.practice.entity.Employee;


public class CreateEmployee {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				 .configure("hibernate.cfg.xml")
				 .addAnnotatedClass(Employee.class)
				 .buildSessionFactory();
		try {
		//create session
		Session session = factory.getCurrentSession();
		//begin transaction
		session.beginTransaction();
		//create an employee
		Employee employee1 = new Employee("Tako", "Kultayev", "SpaceX");
		Employee employee2 = new Employee("Kasym", "Abzaluly", "Tesla");
		Employee employee3 = new Employee("Haki", "Abzaluly", "PayPal");
		Employee employee4 = new Employee("Agzam", "Yeldosuly", "World");
		//save the employee
		session.save(employee1);
		session.save(employee2);
		session.save(employee3);
		session.save(employee4);
		
		
		//commit
		session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
