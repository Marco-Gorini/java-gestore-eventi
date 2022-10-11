package com.soprasteria;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EventProgram {
	private String title;
	private List<Event> events;
	
	
	
	//Constructor
	
	public List<Event> getEvents() {
		return events;
	}

	public EventProgram(String title) {
		this.title = title;
		this.events = new ArrayList<>();
	}
	
	//Other Methods
	
	public void addEvent(Event eventToAdd) {
		events.add(eventToAdd);
	}
	
	public List<Event> eventsByDate(LocalDate date){
		List<Event> eventsByDate = new ArrayList<>();
		for(int i = 0; i < events.size(); i++) {
			if(ChronoUnit.DAYS.between(date,events.get(i).getDate()) == 0) {
				eventsByDate.add(events.get(i));
			}
		}
		return eventsByDate;
	}
	
	public int numberOfEvents() {
		return events.size();
	}
	
	public void clearEvents() {
		for(int i = 0; i < events.size(); i++) {
			events.remove(i);
		}
	}
	
	public String showEvents() {
		String stringToReturn = "";
		for(int i = 0; i < events.size(); i++) {
			long min = 0;
			int minimumIndex = i;
			for(int j = 0; j < events.size(); j++) {
				if(ChronoUnit.DAYS.between(events.get(i).getDate(), events.get(j).getDate()) < min && j > i) {
					minimumIndex = j;
					min = ChronoUnit.DAYS.between(events.get(i).getDate(), events.get(j).getDate());
				}
			}
			Event eventToExchange = events.get(minimumIndex);
			events.set(minimumIndex,events.get(i));
			events.set(i, eventToExchange);
		}
		for(int i = 0; i < events.size(); i++) {
			stringToReturn += (events.get(i).toString() + "\n");
		}
		return stringToReturn;
	}
	
}
