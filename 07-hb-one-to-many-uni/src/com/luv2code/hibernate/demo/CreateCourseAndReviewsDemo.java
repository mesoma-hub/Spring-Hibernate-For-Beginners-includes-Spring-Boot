package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {

			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course newCourse = new Course("300 Spartans");
			//add some reviews
			newCourse.add(new Review("GG WP"));
			newCourse.add(new Review("HAHA XD"));
			newCourse.add(new Review("FU, U SUCC"));
			
			//save the course and cascade save reviews
			System.out.println("Saving the course>>>>>>>");
			System.out.println(newCourse);
			System.out.println(newCourse.getReviews());
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<");
			session.save(newCourse);
		
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
