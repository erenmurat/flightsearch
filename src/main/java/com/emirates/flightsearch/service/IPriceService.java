package com.emirates.flightsearch.service;

import java.time.LocalDate;
import java.util.List;

import com.emirates.flightsearch.exception.NotFoundException;
import com.emirates.flightsearch.model.Flight;
import com.emirates.flightsearch.model.Price;

public interface IPriceService {

	public void savePrice(Price price);

 	public Price findPrice(Flight flight) throws NotFoundException;
	
	public List<Price> findAllPrices() throws NotFoundException;
	
	public Price findPrice(LocalDate departureDate, String flightNumber) throws NotFoundException;



}
