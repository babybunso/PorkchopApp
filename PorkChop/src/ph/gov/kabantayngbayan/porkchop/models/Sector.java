package ph.gov.kabantayngbayan.porkchop.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import ph.gov.kabantayngbayan.porkchop.R;

public class Sector {

	private List<Particular> particulars;

	public List<Particular> getParticulars() {
		generateData();
		return particulars;
	}

	private void generateData() { /* Generate Static Data */
		particulars = new ArrayList<Sector.Particular>();

		/* YEAR 2013 */
		Particular p = new Particular();
		p.setYear("2013");
		p.setName("Social Services");
		p.setLevels(698.8);
		p.setPercentShare(34.8);
		p.setImageId(R.drawable.sel_social_services);
		particulars.add(p);
		
		p = new Particular();
		p.setYear("2013");
		p.setName("Economic Services");
		p.setLevels(511.1);
		p.setPercentShare(25.5);
		p.setImageId(R.drawable.sel_economic_services);
		particulars.add(p);
		
		p = new Particular();
		p.setYear("2013");
		p.setName("General Public Services");
		p.setLevels(346.1);
		p.setPercentShare(17.3);
		p.setImageId(R.drawable.sel_general_public);
		particulars.add(p);
		
		p = new Particular();
		p.setYear("2013");
		p.setName("Debt Services");
		p.setLevels(333.9);
		p.setPercentShare(16.6);
		p.setImageId(R.drawable.sel_debt_burden);
		particulars.add(p);

		p = new Particular();
		p.setYear("2013");
		p.setName("Defense");
		p.setLevels(89.7);
		p.setPercentShare(4.5);
		p.setImageId(R.drawable.sel_defense);
		particulars.add(p);
		
		p = new Particular();
		p.setYear("2013");
		p.setName("Net Lending");
		p.setLevels(26.5);
		p.setPercentShare(1.3);
		p.setImageId(R.drawable.sel_debt_burden);
		particulars.add(p);
		
		/* YEAR 2012 */
		p = new Particular();
		p.setYear("2012");
		p.setName("Social Services");
		p.setLevels(613.4);
		p.setPercentShare(33.8);
		p.setImageId(R.drawable.sel_social_services);
		particulars.add(p);
		
		p = new Particular();
		p.setYear("2012");
		p.setName("Economic Services");
		p.setLevels(439);
		p.setPercentShare(24.2);
		p.setImageId(R.drawable.sel_economic_services);
		particulars.add(p);
		
		p = new Particular();
		p.setYear("2012");
		p.setName("General Public Services");
		p.setLevels(320.3);
		p.setPercentShare(17.6);
		p.setImageId(R.drawable.sel_general_public);
		particulars.add(p);
		
		p = new Particular();
		p.setYear("2012");
		p.setName("Debt Services");
		p.setLevels(333.1);
		p.setPercentShare(18.3);
		p.setImageId(R.drawable.sel_debt_burden);
		particulars.add(p);

		p = new Particular();
		p.setYear("2012");
		p.setName("Defense");
		p.setLevels(87.2);
		p.setPercentShare(4.8);
		p.setImageId(R.drawable.sel_defense);
		particulars.add(p);
		
		p = new Particular();
		p.setYear("2012");
		p.setName("Net Lending");
		p.setLevels(23);
		p.setPercentShare(1.3);
		p.setImageId(R.drawable.sel_debt_burden);
		particulars.add(p);
	}

	public class Particular implements Serializable{

	
		private String year;
		private String name;
		private double levels;			// IN BILIION
		private double percentShare;	// IN PERCENTAGE
		private int imageId;

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
		
		public void setImageId(int id) {
			this.imageId = id;
		}
		
		public int getImageId() {
			return imageId;
		}

	}

}
