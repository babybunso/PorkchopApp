
package ph.gov.kabantayngbayan.porkchop;

import java.io.Serializable;
import java.util.ArrayList;
import ph.gov.kabantayngbayan.porkchop.models.Sector.Particular;
import ph.gov.kabantayngbayan.porkchop.models.Sector;
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
import android.widget.TextView;
import android.widget.Toast;

public class BudgetReleaseSectorActivity extends Activity{
	
	//SARO SECTOR
	ImageView imgMap;
	GridView gridView;
	BudgetReleaseListAdapter saroAdapter;
	
	//TODO
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saro_sector);
		initialize();
	}
	
	
	
	private void initialize() {
		gridView = (GridView) findViewById(R.id.gridview);
		setList();
	}
	
	private void setList() {
		Sector sector = new Sector();
		ArrayList<Particular> items = (ArrayList<Particular>) sector.getParticulars() ;
		Log.d("JOSH", String.valueOf(items.size()));
		gridView = ((GridView) findViewById(R.id.gridview));
		saroAdapter = new BudgetReleaseListAdapter(this, R.layout.item_saro_sector, items);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridView.setAdapter(saroAdapter);
	}
	
	
	



	
	private class BudgetReleaseListAdapter extends ArrayAdapter<Particular> implements OnItemClickListener, Filterable {
		private int layoutId;
	    ArrayList<Particular> originalTransList = null;
	    ArrayList<Particular> filteredTransList = null;
		
		public BudgetReleaseListAdapter(Context context, int layoutId, ArrayList<Particular> items) {
            super(context, layoutId, items);
            this.layoutId = layoutId;
            this.originalTransList = new ArrayList<Particular>(items);
            this.filteredTransList = new ArrayList<Particular>(items);
            
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 ViewHolder itemView = null;
             String newline = System.getProperty("line.separator");
             if (convertView == null) {
                     convertView = getLayoutInflater().inflate(layoutId, null);
                     itemView = new ViewHolder();
                     
                     itemView.imgSector = (ImageView) convertView.findViewById(R.id.img_item);
                     itemView.sectorName = (TextView) convertView.findViewById(R.id.txt_sectorname);
                     convertView.setTag(itemView);
                     
             } else itemView = (ViewHolder) convertView.getTag();
              
              Particular particular = getItem(position);
              itemView.imgSector.setImageResource(particular.getImageId());
              itemView.imgSector.setOnClickListener(itemView);
              itemView.sectorName.setText(particular.getName());
              itemView.particular = particular;
              return convertView;
		}
		
		
		private class ViewHolder implements OnClickListener {
             public ImageView imgSector;
             public TextView sectorName;
             public Particular particular;
			@Override
			public void onClick(View v) {

				Intent i = new Intent();
				Bundle b = new Bundle();	
				//b.putSerializable("saro", (Serializable)particular);
				i.putExtra("name", particular.getName());
				i.putExtra("year", particular.getYear());
				i.putExtra("levels", particular.getLevels());
				i.putExtra("share", particular.getPercentShare());
				i.setClass(BudgetReleaseSectorActivity.this, BudgetReleaseSectorPopActivity.class);
				startActivity(i);
				
			}
		
		
		
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
		}
		
	}
	
}
