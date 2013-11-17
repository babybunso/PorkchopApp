package ph.gov.kabantayngbayan.porkchop;


import ph.gov.kabantayngbayan.porkchop.utils.TouchImageView;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BudgetInfoActivity extends FragmentActivity{

	//BUDGET101
	private int[] pics = {
			R.drawable.budget_01,
			R.drawable.budget_02,
			R.drawable.budget_03,
			R.drawable.budget_04,
			R.drawable.budget_05,
			R.drawable.budget_06,
			R.drawable.budget_07,
			R.drawable.budget_08,
			R.drawable.budget_09,
			R.drawable.budget_10,
			R.drawable.budget_11,
			R.drawable.budget_12,
			R.drawable.budget_13,
			R.drawable.budget_14,
			R.drawable.budget_15,
			R.drawable.budget_16,
			R.drawable.budget_17,
			R.drawable.budget_18,
			R.drawable.budget_19,
			R.drawable.budget_20,
			R.drawable.budget_21,
			R.drawable.budget_22,
			R.drawable.budget_23,
			R.drawable.budget_24,
			R.drawable.budget_25,
	};
	private ViewPager pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget_info);
		initialize();
	}
	
	
	private void initialize() {
		int[] currentPics = null;
		currentPics = pics;

		pager = (ViewPager) findViewById(R.id.view_pager);
        PagerAdapter adapter = new MyPagerAdapter(currentPics);
		pager.setAdapter(adapter);
		
	}
	
	
	private class MyPagerAdapter extends PagerAdapter {
    	

	  	private int[] pictures;
	  	public MyPagerAdapter(int[] pictures) {
	  		this.pictures = pictures;
	  		
	  	}

        @Override
        public Object instantiateItem(View container, int position) { 
        	LayoutInflater inflater = (LayoutInflater)BudgetInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	View layout = inflater.inflate(R.layout.item_info, null);
        	TouchImageView view = (TouchImageView) layout.findViewById(R.id.img_carousel);
        	//ImageView view = (ImageView) layout.findViewById(R.id.img_carousel);
        	try {
	        		view.setImageResource(pictures[position]);
			} catch (Throwable e) {
				finish();
			}
        	
        	((ViewPager)container).addView(layout,0);
            return layout;
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
        	
             return 25;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
        
      
        
    }
}
