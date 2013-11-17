/**
 * 
 */
package ph.gov.kabantayngbayan.porkchop;

import java.util.List;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

import ph.gov.kabantayngbayan.porkchop.models.PieChartModel;
import ph.gov.kabantayngbayan.porkchop.utils.ByCategoryDataUtil;
import ph.gov.kabantayngbayan.porkchop.utils.GraphManager;
import android.app.Activity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diy);

		layoutChartActualBudget = (LinearLayout) findViewById(R.id.chart_actual_budget);
		layoutChartYourBudget = (LinearLayout) findViewById(R.id.chart_your_budget);

		byCategory = new ByCategoryDataUtil(this);
		sectors = (List<PieChartModel>) byCategory.getSectors("2013");

		creatActualBudgetChart();
	}

	private void creatActualBudgetChart() {
		
		final GraphManager g = new GraphManager();
		setGRenderer(g);

		final GraphicalView gView = g.getPieChartView(this, sectors, ACTUAL_BUDGET,
				true, true, false, true, false);
		
		gView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// animate();
				SeriesSelection seriesSelection = gView.getCurrentSeriesAndPoint();
				if (seriesSelection == null) {
					Toast.makeText(DIYActivity.this, "No chart element selected", Toast.LENGTH_SHORT).show();
				} else {
					 for (int i = 0; i < g.mSeries.getItemCount(); i++) {
						 g.mRenderer.getSeriesRendererAt(i).setHighlighted(i == seriesSelection.getPointIndex());
					 }
					 gView.repaint();
					
					Toast.makeText(DIYActivity.this, "Chart data point index " + seriesSelection.getPointIndex() + 
							" selected" + " point value=" + seriesSelection.getValue(), Toast.LENGTH_SHORT).show();
				}
			}
		});


		layoutChartActualBudget.addView(gView, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		creatYourBudgetChart(sectors) ;

	}

	private void creatYourBudgetChart(List<PieChartModel> s) {
		
		GraphManager g = new GraphManager();
		setGRenderer(g);
		
		GraphicalView gView = g.getPieChartView(this, s, YOUR_BUDGET,
				true, true, true, true, false);
		layoutChartYourBudget.addView(gView, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));


	}

	private void setGRenderer(GraphManager g) {
		g.mRenderer.setChartTitleTextSize(40);
		g.mRenderer.setLabelsTextSize(18);
	}

}
