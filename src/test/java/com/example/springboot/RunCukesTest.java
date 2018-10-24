/**
 * 
 */

package com.example.springboot;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * 
 * @author leone.perdigao
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/com/example/springboot/customer.feature",
					"src/test/resources/com/example/springboot/customer-2.feature"},
		plugin={"pretty",
		  "html:target/test-report",
			"json:target/test-report.json",
			"junit:target/test-report.xml"}, 
		glue = "com/example/springboot")
public class RunCukesTest {
}