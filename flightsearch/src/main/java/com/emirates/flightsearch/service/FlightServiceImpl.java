package com.emirates.flightsearch.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emirates.flightsearch.dal.IFlightRepository;
import com.emirates.flightsearch.model.Flight;

@Service
public class FlightServiceImpl implements IFlightService {

	@Autowired
	IFlightRepository flightRepo;

	@Override
	public void saveFlight(Flight flight) {
		flightRepo.saveFlight(flight);

	}

	@Override
	public List<Flight> getAllFlights(String departureCity, String arrivalCity, LocalDate departureDate) {
		return flightRepo.getAllFlights(departureDate, departureCity, arrivalCity);
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepo.getAllFlights();
	}

	@Override
	public Flight getFlightbyFlightNumber(String flightNumber) {

		return flightRepo.getFlightByFlightNumber(flightNumber);
	}

}
