package com.flight.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flight.model.Flight;
@Repository
public interface FlightRepository extends CrudRepository<Flight,BigInteger>{

}
