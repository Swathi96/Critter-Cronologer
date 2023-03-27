package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into
 * separate user and customer controllers would be fine too, though that is not
 * part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	CustomerService custService;
	@Autowired
	EmployeeService empService;


	@PostMapping("/customer")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setName(customerDTO.getName());
		customer.setNotes(customerDTO.getNotes());
		customer.setPhoneNumber(customerDTO.getPhoneNumber());
		List<Long> pets = customerDTO.getPetIds();
		customer = custService.saveCustomer(customer, pets);
		return getCustomerDTO(customer);

	}

	@GetMapping("/customer")
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customerList = custService.getAllCustomers();
		return customerList.stream().map(this::getCustomerDTO).collect(Collectors.toList());

	}

	@GetMapping("/customer/pet/{petId}")
	public CustomerDTO getOwnerByPet(@PathVariable long petId) {
		return getCustomerDTO(custService.getOwnerByPet(petId));

	}

	@PostMapping("/employee")
	public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setName(employeeDTO.getName());
		employee.setDaysAvailable(employeeDTO.getDaysAvailable());

		employee.setSkills(employeeDTO.getSkills());

		return getEmployeeDTO(empService.saveEmployee(employee));

	}

	@PostMapping("/employee/{employeeId}")
	public EmployeeDTO getEmployee(@PathVariable long employeeId) {
		return getEmployeeDTO(empService.getEmployee(employeeId));

	}

	@PutMapping("/employee/{employeeId}")
	public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
		empService.setAvailability(daysAvailable, employeeId);

	}

	@GetMapping("/employee/availability")
	public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
		List<Employee> employeeList = empService.findEmployeesForService(employeeDTO);
		return employeeList.stream().map(this::getEmployeeDTO).collect(Collectors.toList());
	}

	private CustomerDTO getCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setPhoneNumber(customer.getPhoneNumber());
		customerDTO.setNotes(customer.getNotes());
		List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
		customerDTO.setPetIds(petIds);
		return customerDTO;
	}

	private EmployeeDTO getEmployeeDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(employee.getId());
		employeeDTO.setName(employee.getName());
		employeeDTO.setSkills(employee.getSkills());
		employeeDTO.setDaysAvailable(employee.getDaysAvailable());
		return employeeDTO;
	}

}
