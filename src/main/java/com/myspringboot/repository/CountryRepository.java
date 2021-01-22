package com.myspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myspringboot.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	public List<Country> findByNameIgnoreCase(String name);
}
