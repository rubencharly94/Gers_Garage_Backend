package cct.gersgarage.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cct.gersgarage.springboot.model.ServiceBooking;
import cct.gersgarage.springboot.repository.ServiceBookingRepository;

@CrossOrigin(origins = "http://192.168.1.67")
@RestController
@RequestMapping("/book")
public class ServiceBookingController {
	@Autowired
	private ServiceBookingRepository serviceBookingRepository;
	
	
	

	@GetMapping("/getbookings")
	public List<ServiceBooking> getAllBookings(){
		
		return serviceBookingRepository.findAll();
	}
	
	@PostMapping("/savebooking")
	public boolean createExpense(@RequestBody ServiceBooking booking) {
		serviceBookingRepository.save(booking);
		return true;
	}
	
	
	
}
