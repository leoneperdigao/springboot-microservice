/**
 * 
 */
package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.domain.Customer;


/**
 * 
 * @author leone.perdigao
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);
}