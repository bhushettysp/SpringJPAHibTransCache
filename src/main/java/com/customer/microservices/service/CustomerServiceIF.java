package com.customer.microservices.service;

import java.util.List;

import com.customer.microservices.exception.CustomerException;
import com.customer.microservices.model.Customer;

public interface CustomerServiceIF {
	public List<Customer> getCustomers() throws CustomerException;
	public Object getCustomer(Long customerId) throws CustomerException;
	public Object saveCustomer(Customer customer) throws CustomerException;
	public Object updateCustomer(Customer customer) throws CustomerException;
	public Object deleteCustomer(Long customerId) throws CustomerException;
	
}
