package com.udacity.jdnd.course3.critter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
public class PetService {
	@Autowired
	PetRepository petRepo;
	@Autowired
	CustomerRepository custRepo;

	public Pet getPet(long petId) {
		return petRepo.findById(petId).get();
	}
	@Transactional
	public Pet savePet(Pet pet) {
		List<Pet> oldPets = pet.getCustomer().getPets();
		List<Pet> updatedPets = new ArrayList<Pet>(oldPets);
		updatedPets.add(pet);
		Customer customer = pet.getCustomer();
		customer.setPets(updatedPets);
		Pet savedPet =petRepo.save(pet);
		 custRepo.save(customer);
		 return savedPet;
		 
	}

	public List<Pet> getPetsByOwner(long ownerId) {
		return petRepo.getAllByCustomerId(ownerId);
	}

	public List<Pet> getPets() {
		return petRepo.findAll();
	}

}
