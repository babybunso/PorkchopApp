
package ph.gov.kabantayngbayan.porkchop;

import java.util.ArrayList;

import ph.gov.kabantayngbayan.porkchop.db.DBHelper;
import ph.gov.kabantayngbayan.porkchop.db.SaobDataSource;
import ph.gov.kabantayngbayan.porkchop.db.SaroDataSource;
import ph.gov.kabantayngbayan.porkchop.utils.AgencyItem;
import ph.gov.kabantayngbayan.porkchop.utils.AgencyLogoUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class BudgetReleaseActivity extends Activity{
	
	//SARO REGION
	
	ImageView imgMap;
	GridView gridView;
	BudgetReleaseListAdapter saroAdapter;
	
	//TODO
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saro_region);
		initialize();
	}
	
	
	
	private void initialize() {
		imgMap = (ImageView) findViewById(R.id.img_map);
		gridView = (GridView) findViewById(R.id.gridview);
		String region = getIntent().getStringExtra("region");
		int regionImage = 0;
		
		if (region.equals("1")) { 
			regionImage = R.drawable.region_01;
		} else if (region.equals("2")) { 
			regionImage = R.drawable.region_02;
		} else if (region.equals("3")) { 
			regionImage = R.drawable.region_03;
		} else if (region.equals("4")) { 
			regionImage = R.drawable.region_04;
		} else if (region.equals("5")) { 
			regionImage = R.drawable.region_05;
		} else if (region.equals("6")){ 
			regionImage = R.drawable.region_06;
		} else if (region.equals("7")) { 
			regionImage = R.drawable.region_07;
		} else if (region.equals("8")) { 
			regionImage = R.drawable.region_08;
		} else if (region.equals("9")) { 
			regionImage = R.drawable.region_09;
		} else if (region.equals("10")) { 
			regionImage = R.drawable.region_10;
		} else if (region.equals("11") ){ 
			regionImage = R.drawable.region_11;
		} else if (region.equals("12")) { 
			regionImage = R.drawable.region_12;
		} else if (region.equals("CAR")) { 
			regionImage = R.drawable.region_car;
		} else if (region.equals("Manila")) { 
			regionImage = R.drawable.region_ncr;
		}
		imgMap.setImageResource(regionImage);
		setList();
	}
	
	private void setList() {
		ArrayList<AgencyItem> items = getAgencyItems();
		Log.d("JOSH", String.valueOf(items.size()));
		gridView = ((GridView) findViewById(R.id.gridview));
		saroAdapter = new BudgetReleaseListAdapter(this, R.layout.item_saro_region, items);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridView.setAdapter(saroAdapter);
	}
	
	
	public ArrayList<AgencyItem> getAgencyItems() {
		ArrayList<AgencyItem> items = new ArrayList<AgencyItem>();
		for(int i = 0; i < 32 ; i++) {
			AgencyItem agency = new AgencyItem(AgencyLogoUtil.logoName[i],AgencyLogoUtil.logoId[i]);
			items.add(agency);	
		}
		
		return items;
	}

	//TODO transfer to SARODETAILS
	private ArrayList<AgencyItem> getSaroRegion() {
		
		SaroDataSource saroSource = new SaroDataSource(getApplicationContext());
		saroSource.open();
		saroSource.getByParameter(DBHelper.COL_TBL_SARO_REGION+"=''");
		saroSource.close();
		return null;
	
	}
	
	
	private class BudgetReleaseListAdapter extends ArrayAdapter<AgencyItem> implements OnItemClickListener, Filterable {
		private int layoutId;
	    ArrayList<AgencyItem> originalTransList = null;
	    ArrayList<AgencyItem> filteredTransList = null;
		
		public BudgetReleaseListAdapter(Context context, int layoutId, ArrayList<AgencyItem> items) {
            super(context, layoutId, items);
            this.layoutId = layoutId;
            this.originalTransList = new ArrayList<AgencyItem>(items);
            this.filteredTransList = new ArrayList<AgencyItem>(items);
            
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 ViewHolder itemView = null;
             String newline = System.getProperty("line.separator");
             if (convertView == null) {
                     convertView = getLayoutInflater().inflate(layoutId, null);
                     itemView = new ViewHolder();
                     
                     itemView.imgAgency = (ImageView) convertView.findViewById(R.id.img_item);

                     convertView.setTag(itemView);
                     
             } else itemView = (ViewHolder) convertView.getTag();
              
              AgencyItem agencyItem = getItem(position);
              itemView.imgAgency.setImageResource(agencyItem.getImage());
              itemView.imgAgency.setOnClickListener(itemView);
              itemView.agencyItem = agencyItem;
              return convertView;
		}
		
		
		private class ViewHolder implements OnClickListener {
             public ImageView imgAgency;
             public AgencyItem agencyItem;
			@Override
			public void onClick(View v) {
				Intent i = new Intent(BudgetReleaseActivity.this, BudgetAgencyActivity.class);
				i.putExtra("agencyImage", agencyItem.getImage());
				i.putExtra("agencyName", agencyItem.getName());
				startActivity(i);
				Toast.makeText(getApplicationContext(), agencyItem.getName(), Toast.LENGTH_LONG).show();
			}
		
		
		
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
		}
		
	}
	
}
