package com.soprasteria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import Exceptions.IncorrectDayException;
import Exceptions.IncorrectTotalSeatsException;

public class EventProgram {
	private String title;
	private List<Event> events;
	
	//Constructor

	public EventProgram(String title) {
		this.title = title;
		this.events = new ArrayList<>();
	}
	
	//Getters and Setters
	
	public List<Event> getEvents(){
		return events;
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
	
	public void save(String path) throws IOException {
		File filePath = new File(path);
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(title + "\n");
		for(Event event : events) {
			fileWriter.write(event.title + "/" + event.getDate() + "/" + event.getTotalNumber() + "/" + event.getAlreadyBooked());
		}
		fileWriter.close();
	}
	
	public static EventProgram charge(String path) throws IOException, NumberFormatException, IncorrectDayException, IncorrectTotalSeatsException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line = br.readLine();
		EventProgram eventProgram = new EventProgram(line);
		while ((line = br.readLine()) != null) {
			String dataOfEvent[] = line.split("/");
			String stringToDate[] = dataOfEvent[1].split("-");
			LocalDate dateEvent = LocalDate.of(Integer.parseInt(stringToDate[0]), Integer.parseInt(stringToDate[1]), Integer.parseInt(stringToDate[2]));
			Event event = new Event(dataOfEvent[0],dateEvent,Integer.parseInt(dataOfEvent[2]));
			eventProgram.getEvents().add(event);
		}
		br.close();
		return eventProgram;
	}
	
}
