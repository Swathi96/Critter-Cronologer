package com.udacity.jdnd.course3.critter.entity;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import lombok.Data;

@Entity
@Data
public class Employee implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	@ElementCollection
	private Set<EmployeeSkill> skills;
	@ElementCollection
	private Set<DayOfWeek> daysAvailable;

}
