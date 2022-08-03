package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			
			Instructor tempInstructor = new Instructor("Nurik","Great","nurik@gmail.com");
			
			InstructorDetail tempDetail = new InstructorDetail("guzno","luv 2 code !!!");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempDetail);
			
			//start a transaction
			session.beginTransaction();
			//save the instructor
			//this will ALSO save DETAIL because cascade.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			//start a transaction
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
