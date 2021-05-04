package com.myspringboot.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myspringboot.exceptionhandler.ErrorMessage;
import com.myspringboot.exceptionhandler.ResourceNotFoundException;
import com.myspringboot.models.Country;
import com.myspringboot.models.User;
import com.myspringboot.service.CountryService;

@RestController(value = "country")
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@PostMapping()
	public ResponseEntity<Country> createCountry(@Valid @RequestBody Country country) {
		country.setCreatedBy(new User(1l));
		country.setUpdatedBy(new User(1l));
		country = countryService.create(country);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(country.getId())
				.toUri();
		return new ResponseEntity<Country>(country, HttpStatus.CREATED);
		// return ResponseEntity.created(location).body(country);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Country> getById(@PathVariable long id) {
		try {
			Country country = countryService.getById(id).get();
			return ResponseEntity.ok(country);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
			// return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/1/{id}")
	public Country getById1(@PathVariable long id) {
		try {
			Country country = countryService.getById(id).get();
			return country;
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Country> updateCountry(@RequestBody Country country, @PathVariable long id) {

		Optional<Country> studentOptional = countryService.getById(id);
		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		country.setId(id);
		countryService.update(country);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/byName/{name}")
	public ResponseEntity<List<Country>> findByName(@PathVariable String name) {
		return ResponseEntity.ok(countryService.findByName(name));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@GetMapping(value = "/short/{shortUrl}")
	public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {

		// redirection
		String url = "https://dzone.com/articles/url-shortener-detailed-explanation";
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
	}
}
