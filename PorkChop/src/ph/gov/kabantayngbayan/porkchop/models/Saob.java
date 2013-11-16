package ph.gov.kabantayngbayan.porkchop.models;

public class Saob {
	
	private String year;
	private String period;
	private String agency;
	private SaobBudget budget;
	private String id;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public SaobBudget getBudget() {
		return budget;
	}

	public void setBudget(SaobBudget budget) {
		this.budget = budget;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
