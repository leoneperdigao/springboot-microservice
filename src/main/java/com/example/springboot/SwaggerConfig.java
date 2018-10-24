/**
 * 
 */
package com.example.springboot;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author leone.perdigao
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }
    
    private ApiInfo getApiInfo() {
    	return new ApiInfo(
                "Microservice Template",
                "A Microservice template with Spring boot",
                "0.1.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new Contact("Leone Perdigão","https://github.com/leoneperdigao",""),
                "Apache License 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }
}