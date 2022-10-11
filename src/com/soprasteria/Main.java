package com.soprasteria;

import java.time.LocalDate;
import java.util.Scanner;

import Exceptions.AlreadyPassedEventException;
import Exceptions.IncorrectDayException;
import Exceptions.IncorrectTotalSeatsException;
import Exceptions.NoAvailableSeatsException;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		EventProgram ep = new EventProgram("p");
		try {
			ep.getEvents().add(new Event("evento",LocalDate.of(2022, 10, 21),20));
			ep.getEvents().add(new Event("evento",LocalDate.of(2022, 10, 22),20));
			ep.getEvents().add(new Event("evento",LocalDate.of(2022, 10, 20),20));
			ep.getEvents().add(new Event("evento",LocalDate.of(2027, 7, 3),20));
			ep.getEvents().add(new Event("evento",LocalDate.of(2023, 8, 10),20));
			ep.getEvents().add(new Event("evento",LocalDate.of(2025, 7, 3),20));
			ep.getEvents().add(new Event("evento",LocalDate.of(2029, 10, 4),20));
			ep.getEvents().add(new Event("evento",LocalDate.of(2022, 10, 17),20));
		} catch (IncorrectDayException e1) {
			e1.printStackTrace();
		} catch (IncorrectTotalSeatsException e1) {
			e1.printStackTrace();
		}
		System.out.println(ep.showEvents());
		
		System.out.print("Insert the name of the event: ");
		String nameEvent = in.nextLine();
		Event event = null;
		while(true) {
			try {
				System.out.print("INSERT THE DATE OF THE EVENT: ");
				System.out.print("Insert the day: ");
				int dayEvent = in.nextInt();
				in.nextLine();
				System.out.print("Insert the month: ");
				int monthEvent = in.nextInt();
				in.nextLine();
				System.out.print("Insert the year: ");
				int yearEvent = in.nextInt();
				in.nextLine();
				System.out.print("Insert the total seats: ");
				int seatsEvent = in.nextInt();
				in.nextLine();
				event = new Event(nameEvent,LocalDate.of(yearEvent,monthEvent,dayEvent),seatsEvent);
				break;
			} catch (IncorrectDayException e) {
				System.out.println("The day is wrong!");
			} catch (IncorrectTotalSeatsException e) {
				System.out.println("Total seats number needs to be > 0");
			}
		}
		while(true) {
			System.out.println("You want to reserve a seat?(yes/no");
			String command = in.nextLine();
			if(command.equalsIgnoreCase("yes")) {
				System.out.println("How many seats you want to book?");
				int seatsToBook = in.nextInt();
				in.nextLine();
				try {
					event.reserve(seatsToBook);
					break;
				} catch (NoAvailableSeatsException e) {
					System.out.println("The seats are over!");
				} catch (AlreadyPassedEventException e) {
					System.out.println("The event is no more available");
				}
			}
			else {
				break;
			}
		}
		System.out.println("The event has " + event.getTotalNumber() + " seats and " + event.getAlreadyBooked() + " seats booked.");
		while(true) {
			System.out.println("You want to cancel a reservation?(yes/no");
			String command = in.nextLine();
			if(command.equalsIgnoreCase("yes")) {
				System.out.println("How many reservations you want to cancel?");
				int seatsToCancel = in.nextInt();
				in.nextLine();
				try {
					event.cancelReservation(seatsToCancel);
					break;
				} catch (NoAvailableSeatsException e) {
					System.out.println("The event is empty!");
				} catch (AlreadyPassedEventException e) {
					System.out.println("The event is no more available");
				}
			}
			else {
				break;
			}
		}
		System.out.println("The event has " + event.getTotalNumber() + " seats and " + event.getAlreadyBooked() + " seats booked.");
	}
}
