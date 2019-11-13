
package com.angadi.image.api.exception;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author satvasu
 *
 */

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

	/**
	 * @param e
	 * @return
	 */

	@ExceptionHandler({ IOException.class, IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleBadRequest(IOException e) {
		return buildResponseEntity(HttpStatus.BAD_REQUEST.value(), new APIExceptionResponse(
				HttpStatus.BAD_REQUEST.toString(), e.getMessage(), ZonedDateTime.now(ZoneId.systemDefault())));
	}

	/**
	 * @param e
	 * @return
	 */

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException e) {
		return buildResponseEntity(HttpStatus.NOT_FOUND.value(), new APIExceptionResponse(
				HttpStatus.NOT_FOUND.toString(), e.getMessage(), ZonedDateTime.now(ZoneId.systemDefault())));
	}

	/**
	 * @param e
	 * @return
	 */

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<Object> handleAllExceptions(Exception e) {
		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				new APIExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(),
						ZonedDateTime.now(ZoneId.systemDefault())));

	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		return buildResponseEntity(HttpStatus.BAD_REQUEST.value(), new APIExceptionResponse(
				HttpStatus.BAD_REQUEST.toString(), e.getMessage(), ZonedDateTime.now(ZoneId.systemDefault())));

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {		
		//Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());				
		return buildResponseEntity(HttpStatus.BAD_REQUEST.value(), new APIExceptionResponse(
				HttpStatus.BAD_REQUEST.toString(),errors.toString(), ZonedDateTime.now(ZoneId.systemDefault())));

	}

	/**
	 * @param apiError
	 * @return
	 */
	private ResponseEntity<Object> buildResponseEntity(int errorCode, APIExceptionResponse apiError) {
		log.error("Status with Code: {}, Error Message: {}", apiError.getHttpStatus(), apiError.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.valueOf(errorCode));
	}

	// https://dzone.com/articles/spring-rest-service-exception-handling-1

}
