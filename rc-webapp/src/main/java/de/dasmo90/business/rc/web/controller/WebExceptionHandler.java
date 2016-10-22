package de.dasmo90.business.rc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class WebExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(WebExceptionHandler.class);

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Void> handleAllException(HttpClientErrorException ex) {
		LOG.error("Client exception:", ex);
		return new ResponseEntity<>(ex.getStatusCode());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Void> handleAllException(HttpRequestMethodNotSupportedException ex) {
		LOG.info(ex.toString());
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Void> handleAllException(Exception ex) {
		LOG.error("Unexpected exception:", ex);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
