/**
 * 
 */
package ph.gov.kabantayngbayan.porkchop;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;

import ph.gov.kabantayngbayan.porkchop.models.PieChartModel;
import ph.gov.kabantayngbayan.porkchop.utils.GraphManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author Digify-Ray
 * 
 */
public class PagesFragment extends Fragment {
	
	private static final String BY_REGION = "By Region";

	private static final String BY_SECTOR = "By Sector";

	private static final String BY_RECIPIENT_UNIT = "By Recipient Unit";

	private static final String BY_EXPENSE_CLASS = "By Expense Class";

	private static final String TAG = PagesFragment.class.getSimpleName();

	public static final String KEY_EXPENSE_CLASS = "ph.kabantayngbayan.porkchop.BY_EXPENSE_CLASS";
	public static final String KEY_REGION = "ph.kabantayngbayan.porkchop.BY_REGION";
	public static final String KEY_RECIPIENT_UNIT = "ph.kabantayngbayan.porkchop.BY_RECIPIENT_UNIT";
	public static final String KEY_SECTOR = "ph.kabantayngbayan.porkchop.BY_SECTOR";

	private GraphicalView mChartViewExpenseClass;
	private GraphicalView mChartViewRecipientUnit;
	private GraphicalView mChartViewSector;
	private GraphicalView mChartViewRegion;
	
	private LinearLayout layoutPieExpenseClass;
	private LinearLayout layoutPieSaro;
	private LinearLayout layoutPieSector;
	private LinearLayout layoutBarRegion;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_pages, container, false);

		layoutPieExpenseClass = (LinearLayout) view.findViewById(R.id.pie_chart_expense_class);
		layoutPieSaro = (LinearLayout) view.findViewById(R.id.pie_chart_recipient_unit);
		layoutPieSector = (LinearLayout) view.findViewById(R.id.pie_chart_sector);
		layoutBarRegion = (LinearLayout) view.findViewById(R.id.pie_chart_region);

		Bundle bundle = getArguments();
		List<PieChartModel> pies  = new ArrayList<PieChartModel>();
		
		if( null != bundle ) {
			
			List<PieChartModel> expenseClass = (List<PieChartModel>) bundle.getSerializable(KEY_EXPENSE_CLASS);
			List<PieChartModel> recipientUnits = (List<PieChartModel>) bundle.getSerializable(KEY_RECIPIENT_UNIT);
			List<PieChartModel> sectors = (List<PieChartModel>) bundle.getSerializable(KEY_SECTOR);
			List<PieChartModel> regions = (List<PieChartModel>) bundle.getSerializable(KEY_REGION);
			
			
			PieChartModel p = new PieChartModel("NO DATA", 1);
			pies.add(p);
			
			mChartViewExpenseClass = new GraphManager().getPieChartView(getActivity(), 
					(expenseClass.size()>0) ? expenseClass : pies, BY_EXPENSE_CLASS, false, false, false, false, true);
			mChartViewRecipientUnit = new GraphManager().getPieChartView(getActivity(), 
					(recipientUnits.size()>0) ? recipientUnits : pies, BY_RECIPIENT_UNIT, false, false, false, false, true);
			mChartViewSector = new GraphManager().getPieChartView(getActivity(), 
					(sectors.size()>0) ? sectors : pies, BY_SECTOR, false, false, false, false, true);
			mChartViewRegion = new GraphManager().getPieChartView(getActivity(), 
					(regions.size()>0) ? regions : pies, BY_REGION, false, false, false, false, true);
		
			
		} else {
			PieChartModel p = new PieChartModel("NO DATA", -1);
			pies.add(p);
			
			mChartViewExpenseClass = new GraphManager().getPieChartView(getActivity(), pies, BY_EXPENSE_CLASS, false, false, false, false, true);
			mChartViewRecipientUnit = new GraphManager().getPieChartView(getActivity(), pies, BY_RECIPIENT_UNIT, false, false, false, false, true);
			mChartViewSector = new GraphManager().getPieChartView(getActivity(), pies, BY_SECTOR, false, false, false, false, true);
			mChartViewRegion = new GraphManager().getPieChartView(getActivity(), pies, BY_REGION, false, false, false, false, true);

		}
		
		mChartViewExpenseClass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), BY_EXPENSE_CLASS, Toast.LENGTH_LONG).show();
			}
		});
		
		mChartViewRecipientUnit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), BY_RECIPIENT_UNIT, Toast.LENGTH_LONG).show();
			}
		});

		mChartViewSector.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), BY_SECTOR, Toast.LENGTH_LONG).show();
			}
		});
		
		mChartViewRegion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), BY_REGION, Toast.LENGTH_LONG).show();
			}
		});

		
		layoutPieExpenseClass.addView(mChartViewExpenseClass, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layoutPieSaro.addView(mChartViewRecipientUnit, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layoutPieSector.addView(mChartViewSector, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layoutBarRegion.addView(mChartViewRegion, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		return view;
	}

}
