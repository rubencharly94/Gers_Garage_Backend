package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "carmakes")
public class CarMake {
	@Id
	@Column(name = "Id")
	private int MakeId;
	@Column(name = "Name")
	private String MakeName;
	public CarMake(int makeId, String makeName) {
		MakeId = makeId;
		MakeName = makeName;
	}
	public CarMake() {
		
	}
	public int getMakeId() {
		return MakeId;
	}
	public void setMakeId(int makeId) {
		MakeId = makeId;
	}
	public String getMakeName() {
		return MakeName;
	}
	public void setMakeName(String makeName) {
		MakeName = makeName;
	}
	
}
