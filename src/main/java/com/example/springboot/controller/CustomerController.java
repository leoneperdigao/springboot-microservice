/**
 * 
 */
package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.domain.Customer;
import com.example.springboot.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author leone.perdigao
 *
 */
@RestController
@RequestMapping("/api/customers")
@Api("Set of endpoints for Creating, Retrieving, Updating and Deleting of Persons.")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Create Customer", response = Customer.class, httpMethod = "POST")
	@PostMapping(headers = "Accept=application/json")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	@ApiOperation(value = "List Customers", response = Customer.class, responseContainer = "List", httpMethod = "GET")
	@GetMapping(headers = "Accept=application/json")
	public Iterable<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
}
