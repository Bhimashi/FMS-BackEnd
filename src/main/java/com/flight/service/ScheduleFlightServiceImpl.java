package com.flight.service;

import java.math.BigInteger;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.exceptions.RecordNotFoundException;
import com.flight.exceptions.ScheduledFlightNotFoundException;
import com.flight.model.Schedule;
import com.flight.model.ScheduledFlight;
import com.flight.repository.ScheduleRepository;
import com.flight.repository.ScheduledFlightRepository;

@Service
public class ScheduleFlightServiceImpl implements ScheduleFlightService {

	@Autowired
	ScheduledFlightRepository scheduleFlightRepository;

	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	BookingService bookingService;

	@Override
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight) {
		return scheduleFlightRepository.save(scheduledFlight);
	}
	
	@Override
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduleFlight) {
		ScheduledFlight updateScheduleFlight = scheduleFlightRepository.findById(scheduleFlight.getScheduleFlightId()).get();
		Schedule updateSchedule = scheduleRepository.findById(scheduleFlight.getSchedule().getScheduleId()).get();
		updateScheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats());
		updateSchedule.setSrcAirport(scheduleFlight.getSchedule().getSrcAirport());
		updateSchedule.setDstnAirport(scheduleFlight.getSchedule().getDstnAirport());
		updateSchedule.setArrDateTime(scheduleFlight.getSchedule().getArrDateTime());
		updateSchedule.setDeptDateTime(scheduleFlight.getSchedule().getDeptDateTime());
		scheduleFlightRepository.save(updateScheduleFlight);
		return scheduleFlight;
	}

	@Override
	public String removeScheduledFlight(BigInteger flightId) throws RecordNotFoundException {
		if (flightId == null)
			throw new RecordNotFoundException("Enter flight Id");
		Optional<ScheduledFlight> scheduleFlight = scheduleFlightRepository.findById(flightId);
		if (!scheduleFlight.isPresent())
			throw new RecordNotFoundException("Enter a valid Flight Id");
		else {
			scheduleFlightRepository.deleteById(flightId);
		}
		return "Scheduled flight with ID " + flightId + " is not found";
	}

	@Override
	public Iterable<ScheduledFlight> viewAllScheduledFlights() {
		return scheduleFlightRepository.findAll();
	}

	@Override
	public ScheduledFlight viewScheduledFlight(BigInteger flightId) throws ScheduledFlightNotFoundException {
		if (flightId == null)
			throw new ScheduledFlightNotFoundException("Enter flight Id");
		Optional<ScheduledFlight> scheduleFlight = scheduleFlightRepository.findById(flightId);
		if (!scheduleFlight.isPresent())
			throw new ScheduledFlightNotFoundException("Enter a valid Flight Id");
		else
			return scheduleFlight.get();
	}
}
