package com.emirates.flightsearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emirates.flightsearch.model.Flight;
import com.emirates.flightsearch.model.Price;

public class FlightControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getFlights() throws Exception {
		String uri = "/flight";
		if (mvc == null) {
			mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		}
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri).param("dateTime", "2022-04-02").param("departureAirport", "BAE")
						.param("arrivalAirport", "SAW").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Flight[] flights = super.mapFromJson(content, Flight[].class);
		assertTrue(flights.length > 0);
	}

	@Test
	public void getPrice() throws Exception {
		String uri = "/price";
		if (mvc == null) {
			mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		}
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("dateTime", "2022-04-02")
				.param("flightNumber", "TZ2020").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Price price = super.mapFromJson(content, Price.class);
		assertTrue(!price.equals(null));
	}

}
