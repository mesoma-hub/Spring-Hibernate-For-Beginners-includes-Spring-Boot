package com.luv2code.hibernate.practice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.practice.entity.Employee;


public class QueryEmployee {

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
		//query the employee
		List<Employee> employees = session.createQuery("from Employee e where e.company = 'Tesla'").getResultList();
		displayEmployees(employees);
		List<Employee> employeesAll = session.createQuery("From Employee").getResultList();
		displayEmployees(employeesAll);
		
		//commit
		session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	private static void displayEmployees(List<Employee> employees) {
		for(Employee anEmployee : employees) {
			System.out.println(anEmployee);
		}
	}

}
