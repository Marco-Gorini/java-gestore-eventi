package com.soprasteria;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Exceptions.IncorrectDayException;
import Exceptions.IncorrectTotalSeatsException;

public class Concert extends Event{
	BigDecimal price;
	LocalTime hour;
	DecimalFormat df = new DecimalFormat("#.##"); //.format();
	
	
	//Constructor
	
	public Concert(String title, LocalDate date, int totalNumber, BigDecimal price, LocalTime hour)
			throws IncorrectDayException, IncorrectTotalSeatsException {
		super(title, date, totalNumber);
		this.price = price;
		this.hour = hour;
	}
	
	//Getters and Setters

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}
	
	//Other Methods 
	
	@Override
	public String toString() {
		return super.dtf.format(super.date) + "-" + hour + "-" + super.title + "-" + formatPrice() + "$";
	}
	
	public String formatPrice(){
		return df.format(price.doubleValue());
	}
}
