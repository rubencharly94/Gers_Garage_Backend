package cct.gersgarage.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "carmodels")
public class CarModel {
	@Id
	@Column(name = "Id")
	private int ModelId;
	@Column(name = "makeid")
	private int MakeId;
	@Column(name = "Name")
	private String Model;
	@Column(name = "Series")
	private String Series;
	public CarModel(int modelId, int makeId, String model, String series) {
		ModelId = modelId;
		MakeId = makeId;
		Model = model;
		Series = series;
	}
	public CarModel() {
		
	}
	public int getModelId() {
		return ModelId;
	}
	public void setModelId(int modelId) {
		ModelId = modelId;
	}
	public int getMakeId() {
		return MakeId;
	}
	public void setMakeId(int makeId) {
		MakeId = makeId;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getSeries() {
		return Series;
	}
	public void setSeries(String series) {
		Series = series;
	}
	
}
