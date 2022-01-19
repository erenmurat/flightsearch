package com.emirates.flightsearch;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.emirates.flightsearch.dal.IFlightRepository;
import com.emirates.flightsearch.dal.IPriceRepository;
import com.emirates.flightsearch.model.Flight;
import com.emirates.flightsearch.model.Price;

@SpringBootApplication
@EnableAsync
public class FlightsearchApplication implements CommandLineRunner {

	@Autowired
	IFlightRepository fRepo;
	@Autowired
	IPriceRepository pRepo;

	public static void main(String[] args) {
		SpringApplication.run(FlightsearchApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("FlightSearch-");
		executor.initialize();
		return executor;
	}

	@Override
	public void run(String... args) throws Exception {
		Flight f1 = new Flight();
 
		 
		Calendar flightDate = Calendar.getInstance();
		flightDate.set(2022, 03, 02, 19, 30, 0);
 	        
		f1.setFlightDate(flightDate.getTime());
		f1.setArrrivalAirport("SAW");
		f1.setDepartureAirport("BAE");
		f1.setFlightNumber("TZ2020");
		f1.setFlightTime( Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

		Price p1 = new Price();
		p1.setAmount(1000);
		p1.setCurrency("EUR");
		p1.setFlight(f1);
		// f1.setPrice(p1);

		// pRepo.save(p1);
		fRepo.saveFlight(f1);
		pRepo.savePrice(p1);

	}
}
