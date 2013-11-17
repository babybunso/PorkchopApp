package ph.gov.kabantayngbayan.porkchop;

import ph.gov.kabantayngbayan.porkchop.models.Saro;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SAOBDetailsActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saro_details);
		initialize();
	}
	
	private void initialize() {
		Saro saro = (Saro) getIntent().getSerializableExtra("saro");
		TextView txtSaro = (TextView) findViewById(R.id.txt_budget_amount);
		try {
			txtSaro.setText(saroDetailsBuilder(saro));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private String saroDetailsBuilder(Saro saro) {
		String n = System.getProperty("line.separator");
		String details = n+"Year: " +saro.getYear() + n
						+"Issue Date: " +saro.getIssue_date()+n
						+"Region: " + saro.getRegion()+n
						+"Fund Code:" + saro.getFund_code()+n
						+"Department Code: " + saro.getDepartment_code()+n
						+"Agency Code: " + saro.getAgency_code()+n
						+"SPF Code: " + saro.getSpf_code()+n
						+"Description: " + saro.getDescription()+n
						+"Purpose: " + saro.getPurpose()+n
						+"Program Description: " + saro.getProgram_description()+n
						+"Object Code: " + saro.getObject_code()+n
						+"Object Description: " + saro.getObject_description()+n
						+"Amount: PHP" + saro.getAmount()+n
						+"SARO No.: " + saro.getSaro_no()+n
						+"Barcode No.: " +saro.getBarcode_no()
						
						;
						
		return details;
	}
}
