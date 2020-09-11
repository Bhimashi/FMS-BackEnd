package com.flight.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.flight.model.Airport;

@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {

}
