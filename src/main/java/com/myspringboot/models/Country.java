package com.myspringboot.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<State> states;

	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User created_by;

//	Validation test
//	@Max(100)
//	@Min(50)
//	private Integer rangeTest;

	@ManyToOne
	@JoinColumn(name = "updated_by", nullable = false)
	private User updated_by;

	@Column(nullable = false)
	private LocalDateTime created_datetime;
	@Column(nullable = false)
	private LocalDateTime updated_datetime;

	public Country(String name) {
		super();
		this.name = name;
	}

	public Country() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public User getCreatedBy() {
		return created_by;
	}

	public void setCreatedBy(User created_by) {
		this.created_by = created_by;
	}

	public User getUpdatedBy() {
		return updated_by;
	}

	public void setUpdatedBy(User updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(LocalDateTime created_datetime) {
		this.created_datetime = created_datetime;
	}

	public LocalDateTime getUpdatedDatetime() {
		return updated_datetime;
	}

	public void setUpdatedDatetime(LocalDateTime updated_datetime) {
		this.updated_datetime = updated_datetime;
	}

	@PrePersist
	public void prePersist() {
		this.created_datetime = LocalDateTime.now();
		this.updated_datetime = LocalDateTime.now();
	}

//	public Integer getRangeTest() {
//		return rangeTest;
//	}
//
//	public void setRangeTest(Integer rangeTest) {
//		this.rangeTest = rangeTest;
//	}

}
