/**
 * 
 */
package ph.gov.kabantayngbayan.porkchop;

import java.text.DecimalFormat;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.db.SectorDatasource;
import ph.gov.kabantayngbayan.porkchop.models.Particular;
import ph.gov.kabantayngbayan.porkchop.utils.VerticalSeekBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author Digify-Ray
 * 
 */
public class TaxDistActivity extends Activity {

	public VerticalSeekBar mSeekBar;
	public TextView mTvAmount;
	public TextView mTvDetails;
	public Button mBtnTaxCalc;
	public Spinner mSpinner;

	private int mTaxAmount = 0;

	private int mSelectedCategory = 0;

	private String kTaxPHP = "PHP %d";

	private String[] mCategories = { "Expense Class", "Specific Agency",
			"Sector" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taxdist);

		mSeekBar = (VerticalSeekBar) findViewById(R.id.seekBar);
		mTvAmount = (TextView) findViewById(R.id.txt_progcode);
		mTvDetails = (TextView) findViewById(R.id.tv_details);
		mSpinner = (Spinner) findViewById(R.id.sp_category);
		mBtnTaxCalc = (Button) findViewById(R.id.btn_tax_calculator);

		// Initialize contents
		mSeekBar.setMax(50000);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mCategories);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(adapter);

		setTax();

		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mTaxAmount = progress;
				setTax();
			}
		});

		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				mSelectedCategory = position;
				setTax();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void setTax() {
		mSeekBar.setProgress(mTaxAmount);
		mTvAmount.setText(String.format(kTaxPHP, mTaxAmount));
		switch (mSelectedCategory) {
		case 0:
			mTvDetails.setText(getExpenseString(mTaxAmount));
			break;
		case 1:
			mTvDetails.setText(getAgencyString(mTaxAmount));
			break;
		case 2:
			mTvDetails.setText(getSectorString(mTaxAmount));
			break;
		default:
			break;
		}

	}

	private String getSectorString(int amount) {
		SectorDatasource data = new SectorDatasource();
		List<Particular> particulars = data.getParticulars();

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("TAX BY SECTOR \n\n");

		for (Particular particular : particulars) {
			sBuilder.append(particular.getName());
			sBuilder.append(" (");
			sBuilder.append(particular.getPercentShare());
			sBuilder.append("% ): ");
			DecimalFormat format = new DecimalFormat("00.00");
			double total = amount * particular.getPercentShare() / 100;
			sBuilder.append(format.format(total));
			sBuilder.append("\n");
		}

		return sBuilder.toString();
	}

	private String getExpenseString(int amount) {
		StringBuilder sBuilder = new StringBuilder();

		return sBuilder.toString();
	}

	private String getAgencyString(int amount) {
		StringBuilder sBuilder = new StringBuilder();

		return sBuilder.toString();
	}

}
