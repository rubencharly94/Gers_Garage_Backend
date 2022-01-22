package cct.gersgarage.springboot.model;

import java.io.Serializable;

public class PartUseID implements Serializable {
	
	    private int serviceID;

	    private int partID;

	    public PartUseID() {
	    	
	    }

	    public PartUseID(int serviceID, int partID) {
	        this.serviceID = serviceID;
	        this.partID = partID;
	    }

	    // equals() and hashCode()
}
