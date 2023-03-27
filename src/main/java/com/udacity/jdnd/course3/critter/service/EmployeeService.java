package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmploeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
@Service
public class EmployeeService {

	EmploeeRepository empRepo;

	public EmployeeService(EmploeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	public Employee saveEmployee(Employee Employee) {
		return empRepo.save(Employee);
	}

	public Employee getEmployee(long employeeId) {
		return empRepo.findById(employeeId).get();
	}

	public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
		Employee updateEmp = empRepo.findById(employeeId).get();
		updateEmp.setDaysAvailable(daysAvailable);
		empRepo.save(updateEmp);

	}

	public List<Employee> findEmployeesForService(EmployeeRequestDTO Employee) {


        List<Employee> employees = empRepo
                .getAllByDaysAvailableContains(Employee.getDate().getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(Employee.getSkills()))
                .collect(Collectors.toList());
        return employees;
    }

}
