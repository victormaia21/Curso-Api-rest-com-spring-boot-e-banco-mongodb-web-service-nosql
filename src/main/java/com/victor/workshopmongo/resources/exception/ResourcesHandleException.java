package com.victor.workshopmongo.resources.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.victor.workshopmongo.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourcesHandleException {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandadError>ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		String error = "ID NÃO ENCONTRADO";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandadError se = new StandadError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(se);
	}
}
