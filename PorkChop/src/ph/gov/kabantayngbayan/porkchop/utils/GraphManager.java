package ph.gov.kabantayngbayan.porkchop.utils;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import ph.gov.kabantayngbayan.porkchop.models.PieChartModel;
import android.content.Context;
import android.graphics.Color;

public class GraphManager {

	// Colors to be used for the pie slices.
	private final static int[] COLORS = ColorManager.COLORS;
	public CategorySeries mSeries = new CategorySeries("");
	// The main renderer for the main dataset.
	public DefaultRenderer mRenderer = new DefaultRenderer();

	public GraphicalView getPieChartView(Context context, List<PieChartModel> pies, String title, boolean showZoomButton, boolean zoomEnable, boolean showLegend, boolean isPanEnable) {

		mRenderer.setZoomButtonsVisible(showZoomButton);
		mRenderer.setStartAngle(180);
		mRenderer.setDisplayValues(true);
		mRenderer.setClickEnabled(true);
		mRenderer.setZoomEnabled(zoomEnable);
		mRenderer.setPanEnabled(isPanEnable);
		mRenderer.setChartTitle(title);
		mRenderer.setLabelsColor(Color.BLACK);
		
		
		for (PieChartModel p: pies) {
			mSeries.add(p.getCategory(), p.getValue()/* value */);
			SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
			renderer.setShowLegendItem(showLegend);
			renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
			mRenderer.addSeriesRenderer(renderer);
		}

		//mRenderer.getSeriesRendererAt(maxPos).setHighlighted(true);
		mRenderer.setBackgroundColor(Color.rgb(251, 197, 50));
		//mRenderer.setMargins(new int[] { 0, 0, 50, 50 });
		mRenderer.setApplyBackgroundColor(true);

		return ChartFactory.getPieChartView(context, mSeries, mRenderer);
	}


}
