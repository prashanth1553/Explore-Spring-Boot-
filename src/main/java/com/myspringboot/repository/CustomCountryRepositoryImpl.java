package com.myspringboot.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.myspringboot.models.Country;

public class CustomCountryRepositoryImpl implements CustomCountryRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Country> getCountryByName(String name) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Country> query = cb.createQuery(Country.class);
		Root<Country> root = query.from(Country.class);
		query.where(cb.equal(root.get("name"), name));

		return entityManager.createQuery(query).getResultList();
	}

}
