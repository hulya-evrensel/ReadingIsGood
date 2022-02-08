package com.getir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.entity.StatisticParamsRowMapper;
import com.getir.exception.OrderNotfoundException;
import com.getir.service.IOrderService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private IOrderService orderService;
	
	@RequestMapping(value = "/getmonthlystatistics", method = {RequestMethod.GET})
	public ResponseEntity<Object> getOrderById(@RequestParam (name = "userid") Long userid) {
		
		List<StatisticParamsRowMapper> params = orderService.findCustomersHistory(userid);
		if (params != null) {
			return new ResponseEntity<>(params, HttpStatus.OK);
		} else {
			throw new OrderNotfoundException();
		}	
	}
}
