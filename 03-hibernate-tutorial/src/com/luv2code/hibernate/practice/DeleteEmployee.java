package com.luv2code.hibernate.practice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.practice.entity.Employee;


public class DeleteEmployee {

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
		//read the employee
		Employee theEmployee = session.get(Employee.class, employeeId);
		
		System.out.println(theEmployee);
		
		session.delete(theEmployee);
		
		session.createQuery("delete from Employee where id=2");
		//commit
		session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
