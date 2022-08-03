package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> theQuery
						= session.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		//save the customer
		session.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//retriev/read from database using primary key
		
		Customer theCustomer = session.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		if(theSearchName != null && theSearchName.trim().length()>0) {
			//search firstName or lastName case insensitive
			theQuery = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like: theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
				
		} else {
			// the SearchName is empty.. so get all customers
			theQuery = session.createQuery("from Customer", Customer.class);
			
		}
		
		//execute query and get result
		List<Customer> customers = theQuery.getResultList();
		return customers;
	}

}
