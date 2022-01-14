package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "bookings")
public class ServiceBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceID;
	@Column(name = "date")
	private String date;
	@Column(name = "comments")
	private String comments;
	@Column(name = "status")
	private String status;
	@Column(name = "userid")
	private String userID;
	@Column(name = "carid")
	private String carID;
	@Column(name = "repairid")
	private String repairID;
	@Column(name = "mechanicid")
	private Integer mechanicID;
	
	public ServiceBooking() {
		
	}
	
	public ServiceBooking(String date, String comments, String status, String userID, String carID, String repairID,
			int mechanicID) {
		this.date = date;
		this.comments = comments;
		this.status = status;
		this.userID = userID;
		this.carID = carID;
		this.repairID = repairID;
		this.mechanicID = mechanicID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCarID() {
		return carID;
	}

	public void setCarID(String carID) {
		this.carID = carID;
	}

	public String getRepairID() {
		return repairID;
	}

	public void setRepairID(String repairID) {
		this.repairID = repairID;
	}

	public int getMechanicID() {
		return mechanicID;
	}

	public void setMechanicID(int mechanicID) {
		this.mechanicID = mechanicID;
	}
	
	
	
	
	
}
