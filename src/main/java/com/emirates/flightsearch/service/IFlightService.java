package com.emirates.flightsearch.service;

import java.time.LocalDate;
import java.util.List;

import com.emirates.flightsearch.model.Flight;

public interface IFlightService {

	public void saveFlight(Flight flight);

	public List<Flight> getAllFlights(String departureCity, String arrivalCity, LocalDate departureDate);

	public List<Flight> getAllFlights();

	public Flight getFlightbyFlightNumber(String flightNumber);
}
