package com.emirates.flightsearch.controller;

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
import com.emirates.flightsearch.model.Price;
import com.emirates.flightsearch.service.IFlightService;
import com.emirates.flightsearch.service.IPriceService;
import com.emirates.flightsearch.util.DateUtil;

@RestController
public class PriceController {

	@Autowired
	IPriceService priceService;

	@Autowired
	IFlightService flightService;

	@PostMapping("/savePrice")
	public String saveCity(@RequestBody Price price) throws NotFoundException {

		priceService.findAllPrices().stream()
				.filter(x -> x.getFlight().equals(price.getFlight()) && x.getAmount() == price.getAmount()).findFirst()
				.ifPresent(a -> {
					throw new IllegalArgumentException("Price does not Exists");
				});

		priceService.savePrice(price);
		return "Saved successfully...";
	}

	@RequestMapping(value = "/price", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Price> getPrice(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(value = "dateTime") final String dateTime,
			@RequestParam(value = "flightNumber") final String flightNumber) throws NotFoundException {

		Price price = null;
		try {
			price = priceService.findPrice(DateUtil.getLocalDateYYYYMMDD(dateTime), flightNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (price == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Price>(price, HttpStatus.OK);
	}

}
