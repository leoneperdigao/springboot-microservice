package com.example.springboot;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.domain.Customer;
import com.example.springboot.util.TestUtil;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * 
 * @author leone.perdigao
 *
 */
public class BasicStepdefs {

	private static final Logger LOG = LogManager.getLogger(BasicStepdefs.class);
	private static final String BASE_URL = "http://localhost:8080/api/customers";
	
	@Rule
	private WireMockServer wireMockServer = new WireMockServer(8080);
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);

	private RestTemplate rest = new RestTemplate();
	private Customer customer;
	private Customer customer2;
	private List<Customer> customers;
	
	/**
	 * Load mock data
	 */
	private void loadData() {
		customer = new Customer("Dipesh", "Rane");
		customer2 = new Customer("Rohit", "Ghatol");
		customers = new ArrayList<>();
		customers.add(customer);
		customers.add(customer2);
	}
	
	@Before
	public void setup() throws IOException {
		loadData();
		wireMockServer.start();
		
		givenThat(post(urlEqualTo("/api/customers"))
                .withRequestBody(equalToJson(TestUtil.objectToJson(customer)))
                .willReturn(aResponse()
                		.withStatus(200)
                		.withHeader("Content-Type", "application/json")
                		.withBody(TestUtil.objectToJson(customer))));
		
		givenThat(post(urlEqualTo("/api/customers"))
                .withRequestBody(equalToJson(TestUtil.objectToJson(customer2)))
                .willReturn(aResponse()
                		.withStatus(200)
                		.withHeader("Content-Type", "application/json")
                		.withBody(TestUtil.objectToJson(customer2))));
		
		givenThat(get(urlEqualTo("/api/customers"))
                .willReturn(aResponse()
                		.withStatus(200)
                		.withHeader("Content-Type", "application/json")
                		.withBody(TestUtil.objectToJson(customers))));
	}

	@After
	public void destroy() {
		wireMockServer.stop();
	}

	@When("I store a new Customer[firstname={string},lastname={string}]")
	public void storeCustomer(String firstName,String lastName) throws Throwable {
		LOG.info("When I store a Customer[firstName=\""+firstName+"\",lastName=\""+lastName+"\"]");
		Customer customer = new Customer(firstName, lastName);
		ResponseEntity<Customer> response = rest.postForEntity(BASE_URL,customer, Customer.class);
		Customer returnedCustomer = response.getBody();
		assertEquals(customer.getFirstName(), returnedCustomer.getFirstName());
		assertEquals(customer.getLastName(), returnedCustomer.getLastName());
	}

	@Then("I expect to see Customer[firstname={string},lastname={string}] in List of Customers")
	public void iExpectToSee(String firstName,String lastName) throws Throwable {
		LOG.info("I expect to see Customer[firstname=\""+firstName+"\",lastname=\""+lastName+"\" in List of Customers");
		
		ResponseEntity<Customer[]> response = rest.getForEntity(BASE_URL, Customer[].class);

		Customer customer = new Customer(firstName, lastName);
		Customer[] customers = response.getBody();
		
		boolean matchfound=false;
		for(Customer entity:customers){
			if(entity.getFirstName().equals(customer.getFirstName()) && entity.getLastName().equals(customer.getLastName())){
				matchfound = true;
			}
		}
		
		assertTrue("Did not find Customer in return list",matchfound);
	}
}
