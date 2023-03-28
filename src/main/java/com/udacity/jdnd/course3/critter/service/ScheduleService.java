package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmploeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
@Service
public class ScheduleService {
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	EmploeeRepository employeesRepository;
	@Autowired
	PetRepository petRepo;
	@Autowired
	CustomerRepository customerRepository;

	public ScheduleService(ScheduleRepository repo) {
		scheduleRepository = repo;
	}

	public List<Schedule> getScheduleForEmployee(long employeeId) {
        Employee employee = employeesRepository.getOne(employeeId);
		return scheduleRepository.getAllByEmployeesContains(employee);
	}

	public List<Schedule> getScheduleForPet(long petId) {
		return scheduleRepository.findAllByPetsId(petId);
	}

	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}

	public List<Schedule> getScheduleForCustomer(long customerId) {
		return scheduleRepository.findAllByCustomerId(customerId);
	}

	public Schedule createSchedule(Schedule Schedule) {
		return scheduleRepository.save(Schedule);
	}
	@Transactional
	public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
		List<Employee> employees = employeesRepository.findAllById(employeeIds);
		List<Pet> pets = petRepo.findAllById(petIds);
		schedule.setEmployees(employees);
		schedule.setPets(pets);
		return scheduleRepository.save(schedule);
	}

}
