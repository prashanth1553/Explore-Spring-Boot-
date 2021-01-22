package com.myspringboot.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.myspringboot.models.Country;
import com.myspringboot.repository.CountryRepository;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class CountryServiceTest {

	@Tested
	private CountryService countryService;

	@Injectable
	private CountryRepository repo;

	@Test
	public void testCreate() {
		Country country = new Country();
		new Expectations() {
			{
				repo.save(country);
				country.setId(1l);
				result = country;
			}
		};
		assertTrue(countryService.create(country).getId().equals(1l));
	}

}
