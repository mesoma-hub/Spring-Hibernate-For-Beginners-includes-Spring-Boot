package com.luv2code.hibernate.practice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.practice.entity.Employee;


public class ReadEmployee {

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
		int employeeId = 1;
		//save the employee
		Employee theEmployee = session.get(Employee.class, employeeId);
		
		System.out.println(theEmployee);
		
		
		//commit
		session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
