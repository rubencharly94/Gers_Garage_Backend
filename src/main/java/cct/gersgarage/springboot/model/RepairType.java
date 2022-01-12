package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "repairtype")
public class RepairType {
	@Id
	@Column(name = "repairid")
	private String repairID;
	@Column(name = "cost")
	private double cost;
	public RepairType(String repairID, double cost) {
		this.repairID = repairID;
		this.cost = cost;
	}
	public RepairType() {
		
	}
	public String getRepairID() {
		return repairID;
	}
	public void setRepairID(String repairID) {
		this.repairID = repairID;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
