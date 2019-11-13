package com.angadi.image.api.util;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.angadi.image.api.controller"))
                .paths(PathSelectors.ant("/api/**"))              
                .build()
                .apiInfo(getApiInfo());
    }
    private ApiInfo getApiInfo() {
       return new ApiInfoBuilder().title("RESTful API's for Image Upload Service")
				.description("RESTful API's for Image Upload Service").termsOfServiceUrl("Terms Of Service Url")
				.contact(new Contact("Angadi", "www.angadi.com", "testuser@angadi.com"))
				.version("1.0").build();
	}
  
}