package ph.gov.kabantayngbayan.porkchop.models;

public class Programs {

	private String program_desc;
	private String area_code;
	private String area_desc;
	private ProgramBudget budget;

	public String getProgram_desc() {
		return program_desc;
	}

	public void setProgram_desc(String program_desc) {
		this.program_desc = program_desc;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_desc() {
		return area_desc;
	}

	public void setArea_desc(String area_desc) {
		this.area_desc = area_desc;
	}

	public ProgramBudget getBudget() {
		return budget;
	}

	public void setBudget(ProgramBudget budget) {
		this.budget = budget;
	}

}
