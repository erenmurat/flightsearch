package com.emirates.flightsearch.dal;

import java.time.LocalDate;
import java.util.List;

import com.emirates.flightsearch.exception.NotFoundException;
import com.emirates.flightsearch.model.Price;


public interface IPriceRepository   {

	 
	public Price findPrice(String flightNumber, LocalDate departureDate) throws NotFoundException;
	public void savePrice(Price price);
	public List<Price> getAllPrices();
}
