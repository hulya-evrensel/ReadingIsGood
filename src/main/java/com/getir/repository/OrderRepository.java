package com.getir.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.getir.entity.Book;
import com.getir.entity.Customer;
import com.getir.entity.Order;
import com.getir.entity.StatisticParams;
import com.getir.entity.StatisticParamsRowMapper;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query(value = "SELECT * from orders where status=? and bookid = ? order by orderdate desc;", nativeQuery = true)
	public List<Order> getOrdersByStatusAndBookId(String string, Book book);

	@Query(value = "SELECT * from orders where customerid=?", nativeQuery = true)
	public List<Order> findOrdersByCustomerId(Customer customer, Pageable paging);
	
	
	@Query(value = "SELECT * from orders where orderdate BETWEEN :startDate AND :endDate", nativeQuery = true)
	public List<Order> getAllBetweenDates(Date startDate,Date endDate);
	
	@Query(value = "SELECT to_char(orderdate, 'MONTH') as monthname ,count(id) as totalorder, sum(bookcount) as totalbook, sum(amount) as totalpurchase from orders where customerid=?  group by monthname", nativeQuery = true)
	public List<StatisticParamsRowMapper> findCustomersHistory(Customer customer);
}
