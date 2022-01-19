package com.emirates.flightsearch.dal;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.emirates.flightsearch.model.Flight;
import com.emirates.flightsearch.util.DateUtil;

@Repository
public class FlightRepositoryImpl implements IFlightRepository {

	@PersistenceContext
	private EntityManager mgr;

	@Override
	public List<Flight> getAllFlights(LocalDate departureDate, String departureCity, String arrivalCity) {
		String jpql = "select f from Flight f where f.departureAirport=:departureCity and f.arrrivalAirport=:arrivalCity "
				+ "and TO_DATE(f.flightDate, 'yyyy-MM-dd') = TO_DATE(:departureDate, 'yyyy-MM-dd')";

		return mgr.createQuery(jpql, Flight.class).setParameter("departureCity", departureCity)
				.setParameter("arrivalCity", arrivalCity)
				.setParameter("departureDate", DateUtil.getDateFromLocalDate(departureDate), TemporalType.TIMESTAMP)
				.getResultList();
	}

	@Transactional
	@Override
	public void saveFlight(Flight flight) {
		mgr.persist(flight);
	}

	@Override
	public List<Flight> getAllFlights() {
		String jpql = "select f from Flight f";
		return mgr.createQuery(jpql, Flight.class).getResultList();
	}

	@Override
	public Flight getFlightByFlightNumber(String flightNumber) {
		String jpql = "select f from Flight f where f.flightNumber = :flightNumber ";

		return mgr.createQuery(jpql, Flight.class).setParameter("flightNumber", flightNumber).getSingleResult();
	}

}
