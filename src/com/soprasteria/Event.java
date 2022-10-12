package com.soprasteria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import Exceptions.*;

public class Event {
	protected String title;
	protected LocalDate date;
	private final int totalNumber;
	private int alreadyBooked;
	protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	//Constructor
	
	public Event(String title, LocalDate date, int totalNumber) throws IncorrectDayException, IncorrectTotalSeatsException {
		this.title = title;
		if(isDateValid(date)) {
			this.date = date;
		}
		else {
			throw new IncorrectDayException();
		}
		if(isTotalSeatsNumberValid(totalNumber)) {
			this.totalNumber = totalNumber;
		}
		else {
			throw new IncorrectTotalSeatsException();
		}
		this.alreadyBooked = 0;
	}
	
	//Getters and Setters
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public int getAlreadyBooked() {
		return alreadyBooked;
	}
	
	//Check Correct attributes
	
	public boolean isDateValid(LocalDate date) {
		if(ChronoUnit.DAYS.between(LocalDate.now(), date) < 0) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isTotalSeatsNumberValid(int totalSeats) {
		if(totalSeats > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Other Methods
	
	public void reserve(int numberOfBooks) throws NoAvailableSeatsException, AlreadyPassedEventException {
		if(alreadyBooked == totalNumber) {
			throw new NoAvailableSeatsException();
		}
		if(ChronoUnit.DAYS.between(LocalDate.now(), date) < 0) {
			throw new AlreadyPassedEventException();
		}
		alreadyBooked += numberOfBooks;
	}
	
	public void cancelReservation(int seatsToCancel) throws NoAvailableSeatsException, AlreadyPassedEventException {
		if(0 == totalNumber) {
			throw new NoAvailableSeatsException();
		}
		if(ChronoUnit.DAYS.between(LocalDate.now(), date) < 0) {
			throw new AlreadyPassedEventException();
		}
		alreadyBooked -= seatsToCancel;
	}
	
	@Override
	
	public String toString() {
		return (dtf.format(date) + " - " + title + "\n");
	}
}
