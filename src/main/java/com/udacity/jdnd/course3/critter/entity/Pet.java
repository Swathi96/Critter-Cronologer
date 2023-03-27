package com.udacity.jdnd.course3.critter.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.udacity.jdnd.course3.critter.pet.PetType;

import lombok.Data;

@Entity
@Data
public class Pet implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private PetType petType;
	
	private String name;
	
	@ManyToOne(targetEntity = Customer.class,optional = false)
	private Customer customer;
	
	private LocalDate dob;
	
	private String notes;

}
