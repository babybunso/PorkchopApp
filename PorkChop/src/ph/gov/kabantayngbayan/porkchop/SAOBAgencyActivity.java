
package ph.gov.kabantayngbayan.porkchop;

import java.util.ArrayList;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.db.BudgetAutoApproDataSource;
import ph.gov.kabantayngbayan.porkchop.db.DBHelper;
import ph.gov.kabantayngbayan.porkchop.models.BudgetAutoAppro;
import ph.gov.kabantayngbayan.porkchop.models.BudgetNewAppro;
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

public class SAOBAgencyActivity extends Activity{
	
	//SAOB AGENCY
	
	ImageView imgMap;
	GridView gridView;
	BudgetReleaseListAdapter saroAdapter;
	
	//TODO
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saob_agency);
		initialize();
	}
	
	
	
	private void initialize() {
		gridView = (GridView) findViewById(R.id.gridview);
		int region = getIntent().getIntExtra("region", R.drawable.region_05);
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
	private List<BudgetAutoAppro> getBudgetAutoAppro(String agencyName) {
		List<BudgetAutoAppro> appros;
		BudgetAutoApproDataSource autoAppro = new BudgetAutoApproDataSource(getApplicationContext());
		autoAppro.open();
		appros = autoAppro.getByParameter(DBHelper.COL_TBL_BUDGET_APPRO_DEPARTMENT_CODE + "='" + agencyName + "'");
		autoAppro.close();
		return appros;
	
	}
	

	//TODO transfer to SARODETAILS
/*	private List<BudgetNewAppro> getBudgetNewAppro(String agencyName) {
		List<BudgetNewAppro> appros;
		BudgetAutoApproDataSource autoAppro = new BudgetAutoApproDataSource(getApplicationContext());
		autoAppro.open();
		appros = autoAppro.getByParameter(DBHelper.COL_TBL_BUDGET_APPRO_DEPARTMENT_CODE + "='" + agencyName + "'");
		autoAppro.close();
		
		List<Programs> p = appros.get(0).getPrograms();
		return appros;
	
	}*/
	
	
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
				Intent i = new Intent(SAOBAgencyActivity.this, SAOBActivity.class);
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
