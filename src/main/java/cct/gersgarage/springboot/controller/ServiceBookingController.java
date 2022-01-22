package cct.gersgarage.springboot.controller;
import java.util.Base64;
import com.google.gson.Gson;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

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
import cct.gersgarage.springboot.model.CarMake;
import cct.gersgarage.springboot.model.CarModel;
import cct.gersgarage.springboot.model.Mechanic;
import cct.gersgarage.springboot.model.ModifiedBooking;
import cct.gersgarage.springboot.model.Part;
import cct.gersgarage.springboot.model.PartUse;
import cct.gersgarage.springboot.model.RepairType;
import cct.gersgarage.springboot.model.ServiceBooking;
import cct.gersgarage.springboot.model.User;
import cct.gersgarage.springboot.model.UserPass;
import cct.gersgarage.springboot.repository.CarMakeRepository;
import cct.gersgarage.springboot.repository.CarModelRepository;
import cct.gersgarage.springboot.repository.CarRepository;
import cct.gersgarage.springboot.repository.MechanicRepository;
import cct.gersgarage.springboot.repository.PartRepository;
import cct.gersgarage.springboot.repository.PartUseRepository;
import cct.gersgarage.springboot.repository.RepairTypeRepository;
import cct.gersgarage.springboot.repository.ServiceBookingRepository;
import cct.gersgarage.springboot.repository.UserRepository;
import cct.gersgarage.springboot.repository.UserPassRepository;

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
	@Autowired
	private CarMakeRepository carMakeRepository;
	@Autowired
	private CarModelRepository carModelRepository;
	@Autowired
	private PartRepository partRepository;
	@Autowired
	private PartUseRepository partUseRepository;
	@Autowired
	private UserPassRepository userPassRepository;
	

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
	
	@GetMapping("/getUnavailableDates/{repairType}")
	public List<String> checkDatesAvailability(@PathVariable("repairType") String repairType) {
		//List<Date> unavailableDates = new ArrayList<Date>();
		LocalDate dateTemp = LocalDate.now();
		List<String> unavailableDates = new ArrayList<String>(); 
		Hashtable<Integer,Integer> mechanicDict = new Hashtable<Integer,Integer>(); //cambiar a cada fecha
		for(Mechanic mechanic: mechanicRepository.findAll()) {
			mechanicDict.put(mechanic.getMechanicID(), 0);
		}
		int numberServices = 0;
		boolean availableMechanics = false;
		for(LocalDate date = LocalDate.now(); date.isBefore(dateTemp.plusDays(200)); date = date.plusDays(1)) { //checks each day for availability 
			numberServices = 0;
			availableMechanics = false;
			for(Mechanic mechanic: mechanicRepository.findAll()) {
				mechanicDict.replace(mechanic.getMechanicID(), 0);
			}
			for(ServiceBooking booking: serviceBookingRepository.findAll()) {			//checks all bookings and creates hashtable which shows how many services a mechanic has on that day
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
			
			for(int i=1;i<=mechanicRepository.findAll().size();i++) {			//checks if each mechanic has 4 service, otherwise there is availability
				if(repairType.contains("MajorRepair")) {					//if it is a major repair checks on availability of 2 spots
					if(mechanicDict.get(i)<3) {
						availableMechanics = true;
					}
				} else {
					if(mechanicDict.get(i)<4) {
						availableMechanics = true;
					}
				}
			}
			
			if(!availableMechanics) {			//adds to array the date if there is no available mechanics
				unavailableDates.add(date.toString());
			}
			
			
			//System.out.println(availableMechanics);
		}
		return unavailableDates;
	}
	
	@GetMapping("/bookingsDay/{date}")
	public List<ServiceBooking> getBookingsDay(@PathVariable("date") String date){
		List<ServiceBooking> bookingsDay = new ArrayList<ServiceBooking>();
		for(ServiceBooking booking : serviceBookingRepository.findAll()) {
			if(booking.getDate()!=null) {
				if(booking.getDate().equals(date)) {
					bookingsDay.add(booking);
				}
			}
		}
		return bookingsDay;
	}
	
	@GetMapping("/bookingsWeek/{date}")
	public List<ServiceBooking> getBookingsWeek(@PathVariable("date") String date){
		List<ServiceBooking> bookingsDay = new ArrayList<ServiceBooking>();
		LocalDate tempDate = LocalDate.parse(date);
		for(int i=0;i<7;i++) {
			for(ServiceBooking booking : serviceBookingRepository.findAll()) {
				if(booking.getDate()!=null) {
					if(booking.getDate().equals(tempDate.toString())) {
						bookingsDay.add(booking);
					}
				}
			}
			tempDate = tempDate.plusDays(1);
			System.out.println(tempDate);
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
	
	@GetMapping("/modelListGenerator/{makeid}")
	public List<CarModel> getAllModels(@PathVariable("makeid") int makeID){
		List<CarModel> carModels = new ArrayList<CarModel>();
		for(CarModel model:carModelRepository.findAll()) {
			if(model.getMakeId()==makeID) {
				carModels.add(model);
			}
		}
		return carModels;
	}
	
	@GetMapping("/makeListGenerator")
	public List<CarMake> getAllMakes(){
		return carMakeRepository.findAll();
	}
	
	@GetMapping("/partListGenerator")
	public List<Part> getAllParts(){
		return partRepository.findAll();
	}
	
	@GetMapping("/mechanicListGenerator")
	public List<Mechanic> getAllMechanics(){
		return mechanicRepository.findAll();
	}
	
	@GetMapping("/getCarInfo/{plate}")
	public Car getCarInfo(@PathVariable("plate") String plate){
		Car carFound = new Car();
		for(Car car:carRepository.findAll()) {
			if(car.getPlate().equals(plate)) {
				carFound=car;
			}
		}
		return carFound;
	}
	
	@GetMapping("/getFixedPrice/{repairtype}")
	public double getFixedPrice(@PathVariable("repairtype") String repairtype){
		RepairType repT = new RepairType();
		for(RepairType rep:repairTypeRepository.findAll()) {
			if(rep.getRepairID().equals(repairtype)) {
				repT = rep;
			}
		}
		return repT.getCost();
	}
	
	@PostMapping("/saveModifiedBooking") //later
	public boolean postModifiedBooking(@RequestBody ModifiedBooking booking) {
		System.out.println(booking.getMechanicID());
		for(ServiceBooking bookingTemp:serviceBookingRepository.findAll()) {
			if(bookingTemp.getServiceID()==booking.getServiceID()) {
				bookingTemp.setMechanicID(booking.getMechanicID());
				bookingTemp.setStatus(booking.getStatus());
				for(PartUse part:booking.getParts()) {
					partUseRepository.save(part);
					return true;
				}
			}
		}
		return false;
	}
	
	@GetMapping("/getPartsUsed/{serviceID}")
	public List<PartUse> getPartsUsed(@PathVariable("serviceID") String serviceID){
		List<PartUse> partsUsed = new ArrayList<PartUse>();
		for(PartUse part:partUseRepository.findAll()) {
			if(part.getServiceID()==Integer.parseInt(serviceID)) {
				partsUsed.add(part);
			}
		}
		return partsUsed;
	}
	
	@GetMapping("/getPartsUsedInfo/{partID}")
	public List<Part> getPartsUsedCost(@PathVariable("partID") String partID){
		List<Part> parts = new ArrayList<Part>();
		for(Part part:partRepository.findAll()) {
			if(part.getPartID()==Integer.parseInt(partID)) {
				parts.add(part);
			}
		}
		return parts;
	}
	
	@GetMapping("/getPartsUsedDesription/{partID}")
	public String getPartsUsedDescription(@PathVariable("partID") String partID){
		String desc = "";
		for(Part part:partRepository.findAll()) {
			if(part.getPartID()==Integer.parseInt(partID)) {
				desc = part.getDescription();
			}
		}
		return desc;
	}
	
	@GetMapping("/users/{token}")
	public boolean authentication(@PathVariable("token") String token){ //checks if the User and Password combination exists in the users table
		
		var accessgranted = false;
		
		String[] chunks = token.split("\\."); //receives the token from the app and decodes it to get the user and password
		Base64.Decoder decoder = Base64.getDecoder();

		String payload = new String(decoder.decode(chunks[1])); //payload contains the main object of the token
		
		Gson gson = new Gson();//having the String of payload, Gson creates a User object with that
		UserPass tempuser = gson.fromJson(payload , UserPass.class);
		
		for(UserPass user : userPassRepository.findAll()){//checks if user and password exist in the database
			if(user.getUser().equals(tempuser.getUser())&&user.getPassword().equals(tempuser.getPassword())) {
				accessgranted = true;
			}
		}
		
		return accessgranted;
		
	}
	
	
	
}
