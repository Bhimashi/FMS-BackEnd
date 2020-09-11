package com.flight.service;

import java.math.BigInteger;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.exceptions.RecordAlreadyPresentException;
import com.flight.exceptions.RecordNotFoundException;
import com.flight.model.Users;
import com.flight.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<?> createUser(Users newUser) {
		Optional<Users> findUserById = userRepository.findById(newUser.getUserId());
		try {
			if (!findUserById.isPresent()) {
				userRepository.save(newUser);
				return new ResponseEntity<Users>(newUser, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"User with Id: " + newUser.getUserId() + " already exists!!");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Users updateUser(Users updateUser) {
		Optional<Users> findUserById = userRepository.findById(updateUser.getUserId());
		if (findUserById.isPresent()) {
			userRepository.save(updateUser);
		} else
			throw new RecordNotFoundException(
					"User with Id: " + updateUser.getUserId() + " not exists!!");
		return updateUser;
	}

	@Override
	public String deleteUser(BigInteger UserId) {
		Optional<Users> findBookingById = userRepository.findById(UserId);
		if (findBookingById.isPresent()) {
			userRepository.deleteById(UserId);
			return "User Deleted!!";
		} else
			throw new RecordNotFoundException("User not found for the entered UserID");
	}

	@Override
	public Iterable<Users> displayAllUser() {
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<?> findUserById(BigInteger userId) {
		Optional<Users> findById = userRepository.findById(userId);
		try {
			if (findById.isPresent()) {
				Users findUser = findById.get();
				return new ResponseEntity<Users>(findUser, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + userId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}