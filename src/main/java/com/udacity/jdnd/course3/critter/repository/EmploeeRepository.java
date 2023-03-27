package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.entity.Employee;

public interface EmploeeRepository  extends JpaRepository<Employee,Long> {
    List<Employee> getAllByDaysAvailableContains(DayOfWeek dayOfWeek);
}

