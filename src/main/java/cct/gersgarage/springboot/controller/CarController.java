package cct.gersgarage.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cct.gersgarage.springboot.model.Car;
import cct.gersgarage.springboot.repository.CarRepository;

@RestController
@RequestMapping("/cars")
public class CarController {
	@Autowired
	private CarRepository carRepository;
	

	@GetMapping("/carlist")
	public List<Car> getAllTrips(){
		
		return carRepository.findAll();
	}
	
	
	
}
