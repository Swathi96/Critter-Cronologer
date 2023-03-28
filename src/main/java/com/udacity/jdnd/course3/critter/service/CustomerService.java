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
public class CustomerService {

	@Autowired
	CustomerRepository custRepo;
	@Autowired
	PetRepository petRepo;



	
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	public Customer getOwnerByPet(long petId) {

		return petRepo.findById(petId).get().getCustomer();
	}

	public Customer getCustomerById(long ownerId) {
	return custRepo.findById(ownerId).get();
		
	}
	@Transactional
	public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map((petId) -> petRepo.getOne(petId)).collect(Collectors.toList());
        }
        customer.setPets(pets);
        return custRepo.save(customer);
    }

}
