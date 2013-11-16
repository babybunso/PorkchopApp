package ph.gov.kabantayngbayan.porkchop.models;


public class Particular {

	private String year;
	private String name;
	private double levels;			// IN BILIION
	private double percentShare;	// IN PERCENTAGE

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLevels() {
		return levels;
	}

	public void setLevels(double levels) {
		this.levels = levels;
	}

	public double getPercentShare() {
		return percentShare;
	}

	public void setPercentShare(double percentShare) {
		this.percentShare = percentShare;
	}

}