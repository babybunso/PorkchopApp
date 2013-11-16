package ph.gov.kabantayngbayan.porkchop.utils;

public class AgencyItem {
	private String agencyName;
	private int agencyImage;
	
	public AgencyItem (String agency_name, int agency_image) {
		this.agencyName = agency_name;
		this.agencyImage = agency_image;
		
	}
	
	public int getImage() {
		return agencyImage;
	}
	
	public String getName() {
		return agencyName;
	}
}
