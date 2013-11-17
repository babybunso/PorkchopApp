/**
 * 
 */
package ph.gov.kabantayngbayan.porkchop;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

import ph.gov.kabantayngbayan.porkchop.models.PieChartModel;
import ph.gov.kabantayngbayan.porkchop.utils.ByCategoryDataUtil;
import ph.gov.kabantayngbayan.porkchop.utils.GraphManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author Digify-Ray
 * 
 */
public class DIYActivity extends Activity {

	private static final String ACTUAL_BUDGET = "Actual Budget";
	private static final String YOUR_BUDGET = "Your Budget";
	private ByCategoryDataUtil byCategory;
	private LinearLayout layoutChartActualBudget;
	private LinearLayout layoutChartYourBudget;

	private List<PieChartModel> sectors;
	private List<PieChartModel> sectorsYours;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diy);

		layoutChartActualBudget = (LinearLayout) findViewById(R.id.chart_actual_budget);
		layoutChartYourBudget = (LinearLayout) findViewById(R.id.chart_your_budget);

		byCategory = new ByCategoryDataUtil(this);
		sectors = (List<PieChartModel>) byCategory.getSectors("2013");
		sectorsYours = new ArrayList<PieChartModel>();

		creatActualBudgetChart();
	}

	private void creatActualBudgetChart() {

		final GraphManager g = new GraphManager();
		setGRenderer(g);

		final GraphicalView gView = g.getPieChartView(this, sectors,
				ACTUAL_BUDGET, true, true, false, true, false);

		gView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// animate();
				SeriesSelection seriesSelection = gView
						.getCurrentSeriesAndPoint();
				if (seriesSelection == null) {
					Toast.makeText(DIYActivity.this,
							"No chart element selected", Toast.LENGTH_SHORT)
							.show();
				} else {
					for (int i = 0; i < g.mSeries.getItemCount(); i++) {
						g.mRenderer.getSeriesRendererAt(i).setHighlighted(
								i == seriesSelection.getPointIndex());
					}
					gView.repaint();
					// pass sectors.get(seriesSelection.getPointIndex())
					Toast.makeText(
							DIYActivity.this,
							"Chart data point index "
									+ seriesSelection.getPointIndex()
									+ " selected" + " point value="
									+ seriesSelection.getValue(),
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(DIYActivity.this,
							DialogSeekbar.class);
					PieChartModel model = sectors.get(seriesSelection
							.getPointIndex());
					intent.putExtra("percent", getTotal());
					intent.putExtra("name", model.getCategory());
					startActivityForResult(intent, 15);

				}
			}
		});

		layoutChartActualBudget.addView(gView, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		creatYourBudgetChart(sectorsYours);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {

			PieChartModel model = new PieChartModel(
					data.getStringExtra("name"), data.getDoubleExtra("percent",
							0));
			if (checkIfExisting(data.getStringExtra("name")) >= 0) {
				sectorsYours.remove(0);
			}
			sectorsYours.add(model);

			creatYourBudgetChart(sectorsYours);
		}
	}

	private int checkIfExisting(String name) {
		for (int i = 0; i < sectorsYours.size(); i++) {
			if (sectorsYours.get(i).getCategory().equals(name))
				return i;
		}

		return -1;
	}
	
	private double getTotal() { 
		double total = 0.0;
		for (PieChartModel pie : sectorsYours) { 
			total += pie.getValue();
		}
		
		return total;
	}

	private void creatYourBudgetChart(List<PieChartModel> s) {

		GraphManager g = new GraphManager();
		setGRenderer(g);

		GraphicalView gView = g.getPieChartView(this, s, YOUR_BUDGET, true,
				true, true, true, false);
		try {
			layoutChartYourBudget.removeViewAt(0);
			gView.repaint();
		} catch (Exception e) {

		}
		layoutChartYourBudget.addView(gView, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

	}

	private void setGRenderer(GraphManager g) {
		g.mRenderer.setChartTitleTextSize(40);
		g.mRenderer.setLabelsTextSize(18);
	}

}
