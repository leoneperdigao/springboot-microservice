/**
 * 
 */
package com.example.springboot.services;

import com.example.springboot.domain.Customer;

/**
 * @author leone.perdigao
 *
 */
public interface CustomerService {

	public Iterable<Customer> getAllCustomers();
	
	public Customer createCustomer(Customer customer);
}
