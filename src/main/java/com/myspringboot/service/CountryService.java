package com.myspringboot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myspringboot.models.Country;
import com.myspringboot.repository.CountryRepository;

@Component
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Transactional
	public Country create(Country country) {
		return countryRepository.save(country);
	}

	public Optional<Country> getById(long id) {
		return countryRepository.findById(id);
	}

	@Transactional
	public Country update(Country country) {
		return countryRepository.save(country);
	}

	@Transactional
	public List<Country> findByName(String name) {
		return countryRepository.findByNameIgnoreCase(name);
	}

}
