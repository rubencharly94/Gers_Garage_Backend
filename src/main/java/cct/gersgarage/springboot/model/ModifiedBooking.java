package cct.gersgarage.springboot.model;

public class ModifiedBooking {
	private int serviceID;
	private int mechanicID;
	private PartUse[] parts;
	private String status;
	public ModifiedBooking(int serviceID, int mechanicID, PartUse[] parts, String status) {
		this.serviceID = serviceID;
		this.mechanicID = mechanicID;
		this.parts = parts;
		this.status = status;
	}
	public ModifiedBooking() {
		
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public int getMechanicID() {
		return mechanicID;
	}
	public void setMechanicID(int mechanicID) {
		this.mechanicID = mechanicID;
	}
	public PartUse[] getParts() {
		return parts;
	}
	public void setParts(PartUse[] parts) {
		this.parts = parts;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
