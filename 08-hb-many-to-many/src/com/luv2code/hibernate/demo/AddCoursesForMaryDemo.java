package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

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
			
			//get the student mary from database
			int maryId = 2;
			Student tempStudent = session.get(Student.class, maryId);
			
			System.out.println("\nLoaded student:" + tempStudent);
			System.out.println("Student courses: " +tempStudent.getCourses());
			//create more courses
			
			Course course1 = new Course("Java Spring, SOLIDOOP");
			Course course2 = new Course("Power Lines and Electric Generation");
			
			//add student to courses
			course1.addStudent(tempStudent);
			course2.addStudent(tempStudent);
			//save the courses
			session.save(course1);
			session.save(course2);
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
