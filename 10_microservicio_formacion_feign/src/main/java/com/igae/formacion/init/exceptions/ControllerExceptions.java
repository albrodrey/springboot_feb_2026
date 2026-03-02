package com.igae.formacion.init.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptions {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> argumentoIlegal(IllegalArgumentException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
}
