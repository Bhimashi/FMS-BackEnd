package com.flight.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flight.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, BigInteger> {

}
