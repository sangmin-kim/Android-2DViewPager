package co.bytera.twodimensionalviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import co.bytera.twodimensionalviewpager.R;
import co.bytera.twodimensionalviewpager.fragment.TwoDimensionalViewPagerFragment;

public class PagerActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		
		/*
		 * Assign fragment into fragmentContainer in the activity layout
		 */
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		
		if(fragment == null) {
			fragment = new TwoDimensionalViewPagerFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment)
			.commit();
		}
	}

}
