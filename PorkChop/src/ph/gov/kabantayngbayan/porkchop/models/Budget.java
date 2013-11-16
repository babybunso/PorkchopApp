package ph.gov.kabantayngbayan.porkchop.models;

import java.util.List;

public class Budget {

	private String year;
	private String owner_code;
	private String department_code;
	private String agency_type;
	private String owner_desc;
	private String department_desc;
	private List<Programs> programs;
	private String id;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getOwner_code() {
		return owner_code;
	}

	public void setOwner_code(String owner_code) {
		this.owner_code = owner_code;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getAgency_type() {
		return agency_type;
	}

	public void setAgency_type(String agency_type) {
		this.agency_type = agency_type;
	}

	public String getOwner_desc() {
		return owner_desc;
	}

	public void setOwner_desc(String owner_desc) {
		this.owner_desc = owner_desc;
	}

	public String getDepartment_desc() {
		return department_desc;
	}

	public void setDepartment_desc(String department_desc) {
		this.department_desc = department_desc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Programs> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Programs> programs) {
		this.programs = programs;
	}

}
