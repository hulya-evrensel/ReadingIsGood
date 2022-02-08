package com.getir.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.entity.Order;
import com.getir.exception.OrderNotfoundException;
import com.getir.service.IOrderService;
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping(value = "/insertorder", method = {RequestMethod.POST})
	public ResponseEntity<Object> insertOrder(@RequestBody Order order) {
		
		order = orderService.insertOrder(order);
		
		return new ResponseEntity<>(
				"Order is created successfully with Id = " + order.getId(),
				HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/getorderbyid", method = {RequestMethod.GET})
	public ResponseEntity<Object> getOrderById(@RequestParam (name = "id") Long orderId) {
		
		Order orderExist = orderService.findById(orderId);
		if (orderExist != null) {
			return new ResponseEntity<>(orderExist, HttpStatus.OK);
		} else {
			throw new OrderNotfoundException();
		}	
	}
	
	@RequestMapping(value = "/getordersbydaterange", method = {RequestMethod.GET})
	public ResponseEntity<Object> getOrdersByDateRange(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
		
		List<Order> orders = orderService.getOrdersByDateRange(start, end);
		if (orders != null) {
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} else {
			throw new OrderNotfoundException();
		}	
	}
	
}