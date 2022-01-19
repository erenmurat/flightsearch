package com.emirates.flightsearch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {

	private static final long serialVersionUID = 1066765736355089231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_id")
	private Long id;

	@Column(name = "flight_number")
	private String flightNumber;

	@Column(length = 20,name = "departure_airport")
	private String departureAirport;

	@Column(length = 20,name = "arrival_airport")
	private String arrrivalAirport;
	
	
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date flightDate;
	
	@Column(name="flight_time")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = " HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date flightTime;

	 

	public Date getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrrivalAirport() {
		return arrrivalAirport;
	}

	public void setArrrivalAirport(String arrrivalAirport) {
		this.arrrivalAirport = arrrivalAirport;
	}

}
