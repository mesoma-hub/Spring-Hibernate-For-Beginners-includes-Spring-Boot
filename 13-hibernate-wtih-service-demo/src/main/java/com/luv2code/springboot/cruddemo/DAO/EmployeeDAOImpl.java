package com.luv2code.springboot.cruddemo.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	//define field for EntityManager
	private EntityManager entityManager;
	
	//setup constructor injection
	@Autowired
	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	//
	@Override
	public List<Employee> findAll() {
		//get current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		// create query
		Query<Employee> theQuery = session.createQuery("from Employee", Employee.class);
		
		//execute query
		List<Employee> employees = theQuery.getResultList();
		
		//return the result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get current hibernate session
		Session session = entityManager.unwrap(Session.class);
		//get the employee
		Employee theEmployee = session.get(Employee.class, theId);
		
		//return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//create current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//save employee
		session.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		
		//create current hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//delete object with primary key
		Query theQuery = session.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();		
	}

}
