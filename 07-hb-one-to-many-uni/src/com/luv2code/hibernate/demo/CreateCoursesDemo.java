package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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

			//start a transaction
			session.beginTransaction();
			
			//get the instructor from db
			int theId = 1;
			Instructor theInstructor = session.get(Instructor.class, theId);
			//create some courses
			Course course1 = new Course("Django Unchained");
			Course course2 = new Course("Inglorius Basterds");
			Course course3 = new Course("Pulp Fiction");
			//add courses to instructor
			
			theInstructor.add(course1);
			theInstructor.add(course2);
			theInstructor.add(course3);
			
			//save the courses
			session.save(course1);
			session.save(course2);
			session.save(course3);
			
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
