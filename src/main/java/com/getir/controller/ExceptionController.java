package com.getir.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.getir.exception.BookNotfoundException;
import com.getir.exception.EmailConstraintViolationException;
import com.getir.exception.OrderNotfoundException;
import com.getir.exception.StockError;


@ControllerAdvice
public class ExceptionController
{
	@ExceptionHandler(value = BookNotfoundException.class)
	public ResponseEntity<Object> exception(BookNotfoundException exception)
	{
		return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = OrderNotfoundException.class)
	public ResponseEntity<Object> exception(OrderNotfoundException exception)
	{
		return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmailConstraintViolationException.class)
	public ResponseEntity<Object> exception(EmailConstraintViolationException exception)
	{
		return new ResponseEntity<>("Email Already Used", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = StockError.class)
	public ResponseEntity<Object> exception(StockError exception)
	{
		return new ResponseEntity<>("Stock Error", HttpStatus.NOT_ACCEPTABLE);
	}
}