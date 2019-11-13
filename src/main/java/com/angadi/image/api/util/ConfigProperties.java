package com.angadi.image.api.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:image_config.properties")
@ConfigurationProperties(prefix = "gcs-resource")
@Getter
@Setter
public class ConfigProperties {

	private String storageName;
}
