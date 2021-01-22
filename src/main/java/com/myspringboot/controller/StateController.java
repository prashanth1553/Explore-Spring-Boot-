package com.myspringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.models.State;
import com.myspringboot.service.StateService;

@RestController(value = "state")
@RequestMapping("/state")
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping("/{id}")
	public ResponseEntity<State> getState(@PathVariable long id) {
		Optional<State> state = stateService.getById(id);
		if (state.isPresent()) {
			return ResponseEntity.ok(state.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<State> createState(@RequestBody State state) {
		state = stateService.create(state);
		return new ResponseEntity<State>(state, HttpStatus.CREATED);
	}

	@GetMapping("/country/{countryId}")
	public ResponseEntity<List<State>> getByCountry(@PathVariable long countryId) {
		return ResponseEntity.ok(stateService.getByCountry(countryId));
	}

}
