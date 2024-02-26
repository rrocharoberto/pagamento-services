package br.inatel.dm112.rest.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public OrderStandardError handlerException(OrderNotFoundException ex) {

		OrderStandardError error = new OrderStandardError();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return error;
	}

	@ExceptionHandler(OrderDuplicateException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.CONFLICT)
	public OrderStandardError handlerException(OrderDuplicateException ex) {

		OrderStandardError error = new OrderStandardError();
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return error;
	}


	@ExceptionHandler(InvalidOrderOperationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public OrderStandardError handlerException(InvalidOrderOperationException ex) {

		OrderStandardError error = new OrderStandardError();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public OrderStandardError authorization(Exception ex, HttpServletRequest request) {
		return new OrderStandardError(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(),
				System.currentTimeMillis());
	}
}
