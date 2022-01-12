package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "cars")
public class Car {
	@Id
	@Column(name = "plate")
	private String plate;
	@Column(name = "type")
	private String type;
	@Column(name = "make")
	private String make;
	@Column(name = "model")
	private String model;
	@Column(name = "fuel")
	private String fuel;
	public Car(String plate, String type, String make, String model, String fuel) {
		this.plate = plate;
		this.type = type;
		this.make = make;
		this.model = model;
		this.fuel = fuel;
	}
	public Car() {
		
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	
	
}
