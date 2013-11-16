package ph.gov.kabantayngbayan.porkchop;

import java.util.ArrayList;

import ph.gov.kabantayngbayan.porkchop.models.Budget;
import ph.gov.kabantayngbayan.porkchop.models.Saob;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

public class ExpendituresActivity extends Activity{
	//TODO
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saob);
	}

	
	private class ExpenditureListAdapter extends ArrayAdapter<Saob> implements OnItemClickListener, Filterable {
		private int layoutId;
	    ArrayList<Saob> originalTransList = null;
	    ArrayList<Saob> filteredTransList = null;
		
		public ExpenditureListAdapter(Context context, int layoutId, ArrayList<Saob> items) {
            super(context, layoutId, items);
            this.layoutId = layoutId;
            this.originalTransList = new ArrayList<Saob>(items);
            this.filteredTransList = new ArrayList<Saob>(items);
            
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 ViewHolder itemView = null;
             String newline = System.getProperty("line.separator");
             if (convertView == null) {
                     convertView = getLayoutInflater().inflate(layoutId, null);
                     itemView = new ViewHolder();
                     
                    // itemView.amount = (TextView) convertView.findViewById(R.id.amt);
                   //  itemView.date =  (TextView) convertView.findViewById(R.id.date);
                    // itemView.description =  (TextView) convertView.findViewById(R.id.desc);
                    // itemView.cashValue  = (TextView) convertView.findViewById(R.id.cash_value);
                     
                     convertView.setTag(itemView);
                     
             } else itemView = (ViewHolder) convertView.getTag();
             
            
             //Transaction trans = getItem(position);

            // itemView.amount.setTextColor(Color.parseColor(trans.isCashIn() ? "#00ff00" : "#ff0000"));

            // itemView.amount.setText("1000");
            // itemView.cashValue.setText("10000");
             //itemView.date.setText("1000");
             //itemView.description.setText("1000");

     		
             return convertView;
		}
		
		
		private class ViewHolder {
             public TextView date, amount, cashValue, description;
		}
		
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
		}
		
	}
	
}
