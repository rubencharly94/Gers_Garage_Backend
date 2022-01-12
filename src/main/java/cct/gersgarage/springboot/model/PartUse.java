package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "partuse")
public class PartUse {
	@Id
	@Column(name = "serviceid")
	private int serviceID;
	@Column(name = "partid")
	private String partID;
	public PartUse(int serviceID, String partID) {
		this.serviceID = serviceID;
		this.partID = partID;
	}
	
	public PartUse() {
		
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getPartID() {
		return partID;
	}

	public void setPartID(String partID) {
		this.partID = partID;
	}
	
}
