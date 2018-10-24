/**
 * 
 */
package com.example.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author leone.perdigao
 *
 */

@Configuration
@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.example.springboot")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}