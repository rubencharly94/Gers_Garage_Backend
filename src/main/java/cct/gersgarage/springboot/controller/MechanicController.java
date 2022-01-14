package cct.gersgarage.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cct.gersgarage.springboot.model.Mechanic;
import cct.gersgarage.springboot.repository.MechanicRepository;

@RestController
@RequestMapping("/mechanics")
public class MechanicController {
	@Autowired
	private MechanicRepository mechanicRepository;
	

	@GetMapping("/mechaniclist")
	public List<Mechanic> getAllMechanics(){
		
		return mechanicRepository.findAll();
	}
	

}
