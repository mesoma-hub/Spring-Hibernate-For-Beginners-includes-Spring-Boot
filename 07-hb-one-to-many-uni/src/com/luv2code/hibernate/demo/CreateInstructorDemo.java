package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			
			Instructor tempInstructor = new Instructor("Susan","Public","susan@gmail.com");
			
			InstructorDetail tempDetail = new InstructorDetail("guzno","games");
			
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
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
