package com.myspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.myspringboot.models.State;

public interface StateRepository extends CrudRepository<State, Long> {

//	@Query("select s from State s where s.country=:countryId")
//	public List<State> getByCountry(long countryId);

	public List<State> findByCountry_Id(long countryId);

	@Query("FROM State AS st LEFT JOIN st.country AS ct WHERE ct.id = :countryId")
	public List<State> findByCountry(long countryId);

}
