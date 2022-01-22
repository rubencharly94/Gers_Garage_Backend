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
	private int partID;
	@Column(name = "cost")
	private double cost;
	@Column(name = "description")
	private String description;
	public Part(int partID, double cost,String description) {
		this.partID = partID;
		this.cost = cost;
		this.description = description;
	}
	public Part() {
		
	}
	public int getPartID() {
		return partID;
	}
	public void setPartID(int partID) {
		this.partID = partID;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
