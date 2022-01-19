package com.emirates.flightsearch.dal;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.emirates.flightsearch.exception.NotFoundException;
import com.emirates.flightsearch.model.Price;
import com.emirates.flightsearch.util.DateUtil;

@Repository
public class PriceRepository implements IPriceRepository {

	@PersistenceContext
	private EntityManager mgr;

	@Transactional
	@Override
	public void savePrice(Price price) {
		mgr.persist(price);

	}

	@Override
	public List<Price> getAllPrices() {
		String jpql = "select p from Price p";

		return mgr.createQuery(jpql, Price.class).getResultList();
	}

	@Override
	public Price findPrice(String flightNumber, LocalDate departureDate) throws NotFoundException {
 		String jpql = "select p from Price p  inner join Flight f on p.flight = f.id and f.flightNumber = :flight_number "
				+ "and  TO_DATE(f.flightDate, 'yyyy-MM-dd') = TO_DATE(:flightDate, 'yyyy-MM-dd')";
		Price price = null;

		try {
			price = mgr.createQuery(jpql, Price.class).setParameter("flight_number", flightNumber)
					.setParameter("flightDate", DateUtil.getDateFromLocalDate(departureDate)).getSingleResult();
			return price;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
