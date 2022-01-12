package cct.gersgarage.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cct.gersgarage.springboot.model.PartUse;
import cct.gersgarage.springboot.repository.PartUseRepository;

@RestController
@RequestMapping("/partuse")
public class PartUseController {
	@Autowired
	private PartUseRepository partUseRepository;
	

	@GetMapping("/partuse")
	public List<PartUse> getAllPartUse(){
		
		return partUseRepository.findAll();
	}
}
