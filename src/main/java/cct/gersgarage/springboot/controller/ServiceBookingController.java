package cct.gersgarage.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cct.gersgarage.springboot.model.BookingForm;
import cct.gersgarage.springboot.model.Car;
import cct.gersgarage.springboot.model.ServiceBooking;
import cct.gersgarage.springboot.model.User;
import cct.gersgarage.springboot.repository.CarRepository;
import cct.gersgarage.springboot.repository.RepairTypeRepository;
import cct.gersgarage.springboot.repository.ServiceBookingRepository;
import cct.gersgarage.springboot.repository.UserRepository;

@CrossOrigin(origins = "http://192.168.1.67")
@RestController
@RequestMapping("/book")
public class ServiceBookingController {
	@Autowired
	private ServiceBookingRepository serviceBookingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private RepairTypeRepository repairTypeRepository;
	
	

	@GetMapping("/getbookings")
	public List<ServiceBooking> getAllBookings(){
		return serviceBookingRepository.findAll();
	}
	
	@GetMapping("/getbookings/{user}")
	public List<ServiceBooking> getBookingsCustomer(@PathVariable("user") String user){
		List<ServiceBooking> customerBookings = new ArrayList<ServiceBooking>();
		for(ServiceBooking booking : serviceBookingRepository.findAll()) {
			if(booking.getUserID()!=null) {
				if(booking.getUserID().equals(user)) {
					customerBookings.add(booking);
				}
			}
		}
		return customerBookings;
	}
	
	@PostMapping("/savebooking")
	public BookingForm createExpense(@RequestBody BookingForm booking) {
		
		User user = new User();
		user.setUserID("2");
		user.setName(booking.getName());
		user.setPhone(booking.getPhone());
		user.setEmail(booking.getEmail());
		
		Car car = new Car();
		car.setPlate(booking.getPlate());
		car.setType(booking.getVehType());
		car.setMake(booking.getVehMake());
		car.setModel(booking.getVehModel());
		car.setFuel(booking.getVehFuel());
		
		ServiceBooking serviceBooking = new ServiceBooking();
		serviceBooking.setDate(booking.getDate());
		serviceBooking.setComments(booking.getComments());
		serviceBooking.setStatus("booked");
		serviceBooking.setUserID("abc");
		serviceBooking.setMechanicID("1");
		serviceBooking.setCarID(booking.getPlate());
		serviceBooking.setRepairID(booking.getServiceType()+booking.getVehType());
		//String user = "{ userID: 'test', name: '" + booking.getName() + "', phone: '" + booking.getPhone() + "', email:'" + booking.getEmail() +"'}"; 
		//JSONObject userJson = new JSONObject(user);
		userRepository.save(user);
		carRepository.save(car);
		//JSONObject bookingJson = new JSONObject(booking);
		serviceBookingRepository.save(serviceBooking);
		return booking;
	}
	
	@GetMapping("/bookingsDay/{day}/{month}/{year}")
	public List<ServiceBooking> getBookingsDay(@PathVariable("day") String day,@PathVariable("month") String month,@PathVariable("year") String year){
		List<ServiceBooking> bookingsDay = new ArrayList<ServiceBooking>();
		for(ServiceBooking booking : serviceBookingRepository.findAll()) {
			if(booking.getDate()!=null) {
				if(booking.getDate().equals(day+"/"+month+"/"+year)) {
					bookingsDay.add(booking);
				}
			}
		}
		return bookingsDay;
	}
	
	
	
}
