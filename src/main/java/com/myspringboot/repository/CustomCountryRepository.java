package com.myspringboot.repository;

import java.util.List;

import com.myspringboot.models.Country;

public interface CustomCountryRepository {

	public List<Country> getCountryByName(String name);

}
