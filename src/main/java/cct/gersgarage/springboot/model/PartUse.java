package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(PartUseID.class)
@Table(name= "partuse")
public class PartUse {
	@Id
	@Column(name = "serviceid")
	private int serviceID;
	@Id
	@Column(name = "partid")
	private int partID;
	@Column(name = "quantity")
	private int quantity;
	public PartUse(int serviceID, int partID, int quantity) {
		this.serviceID = serviceID;
		this.partID = partID;
		this.quantity = quantity;
	}
	
	public PartUse() {
		
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getPartID() {
		return partID;
	}

	public void setPartID(int partID) {
		this.partID = partID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
