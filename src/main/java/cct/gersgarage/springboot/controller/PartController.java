package cct.gersgarage.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cct.gersgarage.springboot.model.Part;
import cct.gersgarage.springboot.repository.PartRepository;

@RestController
@RequestMapping("/parts")
public class PartController {
	@Autowired
	private PartRepository partRepository;
	

	@GetMapping("/partlist")
	public List<Part> getAllParts(){
		
		return partRepository.findAll();
	}
}