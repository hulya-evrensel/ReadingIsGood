package com.getir.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table (name = "orders")
@Getter
@Setter
@ToString
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerid")
	private Customer customerid;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bookid")
	private Book bookid;

	private Date orderdate;
	
	private int bookcount;
	
	private float amount;
	
	private String status;

	
}
