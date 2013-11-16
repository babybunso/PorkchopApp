package ph.gov.kabantayngbayan.porkchop;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

import ph.gov.kabantayngbayan.porkchop.models.PieChartModel;
import ph.gov.kabantayngbayan.porkchop.utils.GraphManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PieGraphBase extends Activity {

	private GraphicalView mChartView;
	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_chart);

		layout = (LinearLayout) findViewById(R.id.chart);
		final GraphManager g = new GraphManager();

		List<PieChartModel> pies = new ArrayList<PieChartModel>();
		for(int i=0; i<5; i++) {
			PieChartModel p = new PieChartModel("Category"+i, 10+i);
			pies.add(p);
		}
		

		
		
		mChartView = g.getPieChartView(this, pies, "Title", true, true);
		
		mChartView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// animate();
				SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
				if (seriesSelection == null) {
					Toast.makeText(PieGraphBase.this, "No chart element selected", Toast.LENGTH_SHORT).show();
				} else {
					 for (int i = 0; i < g.mSeries.getItemCount(); i++) {
						 g.mRenderer.getSeriesRendererAt(i).setHighlighted(i == seriesSelection.getPointIndex());
					 }
					mChartView.repaint();
					
					Toast.makeText(PieGraphBase.this, "Chart data point index " + seriesSelection.getPointIndex() + 
							" selected" + " point value=" + seriesSelection.getValue(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		layout.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}
	
	
	
	private void animate() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.rail);
		layout.startAnimation(animation);
	}
}
