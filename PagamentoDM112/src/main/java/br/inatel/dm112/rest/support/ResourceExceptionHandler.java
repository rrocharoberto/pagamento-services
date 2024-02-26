package br.inatel.dm112.rest.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(PaymentException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PaymentError handlerException(PaymentException ex) {

		PaymentError error = new PaymentError();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public PaymentError authorization(Exception ex, HttpServletRequest request) {
		return new PaymentError(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(),
				System.currentTimeMillis());
	}
}
