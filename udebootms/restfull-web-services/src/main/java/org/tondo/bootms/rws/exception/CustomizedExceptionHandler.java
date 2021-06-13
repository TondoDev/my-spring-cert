package org.tondo.bootms.rws.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // shared accross all controllers
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest requenst) {
		
		ExceptionResponse errorData = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), requenst.getDescription(false));
		
		return new ResponseEntity<>(errorData, HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex, WebRequest requenst) {
		
		ExceptionResponse errorData = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), requenst.getDescription(false));
		
		return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND );
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse errorData = new ExceptionResponse(LocalDateTime.now(), "Validations faild", ex.getBindingResult().toString());
		return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST );
	}
}
