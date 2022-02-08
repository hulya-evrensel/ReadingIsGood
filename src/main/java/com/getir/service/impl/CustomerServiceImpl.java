package com.getir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.getir.entity.Customer;
import com.getir.entity.Order;
import com.getir.exception.EmailConstraintViolationException;
import com.getir.repository.CustomerRepository;
import com.getir.repository.OrderRepository;
import com.getir.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	public Customer registerCustomer(Customer customer) {
		Customer savedCustomer = null;
		try {
			savedCustomer = customerRepository.save(customer);
		} catch (DataIntegrityViolationException e) {
			throw new EmailConstraintViolationException();
		} catch (Exception e) {
			return null;
			
		}
		
		
		
		return savedCustomer;
	}
	@Override
	public List<Order> getAllOrdersByCustomerId(Long customerId, Integer page, Integer size) {
		Customer customer = new Customer();
		
		customer.setId(customerId);
		
		Pageable paging = PageRequest.of(page, size, Sort.by("orderdate"));

		List<Order> orders = orderRepository.findOrdersByCustomerId(customer, paging);
		return orders;
	}
	
}
