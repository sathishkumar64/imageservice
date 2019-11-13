package com.angadi.image.api.exception;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author satvasu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class APIExceptionResponse {
	
	
	//private int responseCode;
	private String httpStatus;
	private String message;
	private ZonedDateTime zonedDateTime;
	
}
