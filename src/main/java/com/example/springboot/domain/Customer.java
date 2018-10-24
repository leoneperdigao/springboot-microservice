/**
 * 
 */
package com.example.springboot.domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 
 * @author leone.perdigao
 *
 */
@Entity
public class Customer implements Serializable{

	private static final long serialVersionUID = -8512586178643785019L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;

	public Customer() {}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format(
				"Customer[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}