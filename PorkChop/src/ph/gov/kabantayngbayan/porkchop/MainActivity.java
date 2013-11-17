package ph.gov.kabantayngbayan.porkchop;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.db.DBHelper;
import ph.gov.kabantayngbayan.porkchop.db.SaroDataSource;
import ph.gov.kabantayngbayan.porkchop.db.TotalDataSource;
import ph.gov.kabantayngbayan.porkchop.models.Polygon;
import ph.gov.kabantayngbayan.porkchop.models.Region;
import ph.gov.kabantayngbayan.porkchop.models.Saro;
import ph.gov.kabantayngbayan.porkchop.models.Total;
import ph.gov.kabantayngbayan.porkchop.utils.ByCategoryDataUtil;
import ph.gov.kabantayngbayan.porkchop.utils.ColorUtil;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MainActivity extends FragmentActivity implements OnClickListener {

	LinearLayout layMenuItems;

	/**
	 * Constants
	 */
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final LatLng CENTER_PHILIPPINES = new LatLng(11.872800,
			122.861300);
	private static final int ZOOM_LEVEL_PLACE = 5;
	private ByCategoryDataUtil byCategory;

	/**
	 * Variables
	 */
	public ArrayList<Region> mRegions = new ArrayList<Region>();
	public PagerAdapter mPagerAdapter;

	/**
	 * Views
	 */
	private GoogleMap mMap;
	private ViewPager mPager;
	private List<Total> totals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeViews();
		byCategory = new ByCategoryDataUtil(this);

		setUpTotalsBudget();
		setUpMapIfNeeded();
		setUpViewPager();
	}

	private void setUpTotalsBudget() {
		TotalDataSource totalDataSource = new TotalDataSource(MainActivity.this);
		totalDataSource.open();
		totals = totalDataSource.getAll();
		totalDataSource.close();
	}

	private void setUpViewPager() {
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
	}

	private void initializeViews() {
		layMenuItems = (LinearLayout) findViewById(R.id.lay_menu_items);
		findViewById(R.id.img_menu).setOnClickListener(this);
		findViewById(R.id.img_diy).setOnClickListener(this);
		findViewById(R.id.img_saob).setOnClickListener(this);
		findViewById(R.id.img_budgetrelease).setOnClickListener(this);
		findViewById(R.id.img_budget101).setOnClickListener(this);
		findViewById(R.id.img_about).setOnClickListener(this);
	}

	/**
	 * Initialize map
	 */
	private void setUpMapIfNeeded() {
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			if (mMap != null) {
				// Center map to the center of the Philippines
				centerMap(CENTER_PHILIPPINES, ZOOM_LEVEL_PLACE);

				// enable my location to get current location
				// mMap.setMyLocationEnabled(true);
			}
		}

		mMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng arg0) {
				if (pointInRegion(arg0) != null) {
					Intent i = new Intent(MainActivity.this,
							BudgetReleaseActivity.class);
					i.putExtra("region", pointInRegion(arg0));
					startActivity(i);
				}
			}
		});

		parseJson();
		plotPolygons();
	}

	/**
	 * Center the map into a specific place and then set the zoom to zoomLevel
	 * 
	 * @param place
	 *            center of the map
	 * @param zoomLevel
	 */
	private void centerMap(LatLng place, int zoomLevel) {
		if (place != null) {
			LatLng currentLatLng = place;

			CameraPosition cp = new CameraPosition.Builder()
					.target(currentLatLng).zoom(zoomLevel).build();
			mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
		}
	}

	/**
	 * Plot the Polygon layers over Google Map
	 */
	private void plotPolygons() {

		int index = 0;
		for (Region reg : mRegions) {
			int color = ColorUtil.COLORS[index];
			for (Polygon p : reg.getPolygons()) {
				mMap.addPolygon(new PolygonOptions()
						.addAll(Region.getCoordinates(p)).fillColor(color)
						.strokeWidth(0.1f));
			}
			if (index < ColorUtil.COLORS.length - 1) {
				index++;
			} else {
				index = 0;
			}

		}

	}

	/**
	 * Parse the regional.json file from the assets folder
	 */
	private void parseJson() {
		// Get from assets
		AssetManager assetManager = getAssets();
		Gson gson = new Gson();
		try {
			InputStream input = assetManager.open("regional.json");
			int size = input.available();
			byte[] buffer = new byte[size];
			input.read(buffer);
			input.close();

			String text = new String(buffer);
			JsonParser parser = new JsonParser();
			JsonArray jArray = parser.parse(text).getAsJsonArray();

			for (JsonElement element : jArray) {
				Region reg = gson.fromJson(element, Region.class);
				mRegions.add(reg);
			}

			Log.d(TAG, mRegions.toString());

		} catch (IOException e) {
			Log.d(TAG, "Problems in getting files from Assets");
		}
	}

	/**
	 * Gets the region name associated with the point specified on the map
	 * 
	 * @param point
	 * @return
	 */
	private String pointInRegion(LatLng point) {
		for (Region reg : mRegions) {
			for (Polygon p : reg.getPolygons()) {
				if (isPointInPolygon(point, Region.getCoordinates(p))) {
					return reg.getRegion();
				}
			}
		}

		return null;
	}

	/**
	 * Checks if the point is within or inside the polygon given the vertices
	 * 
	 * @param tap
	 * @param vertices
	 * @return
	 */
	private boolean isPointInPolygon(LatLng tap, ArrayList<LatLng> vertices) {
		int intersectCount = 0;
		for (int j = 0; j < vertices.size() - 1; j++) {
			if (rayCastIntersect(tap, vertices.get(j), vertices.get(j + 1))) {
				intersectCount++;
			}
		}

		return ((intersectCount % 2) == 1); // odd = inside, even = outside;
	}

	/**
	 * Checks if the casted ray intersects any ray, use for point-inside-polygon
	 * checking
	 * 
	 * @param tap
	 * @param vertA
	 * @param vertB
	 * @return
	 */
	private boolean rayCastIntersect(LatLng tap, LatLng vertA, LatLng vertB) {
		double aY = vertA.latitude;
		double bY = vertB.latitude;
		double aX = vertA.longitude;
		double bX = vertB.longitude;
		double pY = tap.latitude;
		double pX = tap.longitude;

		if ((aY > pY && bY > pY) || (aY < pY && bY < pY)
				|| (aX < pX && bX < pX)) {
			return false; // a and b can't both be above or below pt.y, and a or
							// b must be east of pt.x
		}

		double m = (aY - bY) / (aX - bX); // Rise over run
		double bee = (-aX) * m + aY; // y = mx + b
		double x = (pY - bee) / m; // algebra is neat!

		return x > pX;
	}

	/**
	 * Pager Adapter for ViewPager SARO Details
	 * 
	 * @author Digify-Ray
	 * 
	 */
	public class PagerAdapter extends FragmentPagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// TODO: Create initialization for PagesFragment, supply a year to
			// the fragment maybe?
			PagesFragment pf = new PagesFragment();
			Bundle bundle = new Bundle();

			String year = totals.get(position).getYear();

			bundle.putSerializable(PagesFragment.KEY_EXPENSE_CLASS,
					(Serializable) byCategory.getExpenseClass(year));
			bundle.putSerializable(PagesFragment.KEY_REGION,
					(Serializable) byCategory.getRegions(year));
			bundle.putSerializable(PagesFragment.KEY_RECIPIENT_UNIT,
					(Serializable) byCategory.getRecipientUnits(year));
			bundle.putSerializable(PagesFragment.KEY_SECTOR,
					(Serializable) byCategory.getSectors(year));

			pf.setArguments(bundle);

			return pf;
		}

		@Override
		public int getCount() {
			// TODO: Change this to number of years to be presented
			return totals.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// int year = 2013;
			// year = year - position;
			// return Integer.toString(year);
			return totals.get(position).getYear();
		}
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setMessage("Exit PorkChop App?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int id) {
								;
								System.exit(0);
							}

						}).setNegativeButton("No", null).show();
	}

	@Override
	public void onClick(View v) {

		Intent i;
		switch (v.getId()) {

		case R.id.img_menu:
			layMenuItems.setVisibility(layMenuItems.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
			break;

		case R.id.img_diy:
			Toast.makeText(getApplicationContext(), "DIY", Toast.LENGTH_LONG).show();
			Intent i = new Intent(this, BudgetReleaseActivity.class);
=======
			Toast.makeText(getApplicationContext(), "DIY", Toast.LENGTH_LONG)
					.show();
			i = new Intent(this, DIYActivity.class);
>>>>>>> branch_mon
			startActivity(i);
			break;

		case R.id.img_saob:
			Toast.makeText(getApplicationContext(), "SAOB", Toast.LENGTH_LONG).show();
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, 0);
			break;

		case R.id.img_budgetrelease:
			Toast.makeText(getApplicationContext(), "BUDGETRELEASE", Toast.LENGTH_LONG).show();
=======
			Toast.makeText(getApplicationContext(), "SAOB", Toast.LENGTH_LONG)
					.show();
			i = new Intent("com.google.zxing.client.android.SCAN");
			i.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(i, 0);
			break;

		case R.id.img_budgetrelease:
			Toast.makeText(getApplicationContext(), "BUDGETRELEASE",
					Toast.LENGTH_LONG).show();

>>>>>>> branch_mon
			break;
		case R.id.img_budget101:
			Toast.makeText(getApplicationContext(), "101", Toast.LENGTH_LONG)
					.show();
			startActivity(new Intent(this, TaxDistActivity.class));
			break;

		case R.id.img_about:
			Toast.makeText(getApplicationContext(), "ABOUT", Toast.LENGTH_LONG)
					.show();
			break;

		}

	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

				ArrayList<Saro> items = new ArrayList<Saro>();
				SaroDataSource saroSource = new SaroDataSource(
						getApplicationContext());
				saroSource.open();
				items = (ArrayList<Saro>) saroSource
						.getByParameter(DBHelper.COL_TBL_SARO_BARCODE_NO + "='"
								+ contents + "'");
				saroSource.close();

				Intent i = new Intent();
				Bundle b = new Bundle();
				if (items.size() == 1) {
					Saro saro = items.get(0);
					b.putSerializable("saro", saro);
					i.putExtras(b);
					i.setClass(MainActivity.this,
							BudgetReleaseDetailsActivity.class);
					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(),
							"No SARO Found for Barcode " + contents,
							Toast.LENGTH_LONG).show();
				}

				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

}
