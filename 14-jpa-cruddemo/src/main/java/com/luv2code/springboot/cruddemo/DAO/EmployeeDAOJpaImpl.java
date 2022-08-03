package com.luv2code.springboot.cruddemo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		//create query
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);
		
		//execute query and get result list
		List<Employee> employees = theQuery.getResultList();
	
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {

		//get employee
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		//return result
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		//save or update with the employee
		Employee anEmployee = entityManager.merge(theEmployee);
		
		//update with id from db.. so we can get generated id for save/insert
		theEmployee.setId(anEmployee.getId());
	}

	@Override
	public void deleteById(int theId) {

	//delete object with primary key
	Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
	
	theQuery.setParameter("employeeId", theId);
	
	theQuery.executeUpdate();	
	}

}
