package com.getir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StatisticParams {

	private String monthname;
	private Integer totalorder;
	private Integer totalbook;
	private float totalpurchase;
	public String getMonthname() {
		return monthname;
	}
	public void setMonthname(String monthname) {
		this.monthname = monthname;
	}
	public Integer getTotalorder() {
		return totalorder;
	}
	public void setTotalorder(Integer totalorder) {
		this.totalorder = totalorder;
	}
	public Integer getTotalbook() {
		return totalbook;
	}
	public void setTotalbook(Integer totalbook) {
		this.totalbook = totalbook;
	}
	public float getTotalpurchase() {
		return totalpurchase;
	}
	public void setTotalpurchase(float totalpurchase) {
		this.totalpurchase = totalpurchase;
	}
	
	
	
	
}
