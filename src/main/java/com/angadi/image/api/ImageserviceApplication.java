package com.angadi.image.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.angadi.image.api.util.ConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class ImageserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageserviceApplication.class, args);
	}

}
