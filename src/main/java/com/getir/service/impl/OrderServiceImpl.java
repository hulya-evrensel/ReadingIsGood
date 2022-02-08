package com.getir.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getir.entity.Book;
import com.getir.entity.Customer;
import com.getir.entity.Order;
import com.getir.entity.StatisticParams;
import com.getir.entity.StatisticParamsRowMapper;
import com.getir.exception.StockError;
import com.getir.repository.BookRepository;
import com.getir.repository.OrderRepository;
import com.getir.service.IOrderService;
@Service
public class OrderServiceImpl implements IOrderService{

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Order insertOrder(Order order) {
		
		if (order.getBookcount() <= 0) {
			throw new StockError();
		}
		//set New for check.
		order.setStatus("N");
		order.setOrderdate( Calendar.getInstance().getTime());
		
		Order savedOrder = orderRepository.save(order);
		
		
		List<Order> newOrders = orderRepository.getOrdersByStatusAndBookId("N", order.getBookid());
		
		for (Order newOrder : newOrders) {
			Book book = bookRepository.getById(newOrder.getBookid().getId());
			int newStock = book.getStock() - newOrder.getBookcount();
			if (newStock >= 0) {
				newOrder.setStatus("O");
				orderRepository.save(newOrder);
				
				book.setStock(newStock);
				bookRepository.save(book);
			} else {
				throw new StockError();
			}
		}
		return savedOrder;
	}
	
	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public List<Order> getOrdersByDateRange(Date start, Date end) {
		return orderRepository.getAllBetweenDates(start, end);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StatisticParamsRowMapper> findCustomersHistory(Long userid) {
		Customer customer = new Customer();
		List<StatisticParams> list = new ArrayList<>();
		StatisticParams param = null;
		customer.setId(userid);
			return orderRepository.findCustomersHistory(customer);
		
			/*		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT  to_char(orderdate, 'MONTH') as monthname ,count(id) as count, "
				+ "sum(bookcount), sum(amount) from orders where customerid=?  group by monthname", new Object[] { customer });
		try {
			for (Map<String, Object> row: rows) {
				param = new StatisticParams();
				
				param.setMonth((String) row.get("monthname"));
				param.setTotalOrder((Integer) row.get("count"));
				param.setTotalBook((Integer) row.get("bookcount"));
				param.setTotalPurchase((Float) row.get("amount"));
				
				list.add(param);
			}
		}catch (Exception e) {
			System.out.println("hataaaaaa" + e);
		}
		
		return list;*/
	}
	
	


}
