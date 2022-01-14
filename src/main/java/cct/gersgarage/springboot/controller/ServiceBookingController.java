package cct.gersgarage.springboot.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
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
import cct.gersgarage.springboot.model.Mechanic;
import cct.gersgarage.springboot.model.ServiceBooking;
import cct.gersgarage.springboot.model.User;
import cct.gersgarage.springboot.repository.CarRepository;
import cct.gersgarage.springboot.repository.MechanicRepository;
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
	@Autowired
	private MechanicRepository mechanicRepository;
	
	

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
	
	@GetMapping("/getpastbookingdetails/{user}")
	public ServiceBooking getPastBookingDetails(@PathVariable("user") String user) {
		ServiceBooking bookingtemp = new ServiceBooking();
		for(ServiceBooking booking: serviceBookingRepository.findAll()) {
			if(booking.getUserID()!=null)
				if(booking.getUserID().equals(user)) {
					bookingtemp =booking;
				}
		}
		return bookingtemp;
	}
	
	@GetMapping("/getpastdetails/{user}")
	public User getPastDetails(@PathVariable("user") String user) {
		User usertemp = new User();
		for(User userelement: userRepository.findAll()) {
			if(userelement.getUserID()!=null)
				if(userelement.getUserID().equals(user)) {
					usertemp =userelement;
				}
		}
		return usertemp;
	}
	
	@PostMapping("/savebooking")
	public boolean postBooking(@RequestBody BookingForm booking) {
		checkDatesAvailability(); //delete later
		User user = new User();
		user.setUserID("3");
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
		serviceBooking.setUserID("usertest");
		serviceBooking.setMechanicID(0);
		serviceBooking.setCarID(booking.getPlate());
		serviceBooking.setRepairID(booking.getServiceType()+booking.getVehType());
		
		boolean existentUser=false;						//checks if the user and car already exists to save user and car or not
		for(User userline: userRepository.findAll()) {
			if(userline.getUserID().equals(user.getUserID())) {
				existentUser = true;
			}
			if(!existentUser) {
				userRepository.save(user);
			}
		}
		
		boolean existentCar=false;
		for(Car carline: carRepository.findAll()) {
			if(carline.getPlate().equals(car.getPlate())) {
				existentCar = true;
			}
			if(!existentCar) {
				carRepository.save(car);
			}
		}
		
		if(getMechanicsAvailability(serviceBooking.getDate(),1,serviceBooking.getRepairID())) {
			serviceBooking.setMechanicID(1);
			serviceBookingRepository.save(serviceBooking);
		} else if(getMechanicsAvailability(serviceBooking.getDate(),2,serviceBooking.getRepairID())) {
			serviceBooking.setMechanicID(2);
			serviceBookingRepository.save(serviceBooking);
		} else if(getMechanicsAvailability(serviceBooking.getDate(),3,serviceBooking.getRepairID())) {
			serviceBooking.setMechanicID(3);
			serviceBookingRepository.save(serviceBooking);
		} else if(getMechanicsAvailability(serviceBooking.getDate(),4,serviceBooking.getRepairID())) {
			serviceBooking.setMechanicID(4);
			serviceBookingRepository.save(serviceBooking);
		} else {
			System.out.println("no availability");
			return false;
		}
		return true;
	}
	
	public boolean getMechanicsAvailability(String date, int mechanicID,String repairType) {
		int services = 0;
		for(ServiceBooking booking: serviceBookingRepository.findAll()) {
			if(booking.getDate().equals(date)&&booking.getMechanicID()==mechanicID) {
				if(booking.getRepairID().contains("MajorRepair")) {
					services+=2;
				} else {
					services+=1;
				}
			}
		}
		if(repairType.contains("MajorRepair")&&services>=3) {
			return false;
		} else if(services>=4) {
			return false;
		} else {
			return true;
		}
	}
	
	public void checkDatesAvailability() {
		//List<Date> unavailableDates = new ArrayList<Date>();
		LocalDate dateTemp = LocalDate.now();
		Hashtable<Integer,Integer> mechanicDict = new Hashtable<Integer,Integer>(); //cambiar a cada fecha
		for(Mechanic mechanic: mechanicRepository.findAll()) {
			mechanicDict.put(mechanic.getMechanicID(), 0);
		}
		int numberServices = 0;
		for(LocalDate date = LocalDate.now(); date.isBefore(dateTemp.plusDays(200)); date = date.plusDays(1)) {
			for(ServiceBooking booking: serviceBookingRepository.findAll()) {
				if(booking.getDate().equals(date.toString())) {
					for(Mechanic mechanic: mechanicRepository.findAll()) {
						if(booking.getMechanicID()==mechanic.getMechanicID()&&booking.getRepairID().contains("MajorRepair")) {
							numberServices = mechanicDict.get(mechanic.getMechanicID());
							mechanicDict.replace(mechanic.getMechanicID(), numberServices+2);
						} else if(booking.getMechanicID()==mechanic.getMechanicID()){
							numberServices = mechanicDict.get(mechanic.getMechanicID());
							mechanicDict.replace(mechanic.getMechanicID(), numberServices+1);
						}
					}
				}
			}
		}
		System.out.println(mechanicDict);
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
	
	@GetMapping("/getbookingadmin/{serviceid}")
	public ServiceBooking getBookingAdmin(@PathVariable("serviceid") int serviceid) {
		ServiceBooking booking = new ServiceBooking();
		for(ServiceBooking serviceBooking: serviceBookingRepository.findAll()) {
			if(serviceBooking.getServiceID()==serviceid) {
				booking= serviceBooking;
			}
		}
		return booking;
	}
	
	
}
