package ph.gov.kabantayngbayan.porkchop.db;

import java.util.ArrayList;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.models.Particular;

public class SectorDatasource {

	private List<Particular> particulars = new ArrayList<Particular>();

	public List<Particular> getParticulars() {
		generateData();
		return particulars;
	}

	private void generateData() { /* Generate Static Data */
		
		particulars = new ArrayList<Particular>();

		/* YEAR 2013 */
		Particular p = new Particular();
		p.setYear("2013");
		p.setName("Social Services");
		p.setLevels(698.8);
		p.setPercentShare(34.8);
		particulars.add(p);

		p = new Particular();
		p.setYear("2013");
		p.setName("Economic Services");
		p.setLevels(511.1);
		p.setPercentShare(25.5);
		particulars.add(p);

		p = new Particular();
		p.setYear("2013");
		p.setName("General Public Services");
		p.setLevels(346.1);
		p.setPercentShare(17.3);
		particulars.add(p);

		p = new Particular();
		p.setYear("2013");
		p.setName("Debt Services");
		p.setLevels(333.9);
		p.setPercentShare(16.6);
		particulars.add(p);

		p = new Particular();
		p.setYear("2013");
		p.setName("Defense");
		p.setLevels(89.7);
		p.setPercentShare(4.5);
		particulars.add(p);

		p = new Particular();
		p.setYear("2013");
		p.setName("Net Lending");
		p.setLevels(26.5);
		p.setPercentShare(1.3);
		particulars.add(p);

		/* YEAR 2012 */
		p = new Particular();
		p.setYear("2012");
		p.setName("Social Services");
		p.setLevels(613.4);
		p.setPercentShare(33.8);
		particulars.add(p);

		p = new Particular();
		p.setYear("2012");
		p.setName("Economic Services");
		p.setLevels(439);
		p.setPercentShare(24.2);
		particulars.add(p);

		p = new Particular();
		p.setYear("2012");
		p.setName("General Public Services");
		p.setLevels(320.3);
		p.setPercentShare(17.6);
		particulars.add(p);

		p = new Particular();
		p.setYear("2012");
		p.setName("Debt Services");
		p.setLevels(333.1);
		p.setPercentShare(18.3);
		particulars.add(p);

		p = new Particular();
		p.setYear("2012");
		p.setName("Defense");
		p.setLevels(87.2);
		p.setPercentShare(4.8);
		particulars.add(p);

		p = new Particular();
		p.setYear("2012");
		p.setName("Net Lending");
		p.setLevels(23);
		p.setPercentShare(1.3);
		particulars.add(p);
	}

}
