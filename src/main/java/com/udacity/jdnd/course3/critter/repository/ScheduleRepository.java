package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	
	List<Schedule> findAllByPetsId(Long id);
	
	List<Schedule> getAllByEmployeesContains(Employee employee);
	
	@Query("SELECT s FROM Schedule s JOIN s.pets AS p JOIN p.customer AS c WHERE c.id = :id")
	List<Schedule> findAllByCustomerId(Long id);
	

}
