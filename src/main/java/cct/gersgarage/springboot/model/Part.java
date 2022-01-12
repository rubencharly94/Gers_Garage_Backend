package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "parts")
public class Part {
	@Id
	@Column(name = "partid")
	private String partID;
	@Column(name = "cost")
	private double cost;
	public Part(String partID, double cost) {
		this.partID = partID;
		this.cost = cost;
	}
	public Part() {
		
	}
	public String getPartID() {
		return partID;
	}
	public void setPartID(String partID) {
		this.partID = partID;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
