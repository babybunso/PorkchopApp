/**
 * 
 */
package ph.gov.kabantayngbayan.porkchop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * @author Digify-Ray
 *
 */
public class DialogSeekbar extends Activity {
	
	SeekBar mSeekBar; 
	TextView mTextView, mSector; 
	Button mBtnOk;
	
	String name; 
	double percent;
	int progress = 0; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_seek);
		
		Intent i = getIntent(); 
		
		mSeekBar = (SeekBar) findViewById(R.id.seekbar); 
		mTextView = (TextView) findViewById(R.id.tv_amount);
		mSector = (TextView) findViewById(R.id.tv_sector_percent);
		mBtnOk = (Button) findViewById(R.id.btn_ok);
		
		mSeekBar.setMax(100 - (int)i.getDoubleExtra("percent", 0)); 
		mSeekBar.setProgress(0);
		mTextView.setText("0");
		name = i.getStringExtra("name");; 
		percent = i.getDoubleExtra("percent", 0);
		mSector.setText(name);
		
		mBtnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent data = new Intent(); 
				data.putExtra("name", name);
				data.putExtra("percent", (double)progress);
				setResult(RESULT_OK, data);
				finish();
			}
		});
		
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
			public void onProgressChanged(SeekBar seekBar, int progress1,
					boolean fromUser) {
				progress = progress1; 
				mTextView.setText(Integer.toString(progress));
				
			}
		});
		
	}

}
