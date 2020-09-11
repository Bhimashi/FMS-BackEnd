package com.flight.repository;
import java.math.BigInteger;


import org.springframework.data.repository.CrudRepository;

import com.flight.model.Users;;

public interface UserRepository extends CrudRepository<Users, BigInteger>{

}