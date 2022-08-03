package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//use the session object to save Java object
			
			int studentId = 2;
			//now get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student by ID
			System.out.println("Getting student with ID: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			//NEW CODE
			//delete a student
			System.out.println("Deleting student: " + myStudent);
			session.delete(myStudent);
			
			//delete student id=2
			System.out.println("Deleting student: id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
