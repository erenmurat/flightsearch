package com.emirates.flightsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emirates.flightsearch.exception.NotFoundException;
import com.emirates.flightsearch.model.Flight;
import com.emirates.flightsearch.service.IFlightService;
import com.emirates.flightsearch.util.DateUtil;

@RestController
public class FlightController {

	@Autowired
	IFlightService flightService;

	@PostMapping("/saveFlight")
	public String saveFlight(@RequestBody Flight flight) throws NotFoundException {

		flightService.getAllFlights().stream()
				.filter(x -> x.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())
						&& x.getFlightDate().equals(flight.getFlightDate()))
				.findFirst().ifPresent(a -> {
					throw new IllegalArgumentException("Flight Exists");
				});

		flightService.saveFlight(flight);
		return "Saved successfully...";
	}

	// contain “date”, “airport codes of departure and arrival”.
	@RequestMapping(value = "/flight", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Flight>> getFlights(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(value = "dateTime") final String dateTime,
			@RequestParam(value = "departureAirport") final String departureAirport,
			@RequestParam(value = "arrivalAirport") final String arrivalAirport) throws NotFoundException {

		List<Flight> listFlight = null;

		try {
			listFlight = flightService.getAllFlights(departureAirport, arrivalAirport,
					DateUtil.getLocalDateYYYYMMDD(dateTime));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (listFlight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Flight>>(listFlight, HttpStatus.OK);

	}
}
