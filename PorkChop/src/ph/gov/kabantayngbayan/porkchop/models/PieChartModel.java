package ph.gov.kabantayngbayan.porkchop.models;

import java.io.Serializable;

public class PieChartModel implements Serializable{

	private String category;
	private double value;
	
	public PieChartModel(String category, double value) {
		this.category = category;
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
