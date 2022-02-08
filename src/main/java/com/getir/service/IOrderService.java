package com.getir.service;

import java.util.Date;
import java.util.List;

import com.getir.entity.Order;
import com.getir.entity.StatisticParams;
import com.getir.entity.StatisticParamsRowMapper;

public interface IOrderService {

	public Order insertOrder(Order order);

	public Order findById(Long orderId);

	public List<Order> getOrdersByDateRange(Date start, Date end);

	public List<StatisticParamsRowMapper> findCustomersHistory(Long userid);



}
