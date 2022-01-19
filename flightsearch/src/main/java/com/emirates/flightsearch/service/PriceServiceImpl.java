package com.emirates.flightsearch.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emirates.flightsearch.dal.IPriceRepository;
import com.emirates.flightsearch.exception.NotFoundException;
import com.emirates.flightsearch.model.Flight;
import com.emirates.flightsearch.model.Price;

@Service
public class PriceServiceImpl implements IPriceService {

	@Autowired
	IPriceRepository priceRepo;

	@Override
	public void savePrice(Price price) {
		priceRepo.savePrice(price);

	}

	 

	@Override
	public List<Price> findAllPrices() throws NotFoundException {

		return priceRepo.getAllPrices();
	}



	@Override
	public Price findPrice(Flight flight) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Price findPrice(LocalDate departureDate, String flightNumber) throws NotFoundException {
		return priceRepo.findPrice(flightNumber, departureDate);
		
	}

}
