
package ph.gov.kabantayngbayan.porkchop;

import java.util.ArrayList;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.db.DBHelper;
import ph.gov.kabantayngbayan.porkchop.db.SaroDataSource;
import ph.gov.kabantayngbayan.porkchop.models.Saro;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SAOBActivity extends Activity{
	
	//SARO BY AGENCY
	
	ImageView imgMap;
	ListView gridView;
	BudgetReleaseListAdapter saroAdapter;
	
	//TODO
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saob);
		initialize();
	}
	
	
	
	private void initialize() {
		imgMap = (ImageView) findViewById(R.id.img_menu);
		gridView = (ListView) findViewById(R.id.listView1);
		int agency = getIntent().getIntExtra("agencyImage", R.drawable.agency_armm);
		String agencyName = getIntent().getStringExtra("agencyName");
		imgMap.setImageResource(agency);
		setList();
	}
	
	private void setList() {
		ArrayList<Saro> items = (ArrayList<Saro>) getSaroRegion();
		Log.d("JOSH", String.valueOf(items.size()));
		gridView = ((ListView) findViewById(R.id.listView1));
		saroAdapter = new BudgetReleaseListAdapter(this, R.layout.item_saob, items);
		gridView.setAdapter(saroAdapter);
	}
	
	


	//TODO transfer to SARODETAILS
	private List<Saro> getSaroRegion() {
		ArrayList<Saro> items = new ArrayList<Saro>();
		items.add(new Saro());
		items.add(new Saro());
		items.add(new Saro());
		return items;
	
	}
	
	private class BudgetReleaseListAdapter extends ArrayAdapter<Saro> implements OnItemClickListener, Filterable {
		private int layoutId;
	    ArrayList<Saro> originalTransList = null;
	    ArrayList<Saro> filteredTransList = null;
		
		public BudgetReleaseListAdapter(Context context, int layoutId, ArrayList<Saro> items) {
            super(context, layoutId, items);
            this.layoutId = layoutId;
            this.originalTransList = new ArrayList<Saro>(items);
            this.filteredTransList = new ArrayList<Saro>(items);
            
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 ViewHolder itemView = null;
             String newline = System.getProperty("line.separator");
             if (convertView == null) {
                     convertView = getLayoutInflater().inflate(layoutId, null);
                     itemView = new ViewHolder();
                     itemView.layClick = (LinearLayout) convertView.findViewById(R.id.lay_regionmap);
                     itemView.programCode = (TextView) convertView.findViewById(R.id.txt_saobitem);

                     convertView.setTag(itemView);
                     
             } else itemView = (ViewHolder) convertView.getTag();
              
              Saro saro = getItem(position);
              itemView.layClick.setOnClickListener(itemView);
              try {
            	 if(position == 0) itemView.programCode.setText("Capital Outlays (CO)");
            	 if(position == 1) itemView.programCode.setText("Maintenance & Other Operating Expenditures (MOOE)");
            	 if(position == 2) itemView.programCode.setText("Personal Services (PS)");
                  itemView.saro = saro;
			} catch (Exception e) {
				// TODO: handle exception
			}
              
              return convertView;
		}
		
		
		private class ViewHolder implements OnClickListener {
             public TextView programCode;
             public LinearLayout layClick;
             public Saro saro;
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				Bundle b = new Bundle();	
				b.putSerializable("saro", saro);
				i.putExtras(b);
				i.setClass(SAOBActivity.this, BudgetReleaseDetailsActivity.class);
				startActivity(i);
				
			}
		
		
		
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
		}
		
	}
	
}
