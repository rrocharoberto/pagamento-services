package br.inatel.dm112.rest.support;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UtilityExceptionHandler {
	
	@ExceptionHandler(UtilityException.class)
	public ResponseEntity<StandardError> handleInvalidData(
			UtilityException excecaoCapturada, HttpServletRequest req) {
		
		StandardError error = new StandardError(
				excecaoCapturada.getMessage(),
				HttpStatus.BAD_REQUEST.value(), 
				new Date());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
