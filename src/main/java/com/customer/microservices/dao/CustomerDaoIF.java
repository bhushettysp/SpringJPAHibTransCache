package com.customer.microservices.dao;

import java.util.List;

import com.customer.microservices.exception.CustomerException;
import com.customer.microservices.model.Customer;

public interface CustomerDaoIF {
	
	public List<Customer> getCustomers() throws CustomerException;
	public Object getCustomer(Long customerId) throws CustomerException;
	public Object getCustomerByEmail(String emailId) throws CustomerException;
	public Object getCustomerByName(String name) throws CustomerException;
	public Object saveCustomer(Customer customer) throws CustomerException;
	public Object updateCustomer(Customer customer) throws CustomerException;
	public Object deleteCustomer(Long customerId) throws CustomerException;
	public void cacheEvict();
	
}
