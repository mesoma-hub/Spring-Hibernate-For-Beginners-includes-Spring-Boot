package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {

			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course newCourse = new Course("Three Kingdoms - How to Survive Lifetime of Shitshow");
			//add some reviews
			session.save(newCourse);
			System.out.println("Saved the course: " + newCourse);
			
			//create students
			
			Student tempStudent1 = new Student("Jon", "Krasinsky", "jon@gmail.com");
			Student tempStudent2 = new Student("Pam", "Beasly", "pam@gmail.com");
			
			//save the students
			newCourse.addStudent(tempStudent1);
			newCourse.addStudent(tempStudent2);
			
			session.save(tempStudent1);
			session.save(tempStudent2);
			
			System.out.println("Saved courses:" + newCourse.getStudents());
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
