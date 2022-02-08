package com.getir.service;

import java.util.List;

import com.getir.entity.Customer;
import com.getir.entity.Order;

public interface ICustomerService {

	public Customer registerCustomer(Customer customer);

	public List<Order> getAllOrdersByCustomerId(Long customerId, Integer page, Integer size);
	
}
