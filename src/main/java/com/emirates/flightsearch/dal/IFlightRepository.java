package com.emirates.flightsearch.dal;

import java.time.LocalDate;
import java.util.List;

import com.emirates.flightsearch.model.Flight;


public interface IFlightRepository {
	List<Flight> getAllFlights();

	List<Flight> getAllFlights(LocalDate departureDate, String departureCity, String arrivalCity);

	void saveFlight(Flight flight);
	
	Flight getFlightByFlightNumber(String  flightNumber);

}
