package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
	
	List<Pet> getAllByCustomerId(Long customerId);
	

}