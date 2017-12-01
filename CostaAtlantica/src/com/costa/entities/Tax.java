package com.costa.entities;

import java.time.LocalDate;

public class Tax {

	private String description;
	private double total;
	private LocalDate expiration;
	
	public Tax() {
		
	}
	
	public Tax(String description, double total, LocalDate expiration) {
		super();
		this.description = description;
		this.total = total;
		this.expiration = expiration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}
}
