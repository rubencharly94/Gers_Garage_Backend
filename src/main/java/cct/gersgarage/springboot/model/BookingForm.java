package cct.gersgarage.springboot.model;


public class BookingForm {
	private String serviceType;
	private String name;
	private String phone;
	private String email;
	private String date;
	private String vehType;
	private String vehMake;
	private String vehModel;
	private String vehFuel;
	private String plate;
	private String comments;
	public BookingForm(String serviceType, String name, String phone, String email, String date, String vehType,
			String vehMake, String vehModel,String vehFuel,  String plate, String comments) {
		super();
		this.serviceType = serviceType;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.date = date;
		this.vehType = vehType;
		this.vehMake = vehMake;
		this.vehModel = vehModel;
		this.vehFuel = vehFuel;
		this.plate = plate;
		this.comments = comments;
	}
	public BookingForm() {
		
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVehType() {
		return vehType;
	}
	public void setVehType(String vehType) {
		this.vehType = vehType;
	}
	public String getVehMake() {
		return vehMake;
	}
	public void setVehMake(String vehMake) {
		this.vehMake = vehMake;
	}
	
	public String getVehModel() {
		return vehModel;
	}
	public void setVehModel(String vehModel) {
		this.vehModel = vehModel;
	}
	public String getVehFuel() {
		return vehFuel;
	}
	public void setVehFuel(String vehFuel) {
		this.vehFuel = vehFuel;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
