package com.getir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.entity.Customer;
import com.getir.entity.Order;
import com.getir.exception.OrderNotfoundException;
import com.getir.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping(value = "/registercustomer", method = {RequestMethod.POST})
	public ResponseEntity<Object> registerCustomer(@RequestBody Customer customer) {
		
		customer =  customerService.registerCustomer(customer);
		return new ResponseEntity<>(
				"customer is created successfully with Id = " + customer.getId(),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getallordersbycustomerid", method = {RequestMethod.GET})
	public ResponseEntity<Object> getAllOrdersByCustomerId(@RequestParam(name = "id") Long customerId,
			@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		
		List <Order> orders =  customerService.getAllOrdersByCustomerId(customerId, page, size);
		
		if (orders != null) {
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} else {
			throw new OrderNotfoundException();
		}
	}
}
