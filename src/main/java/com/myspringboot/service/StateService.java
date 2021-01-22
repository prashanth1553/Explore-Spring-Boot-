package com.myspringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.models.State;
import com.myspringboot.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public Optional<State> getById(long id) {
		return stateRepository.findById(id);
	}

	public State create(State state) {
		return stateRepository.save(state);
	}

	public List<State> getByCountry(long countryId) {
		return stateRepository.findByCountry(countryId);
	}
}
