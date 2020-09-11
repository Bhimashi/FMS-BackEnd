package com.flight.service;

import java.math.BigInteger;

import com.flight.exceptions.RecordNotFoundException;
import com.flight.exceptions.ScheduledFlightNotFoundException;
import com.flight.model.ScheduledFlight;

public interface ScheduleFlightService {
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight);

	public String removeScheduledFlight(BigInteger id) throws RecordNotFoundException;

	public Iterable<ScheduledFlight> viewAllScheduledFlights();

	public ScheduledFlight viewScheduledFlight(BigInteger id) throws ScheduledFlightNotFoundException;

}
