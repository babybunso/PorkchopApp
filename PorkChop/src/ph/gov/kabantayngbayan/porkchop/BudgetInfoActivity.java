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
	private int[] pics = {};
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
        	
             return pictures.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
        
      
        
    }
}
