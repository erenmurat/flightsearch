package com.emirates.flightsearch.dal;

import java.time.LocalDate;
import java.util.List;

import com.emirates.flightsearch.model.Flight;


public interface IFlightRepository {
	List<Flight> getAllFlights();

	List<Flight> getAllFlights(LocalDate departureDate, String departureCity, String arrivalCity);

	void saveFlight(Flight flight);
	
	Flight getFlightByFlightNumber(String  flightNumber);

//	@Query(value = "SELECT a FROM Flight a WHERE flightDate = ?1 AND departureAirport = ?2 and arrrivalAirport = ?3", nativeQuery = true)
//	public List<Flight> findFlight(Date reqDate, String departureAirportCode, String arrivalAirportCode)
//			throws NotFoundException;
//
//	@Query(value = "SELECT a FROM Flight a WHERE flightNumber = ?1 and flightDate = ?2", nativeQuery = true)
//	public Flight findFlight(String flightNumber, Date reqDate);
}
