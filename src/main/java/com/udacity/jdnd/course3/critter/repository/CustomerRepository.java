package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
