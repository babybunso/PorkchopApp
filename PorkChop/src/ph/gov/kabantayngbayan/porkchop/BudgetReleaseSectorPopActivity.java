package ph.gov.kabantayngbayan.porkchop;

import ph.gov.kabantayngbayan.porkchop.models.Sector.Particular;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BudgetReleaseSectorPopActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saro_sector_pop);
		initialize();
	}
	
	private void initialize() {
		//Particular saro = (Particular) getIntent().getSerializableExtra("saro"));
		TextView txtSaro = (TextView) findViewById(R.id.txt_budget_amount);
		findViewById(R.id.img_exit).setOnClickListener(this);
		try {
			txtSaro.setText(saroDetailsBuilder());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private String saroDetailsBuilder() {
		String name = getIntent().getStringExtra("name");
		String year = getIntent().getStringExtra("year");
		double levels = getIntent().getDoubleExtra("levels", 0);
		double share = getIntent().getDoubleExtra("share", 0);
		String n = System.getProperty("line.separator");
		String details = n +"Year:	"+year+n+n
						    +"Name: 	" + name+n
						    +"Levels: 	" + levels+n
						    +"Percent Share:	" +share+n
						;
						
		return details;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.img_exit) {
			finish();
		}
		
	}
}
