package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
	ScheduleService schedService;


	@PostMapping
	public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
         Schedule schedule = new Schedule();
         schedule.setActivities(scheduleDTO.getActivities());
         schedule.setDate(scheduleDTO.getDate());
		return getScheduleDTO(schedService.saveSchedule(schedule,scheduleDTO.getEmployeeIds(),scheduleDTO.getPetIds()));
	}

	@GetMapping
	public List<ScheduleDTO> getAllSchedules() {
		List<Schedule> schList=  schedService.getAllSchedules();
		return schList.stream().map(this::getScheduleDTO).collect(Collectors.toList());
	}

	@GetMapping("/pet/{petId}")
	public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
		List<Schedule> schList=  schedService.getScheduleForPet(petId);
		return schList.stream().map(this::getScheduleDTO).collect(Collectors.toList());
	}

	@GetMapping("/employee/{employeeId}")
	public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
		List<Schedule> schList= schedService.getScheduleForEmployee(employeeId);
		return schList.stream().map(this::getScheduleDTO).collect(Collectors.toList());
	}

	@GetMapping("/customer/{customerId}")
	public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
		List<Schedule> schList= schedService.getScheduleForCustomer(customerId);
		return schList.stream().map(this::getScheduleDTO).collect(Collectors.toList());
	}
	
	private ScheduleDTO getScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());
        return scheduleDTO;
    }
}
