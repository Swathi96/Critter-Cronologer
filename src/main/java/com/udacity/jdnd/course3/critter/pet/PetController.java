package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
	PetService petService;
    @Autowired
    CustomerService custService;

	
	@PostMapping
	public PetDTO savePet(@RequestBody PetDTO petDTO) {
		Pet pet = new Pet();
		pet.setDob(petDTO.getBirthDate());
		pet.setName(petDTO.getName());
		pet.setPetType(petDTO.getType());
		pet.setCustomer(custService.getCustomerById(petDTO.getOwnerId()));
		
		return getPetDTO(petService.savePet(pet));
	}

	@GetMapping("/{petId}")
	public PetDTO getPet(@PathVariable long petId) {
		return getPetDTO(petService.getPet(petId));
	}

	@GetMapping
	public List<PetDTO> getPets() {

		List<Pet> pets = petService.getPets();
		return pets.stream().map(this::getPetDTO).collect(Collectors.toList());

	}

	@GetMapping("/owner/{ownerId}")
	public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
		List<Pet> pets =  petService.getPetsByOwner(ownerId);
		
		return pets.stream().map(this::getPetDTO).collect(Collectors.toList());
	}
	
	private PetDTO getPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getPetType());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setBirthDate(pet.getDob());
        petDTO.setNotes(pet.getNotes());
        return petDTO;
    }
}
