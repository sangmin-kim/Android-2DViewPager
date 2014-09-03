package co.bytera.twodimensionalviewpager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import co.bytera.twodimensionalviewpager.R;
import co.bytera.twodimensionalviewpager.adapter.DepthPageTransformer;
import co.bytera.twodimensionalviewpager.adapter.TwoDimensionalViewPagerAdapter;

public class TwoDimensionalViewPagerFragment extends Fragment {

	protected static final String TAG = "BaseFragment";
	
	protected ViewPager viewPager;
	protected TwoDimensionalViewPagerAdapter adapter;

	/*
	 * Image data is 2-D array (even though array is ragged, it works fine)
	 * Following images are included only for demonstration purpose.
	 */
	private int[][] images = new int[][] {
			{ R.drawable.bmw, R.drawable.linux, R.drawable.motorcycle, R.drawable.lovato },
			{ R.drawable.mario, R.drawable.sponzebob, R.drawable.rose }, 
			{ R.drawable.cyrus, R.drawable.elphi, R.drawable.apple, R.drawable.basketball, R.drawable.elphi2 }};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_layout, container, false);
		
		viewPager = (ViewPager)v.findViewById(R.id.viewPager);
		
		/*
		 * To detect the gesture (UP/DOWN/LEFT/RIGHT)
		 */
		final GestureDetector gesture = new GestureDetector(getActivity(), 
				new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDown(MotionEvent e) {
				return true;
			}
			
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				final int SWIPE_MIN_DISTANCE = 120;
				final int SWIPE_MAX_OFF_PATH = 250;
				final int SWIPE_THRESHOLD_VELOCITY = 200;
				
				try {
					if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE 
							&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
						/*
						 * finger motion is UP side.  page is moving to next row
						 */
						adapter.nextRow();
						adapter.notifyDataSetChanged();
					} else if(e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
							&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
						/*
						 * finger motion is DOWN side.  page is moving to previous row
						 */
						adapter.previousRow();
						adapter.notifyDataSetChanged();
					} else if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE 
							&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						/*
						 * more action can be done here
						 */
			
					} else if(e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE 
							&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						/*
						 * more action can be done here
						 */
						
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				return super.onFling(e1,e2, velocityX, velocityY);
			}
		});
		
		/*
		 *  set on touch listener to the viewPager
		 */
		viewPager.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				return gesture.onTouchEvent(event);
			}
		});
		
		return v;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		adapter = new TwoDimensionalViewPagerAdapter(getActivity());
		adapter.setData(images);
		viewPager.setAdapter(adapter);
		viewPager.setPageTransformer(false, new DepthPageTransformer());
	}
	
}
