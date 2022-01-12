package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "mechanics")
public class Mechanic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mechanicID;
	@Column(name = "name")
	private String name;
	public Mechanic(String name) {
		this.name = name;
	}
	public Mechanic() {
		
	}
	public int getMechanicID() {
		return mechanicID;
	}
	public void setMechanicID(int mechanicID) {
		this.mechanicID = mechanicID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
