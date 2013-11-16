package ph.gov.kabantayngbayan.porkchop;

import ph.gov.kabantayngbayan.porkchop.utils.RestManager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	private static final String TAG = SplashActivity.class.getSimpleName();
	private ImageView imageViewLoading;
	RestManager restManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		restManager = RestManager.getInstance(SplashActivity.this);
		imageViewLoading = (ImageView) findViewById(R.id.imageViewLoading);
		
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		new AsyncLocalJsonLoader().execute();
	}

	@Override
	public void onBackPressed() {
		return;
	}

	private void startMainAcivity() {
		
		finish();
		Intent i = new Intent(SplashActivity.this, MainActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		
	}

	private class AsyncLocalJsonLoader extends AsyncTask<Void, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			try {
				AnimationDrawable animDrawable = (AnimationDrawable) imageViewLoading.getBackground();
				animDrawable.start();
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				Log.i(TAG, "LOADING_IMAGE is not initialized");
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			restManager.initData();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			startMainAcivity();
		}

	}

}
